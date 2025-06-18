package org.deliverymatch.backend.model.utilisateur;

import jakarta.persistence.Entity;

@Entity
public class Expediteur extends User {
    public Expediteur() {
        setRole("ROLE_EXPEDITEUR"); // Définit le rôle par défaut
    }
}