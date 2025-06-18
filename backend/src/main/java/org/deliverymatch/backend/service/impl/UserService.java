package org.deliverymatch.backend.service.impl;


import org.deliverymatch.backend.dto.RegisterUserDTO;
import org.deliverymatch.backend.dto.UserDTO;
import org.deliverymatch.backend.model.utilisateur.Conducteur;
import org.deliverymatch.backend.model.utilisateur.Expediteur;
import org.deliverymatch.backend.model.utilisateur.User;
import org.deliverymatch.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserDTO registerUser(RegisterUserDTO registerDTO) {
        User user;
        if ("ROLE_CONDUCTEUR".equals(registerDTO.getRole())) {
            user = new Conducteur();
        } else if ("ROLE_EXPEDITEUR".equals(registerDTO.getRole())) {
            user = new Expediteur();
        } else {
            throw new IllegalArgumentException("Rôle invalide");
        }

        user.setNom(registerDTO.getNom());
        user.setPrenom(registerDTO.getPrenom());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRole(registerDTO.getRole());

        if (user instanceof Conducteur) {
            ((Conducteur) user).setVehiculeType(registerDTO.getVehiculeType());
            ((Conducteur) user).setCapaciteMax(registerDTO.getCapaciteMax());
            ((Conducteur) user).setLicenceNumber(registerDTO.getLicenceNumber());
        }

        user = userRepository.save(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setNom(user.getNom());
        userDTO.setPrenom(user.getPrenom());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}