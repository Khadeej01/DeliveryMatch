package org.deliverymatch.backend.model.utilisateur;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User {
    public Admin() {
        setRole("ROLE_ADMIN"); // Définit le rôle par défaut
    }
}