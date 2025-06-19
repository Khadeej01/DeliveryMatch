package org.deliverymatch.backend.model.utilisateur;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EXPEDITEUR")
public class Expediteur extends User {
    public Expediteur() {}
}