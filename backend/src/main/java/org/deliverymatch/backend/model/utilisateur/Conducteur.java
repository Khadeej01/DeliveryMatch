package org.deliverymatch.backend.model.utilisateur;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CONDUCTEUR")
public class Conducteur extends User {
    private String vehiculeType;
    private double capaciteMax;
    private String licenceNumber;

    public Conducteur() {}

    public String getVehiculeType() { return vehiculeType; }
    public void setVehiculeType(String vehiculeType) { this.vehiculeType = vehiculeType; }
    public double getCapaciteMax() { return capaciteMax; }
    public void setCapaciteMax(double capaciteMax) { this.capaciteMax = capaciteMax; }
    public String getLicenceNumber() { return licenceNumber; }
    public void setLicenceNumber(String licenceNumber) { this.licenceNumber = licenceNumber; }
}