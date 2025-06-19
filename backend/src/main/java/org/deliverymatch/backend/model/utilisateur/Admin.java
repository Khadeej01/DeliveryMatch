package org.deliverymatch.backend.model.utilisateur;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {
    public Admin() {
        // No need to setRole here, JPA will handle the discriminator
    }
}