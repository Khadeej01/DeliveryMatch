package org.deliverymatch.backend.model.utilisateur;



import jakarta.persistence.Entity;

@Entity
public class Conducteur extends User {
    private String vehiculeType; // Type de véhicule (ex: voiture, camion)
    private double capaciteMax; // Capacité maximale en kg ou m³
    private String licenceNumber; // Numéro de licence

    // Constructeur par défaut (requis pour JPA)
    public Conducteur() {
        setRole("ROLE_CONDUCTEUR"); // Définit le rôle par défaut
    }

    // Getters et setters
    public String getVehiculeType() { return vehiculeType; }
    public void setVehiculeType(String vehiculeType) { this.vehiculeType = vehiculeType; }
    public double getCapaciteMax() { return capaciteMax; }
    public void setCapaciteMax(double capaciteMax) { this.capaciteMax = capaciteMax; }
    public String getLicenceNumber() { return licenceNumber; }
    public void setLicenceNumber(String licenceNumber) { this.licenceNumber = licenceNumber; }
}