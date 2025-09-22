package com.asist.controller;

import com.asist.model.User;
import com.asist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * UserController - REST Controller para la gestión de usuarios
 * 
 * Proporciona endpoints REST para operaciones CRUD sobre usuarios:
 * - GET /api/users - Obtener todos los usuarios
 * - GET /api/users/{id} - Obtener usuario por ID
 * - POST /api/users - Registrar nuevo usuario
 * - PUT /api/users/{id} - Actualizar usuario existente
 * - DELETE /api/users/{id} - Eliminar usuario
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Obtener todos los usuarios
     * GET /api/users
     * 
     * @return ResponseEntity con lista de todos los usuarios
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtener usuario por ID
     * GET /api/users/{id}
     * 
     * @param id ID del usuario a buscar
     * @return ResponseEntity con el usuario encontrado o error 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        try {
            Optional<User> userData = userRepository.findById(id);
            if (userData.isPresent()) {
                return new ResponseEntity<>(userData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Registrar nuevo usuario
     * POST /api/users
     * 
     * @param user Datos del usuario a registrar
     * @return ResponseEntity con el usuario creado
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User newUser = userRepository.save(new User(user.getName(), user.getEmail(), user.getPassword()));
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Actualizar usuario existente
     * PUT /api/users/{id}
     * 
     * @param id ID del usuario a actualizar
     * @param user Nuevos datos del usuario
     * @return ResponseEntity con el usuario actualizado o error 404
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        try {
            Optional<User> userData = userRepository.findById(id);
            if (userData.isPresent()) {
                User existingUser = userData.get();
                existingUser.setName(user.getName());
                existingUser.setEmail(user.getEmail());
                existingUser.setPassword(user.getPassword());
                return new ResponseEntity<>(userRepository.save(existingUser), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Eliminar usuario
     * DELETE /api/users/{id}
     * 
     * @param id ID del usuario a eliminar
     * @return ResponseEntity con estado de la operación
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
