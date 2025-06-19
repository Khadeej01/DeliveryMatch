package org.deliverymatch.backend.controller.admin;

import org.deliverymatch.backend.dto.UserDTO;
import org.deliverymatch.backend.model.utilisateur.User;
import org.deliverymatch.backend.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    // Lister tous les utilisateurs
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDTO> userDTOs = users.stream().map(user -> {
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setNom(user.getNom());
            dto.setPrenom(user.getPrenom());
            dto.setEmail(user.getEmail());
            // Use the class name as the role
            dto.setRole(user.getClass().getSimpleName().toUpperCase());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(userDTOs);
    }

    // Récupérer un utilisateur par ID
    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setNom(user.getNom());
        dto.setPrenom(user.getPrenom());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getClass().getSimpleName().toUpperCase());
        return ResponseEntity.ok(dto);
    }

    // Mettre à jour un utilisateur
    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User user = userService.updateUser(id, userDTO);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userDTO.setId(user.getId());
        return ResponseEntity.ok(userDTO);
    }

    // Supprimer un utilisateur
    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}