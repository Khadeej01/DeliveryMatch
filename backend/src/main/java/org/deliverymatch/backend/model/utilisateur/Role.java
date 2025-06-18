package org.deliverymatch.backend.model.utilisateur;


public enum Role {
    ADMIN,
    CONDUCTEUR,
    EXPEDITEUR;

    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}