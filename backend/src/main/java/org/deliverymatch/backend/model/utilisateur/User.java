package org.deliverymatch.backend.model.utilisateur;

import jakarta.persistence.*;
import org.hibernate.annotations.DiscriminatorFormula;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorFormula("CASE WHEN role = 'ROLE_CONDUCTEUR' THEN 'CONDUCTEUR' ELSE role END")
@Entity
@Table(name = "users")
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nom;
    protected String prenom;
    protected String email;
    protected String password;
    protected String role; // Sera utilisé comme discriminateur

    // Constructeur par défaut (requis pour JPA)
    public User() {}

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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