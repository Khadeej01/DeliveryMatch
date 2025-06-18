package org.deliverymatch.backend.service.impl;

import org.deliverymatch.backend.dto.DemandeTransportDTO;
import org.deliverymatch.backend.model.DemandeTransport;
import org.deliverymatch.backend.repository.DemandeTransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandeTransportService {

    @Autowired
    private DemandeTransportRepository demandeTransportRepository;

    public DemandeTransportDTO createDemande(DemandeTransportDTO demandeDTO) {
        DemandeTransport demande = new DemandeTransport();
        demande.setUserId(demandeDTO.getUserId());
        demande.setTrajetId(demandeDTO.getTrajetId());
        demande.setDimensions(demandeDTO.getDimensions());
        demande.setPoids(demandeDTO.getPoids());
        demande.setTypeColis(demandeDTO.getTypeColis());
        demande.setStatut("PENDING");
        demande = demandeTransportRepository.save(demande);

        DemandeTransportDTO resultDTO = new DemandeTransportDTO();
        resultDTO.setId(demande.getId());
        resultDTO.setUserId(demande.getUserId());
        resultDTO.setTrajetId(demande.getTrajetId());
        resultDTO.setDimensions(demande.getDimensions());
        resultDTO.setPoids(demande.getPoids());
        resultDTO.setTypeColis(demande.getTypeColis());
        resultDTO.setStatut(demande.getStatut());
        return resultDTO;
    }
}