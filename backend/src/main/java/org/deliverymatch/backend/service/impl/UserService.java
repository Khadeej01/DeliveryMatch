package org.deliverymatch.backend.service.impl;

import org.deliverymatch.backend.dto.UserDTO;
import org.deliverymatch.backend.model.utilisateur.Conducteur;
import org.deliverymatch.backend.model.utilisateur.Expediteur;
import org.deliverymatch.backend.model.utilisateur.User;
import org.deliverymatch.backend.dto.RegisterUserDTO;
import org.deliverymatch.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Inscription (déjà existante)
    public UserDTO registerUser(RegisterUserDTO registerDTO) {
        User user;
        if ("CONDUCTEUR".equalsIgnoreCase(registerDTO.getRole()) || "ROLE_CONDUCTEUR".equalsIgnoreCase(registerDTO.getRole())) {
            user = new Conducteur();
        } else if ("EXPEDITEUR".equalsIgnoreCase(registerDTO.getRole()) || "ROLE_EXPEDITEUR".equalsIgnoreCase(registerDTO.getRole())) {
            user = new Expediteur();
        } else {
            throw new IllegalArgumentException("Rôle invalide");
        }

        user.setNom(registerDTO.getNom());
        user.setPrenom(registerDTO.getPrenom());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        // No setRole here!

        user = userRepository.save(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setNom(user.getNom());
        userDTO.setPrenom(user.getPrenom());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getClass().getSimpleName().toUpperCase());
        return userDTO;
    }

    // Lister tous les utilisateurs
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Récupérer un utilisateur par ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Mettre à jour un utilisateur
    public User updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setNom(userDTO.getNom());
            user.setPrenom(userDTO.getPrenom());
            user.setEmail(userDTO.getEmail());
            // Do not update the role here, as it's managed by JPA discriminator
            // Do not update the password here for security reasons
            return userRepository.save(user);
        }
        return null;
    }

    // Supprimer un utilisateur
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}