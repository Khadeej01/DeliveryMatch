package org.deliverymatch.backend.dto;

public class UserDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String role;
    private String vehiculeType; // Pour Conducteur
    private double capaciteMax; // Pour Conducteur
    private String licenceNumber; // Pour Conducteur

    // Constructeur par défaut
    public UserDTO() {}

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getVehiculeType() { return vehiculeType; }
    public void setVehiculeType(String vehiculeType) { this.vehiculeType = vehiculeType; }
    public double getCapaciteMax() { return capaciteMax; }
    public void setCapaciteMax(double capaciteMax) { this.capaciteMax = capaciteMax; }
    public String getLicenceNumber() { return licenceNumber; }
    public void setLicenceNumber(String licenceNumber) { this.licenceNumber = licenceNumber; }
}