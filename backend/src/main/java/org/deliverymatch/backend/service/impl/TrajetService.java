package org.deliverymatch.backend.service.impl;

import org.deliverymatch.backend.dto.TrajetDTO;
import org.deliverymatch.backend.model.Trajet;
import org.deliverymatch.backend.repository.TrajetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrajetService {

    @Autowired
    private TrajetRepository trajetRepository;

    public TrajetDTO createTrajet(TrajetDTO trajetDTO) {
        Trajet trajet = new Trajet();
        trajet.setUserId(trajetDTO.getUserId());
        trajet.setLieuDepart(trajetDTO.getLieuDepart());
        trajet.setEtapesIntermediaires(trajetDTO.getEtapesIntermediaires());
        trajet.setDestinationFinale(trajetDTO.getDestinationFinale());
        trajet.setDimensionsMax(trajetDTO.getDimensionsMax());
        trajet.setTypeMarchandise(trajetDTO.getTypeMarchandise());
        trajet.setCapaciteDisponible(trajetDTO.getCapaciteDisponible());
        trajet = trajetRepository.save(trajet);

        TrajetDTO resultDTO = new TrajetDTO();
        resultDTO.setId(trajet.getId());
        resultDTO.setUserId(trajet.getUserId());
        resultDTO.setLieuDepart(trajet.getLieuDepart());
        resultDTO.setEtapesIntermediaires(trajet.getEtapesIntermediaires());
        resultDTO.setDestinationFinale(trajet.getDestinationFinale());
        resultDTO.setDimensionsMax(trajet.getDimensionsMax());
        resultDTO.setTypeMarchandise(trajet.getTypeMarchandise());
        resultDTO.setCapaciteDisponible(trajet.getCapaciteDisponible());
        return resultDTO;
    }

    public List<TrajetDTO> getAllTrajets() {
        return trajetRepository.findAll().stream().map(trajet -> {
            TrajetDTO dto = new TrajetDTO();
            dto.setId(trajet.getId());
            dto.setUserId(trajet.getUserId());
            dto.setLieuDepart(trajet.getLieuDepart());
            dto.setEtapesIntermediaires(trajet.getEtapesIntermediaires());
            dto.setDestinationFinale(trajet.getDestinationFinale());
            dto.setDimensionsMax(trajet.getDimensionsMax());
            dto.setTypeMarchandise(trajet.getTypeMarchandise());
            dto.setCapaciteDisponible(trajet.getCapaciteDisponible());
            return dto;
        }).collect(Collectors.toList());
    }
}