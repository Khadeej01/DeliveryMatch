package org.deliverymatch.backend.controller.expediteur;



import org.deliverymatch.backend.dto.DemandeTransportDTO;
import org.deliverymatch.backend.service.impl.DemandeTransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demandes")
public class DemandeTransportController {

    @Autowired
    private DemandeTransportService demandeTransportService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_EXPEDITEUR')")
    public ResponseEntity<DemandeTransportDTO> createDemande(@RequestBody DemandeTransportDTO demandeDTO) {
        return ResponseEntity.ok(demandeTransportService.createDemande(demandeDTO));
    }
}