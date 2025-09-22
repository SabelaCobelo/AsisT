package com.asist.controller;

import com.asist.model.User;
import com.asist.repository.UserRepository;
import com.asist.service.JwtService;
import com.asist.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        try {
            // Validaciones de negocio adicionales
            if (userService.existsByEmail(request.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new MessageResponse("El email ya está registrado"));
            }

            // Crear usuario y hashear contraseña
            User user = new User();
            user.setEmail(request.getEmail());
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            User saved = userService.registerUser(user);

            // Autenticación inmediata opcional tras registro
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword()
            );
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtService.generateToken(
                    org.springframework.security.core.userdetails.User.withUsername(saved.getEmail())
                            .password(saved.getPassword())
                            .authorities("ROLE_USER")
                            .build()
            );
            String refresh = jwtService.generateRefreshToken(
                    org.springframework.security.core.userdetails.User.withUsername(saved.getEmail())
                            .password(saved.getPassword())
                            .authorities("ROLE_USER")
                            .build()
            );

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(JwtResponse.of(jwt, refresh, saved));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error en registro: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword()
            );
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Cargar usuario
            Optional<User> userOpt = userService.findByEmail(request.getEmail());
            if (userOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new MessageResponse("Credenciales inválidas"));
            }
            User user = userOpt.get();

            // Generar tokens
            var principal = org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                    .password(user.getPassword()).authorities("ROLE_USER").build();
            String jwt = jwtService.generateToken(principal);
            String refresh = jwtService.generateRefreshToken(principal);

            return ResponseEntity.ok(JwtResponse.of(jwt, refresh, user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Login fallido: " + e.getMessage()));
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> payload) {
        String refreshToken = payload.get("refreshToken");
        if (refreshToken == null || refreshToken.isBlank()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Refresh token requerido"));
        }
        if (!jwtService.validateToken(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Refresh token inválido"));
        }
        String email = jwtService.extractUsername(refreshToken);
        var userOpt = userService.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Usuario no encontrado"));
        }
        var principal = org.springframework.security.core.userdetails.User.withUsername(email)
                .password(userOpt.get().getPassword()).authorities("ROLE_USER").build();
        String newAccess = jwtService.generateToken(principal);
        return ResponseEntity.ok(new HashMap<>() {{
            put("accessToken", newAccess);
        }});
    }

    // DTOs con validaciones avanzadas
    public static class RegisterRequest {
        @NotBlank(message = "El username es obligatorio")
        @Size(min = 3, max = 50)
        private String username;
        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Email no válido")
        private String email;
        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 8, max = 128, message = "La contraseña debe tener entre 8 y 128 caracteres")
        private String password;
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class LoginRequest {
        @NotBlank
        @Email
        private String email;
        @NotBlank
        private String password;
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class MessageResponse {
        private String message;
        public MessageResponse(String message) { this.message = message; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }

    public static class JwtResponse {
        private String accessToken;
        private String refreshToken;
        private String tokenType = "Bearer";
        private Map<String, Object> user;
        public static JwtResponse of(String access, String refresh, User userEntity) {
            JwtResponse r = new JwtResponse();
            r.accessToken = access;
            r.refreshToken = refresh;
            r.user = Map.of(
                    "id", userEntity.getId(),
                    "email", userEntity.getEmail(),
                    "username", userEntity.getUsername()
            );
            return r;
        }
        public String getAccessToken() { return accessToken; }
        public String getRefreshToken() { return refreshToken; }
        public String getTokenType() { return tokenType; }
        public Map<String, Object> getUser() { return user; }
    }
}
