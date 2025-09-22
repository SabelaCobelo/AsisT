package com.asist.service;

import com.asist.model.User;
import com.asist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Registra un nuevo usuario con contraseña hasheada
     */
    public User registerUser(User user) {
        // Hashear la contraseña antes de guardar
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Busca usuario por email
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Busca usuario por ID
     */
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Obtiene todos los usuarios
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Actualiza información del usuario
     */
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Elimina usuario por ID
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Verifica si existe un usuario con el email dado
     */
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Valida credenciales de usuario
     */
    public boolean validateUserCredentials(String email, String password) {
        Optional<User> userOpt = findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    /**
     * Cambia la contraseña del usuario
     */
    public boolean changePassword(Long userId, String oldPassword, String newPassword) {
        Optional<User> userOpt = findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(oldPassword, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }
}
