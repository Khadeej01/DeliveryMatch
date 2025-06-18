package org.deliverymatch.backend.dto;

public class RegisterUserDTO {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String role; // Ex: ROLE_CONDUCTEUR, ROLE_EXPEDITEUR, ROLE_ADMIN

    // Constructeur par défaut
    public RegisterUserDTO() {}

    // Getters et setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}