package org.deliverymatch.backend.service.impl;

import org.deliverymatch.backend.dto.LoginDTO;
import org.deliverymatch.backend.dto.RegisterUserDTO;
import org.deliverymatch.backend.dto.UserDTO;
import org.deliverymatch.backend.model.utilisateur.Admin;
import org.deliverymatch.backend.model.utilisateur.Conducteur;
import org.deliverymatch.backend.model.utilisateur.Expediteur;
import org.deliverymatch.backend.model.utilisateur.User;
import org.deliverymatch.backend.repository.UserRepository;
import org.deliverymatch.backend.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public UserDTO registerUser(RegisterUserDTO registerDTO) {
        User user;
        switch (registerDTO.getRole()) {
            case "ROLE_CONDUCTEUR":
                user = new Conducteur();
                break;
            case "ROLE_EXPEDITEUR":
                user = new Expediteur();
                break;
            case "ROLE_ADMIN":
                user = new Admin();
                break;
            default:
                throw new IllegalArgumentException("Rôle invalide");
        }

        user.setNom(registerDTO.getNom());
        user.setPrenom(registerDTO.getPrenom());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRole(registerDTO.getRole());
        user = userRepository.save(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setNom(user.getNom());
        userDTO.setPrenom(user.getPrenom());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public String loginUser(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail());
        if (user != null && passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return jwtUtils.generateToken(user);
        }
        throw new RuntimeException("Invalid credentials");
    }
}