package org.deliverymatch.backend.dto;

public class DemandeTransportDTO {
    private Long id;
    private Long userId; // ID de l'utilisateur (Expediteur)
    private Long trajetId; // Optionnel, pour lier à un trajet
    private double dimensions;
    private double poids;
    private String typeColis;
    private String statut; // Ex: PENDING, ACCEPTED, REJECTED

    // Constructeur par défaut
    public DemandeTransportDTO() {}

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