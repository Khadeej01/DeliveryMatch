package org.deliverymatch.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "trajets")
public class Trajet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String lieuDepart;
    private String etapesIntermediaires;
    private String destinationFinale;
    private double dimensionsMax;
    private String typeMarchandise;
    private int capaciteDisponible;

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getLieuDepart() { return lieuDepart; }
    public void setLieuDepart(String lieuDepart) { this.lieuDepart = lieuDepart; }
    public String getEtapesIntermediaires() { return etapesIntermediaires; }
    public void setEtapesIntermediaires(String etapesIntermediaires) { this.etapesIntermediaires = etapesIntermediaires; }
    public String getDestinationFinale() { return destinationFinale; }
    public void setDestinationFinale(String destinationFinale) { this.destinationFinale = destinationFinale; }
    public double getDimensionsMax() { return dimensionsMax; }
    public void setDimensionsMax(double dimensionsMax) { this.dimensionsMax = dimensionsMax; }
    public String getTypeMarchandise() { return typeMarchandise; }
    public void setTypeMarchandise(String typeMarchandise) { this.typeMarchandise = typeMarchandise; }
    public int getCapaciteDisponible() { return capaciteDisponible; }
    public void setCapaciteDisponible(int capaciteDisponible) { this.capaciteDisponible = capaciteDisponible; }
}