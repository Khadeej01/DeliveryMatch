package org.deliverymatch.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "demande_transports")
public class DemandeTransport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId; // ID de l'utilisateur (Expediteur)
    private Long trajetId; // ID du trajet associé
    private double dimensions;
    private double poids;
    private String typeColis;
    private String statut; // Ex: PENDING, ACCEPTED, REJECTED

    // Constructeur par défaut (requis pour JPA)
    public DemandeTransport() {}

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getTrajetId() { return trajetId; }
    public void setTrajetId(Long trajetId) { this.trajetId = trajetId; }
    public double getDimensions() { return dimensions; }
    public void setDimensions(double dimensions) { this.dimensions = dimensions; }
    public double getPoids() { return poids; }
    public void setPoids(double poids) { this.poids = poids; }
    public String getTypeColis() { return typeColis; }
    public void setTypeColis(String typeColis) { this.typeColis = typeColis; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
}