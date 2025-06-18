package org.deliverymatch.backend.controller.conducteur;




import org.deliverymatch.backend.dto.TrajetDTO;
import org.deliverymatch.backend.service.impl.TrajetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trajets")
public class TrajetController {

    @Autowired
    private TrajetService trajetService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CONDUCTEUR')")
    public ResponseEntity<TrajetDTO> createTrajet(@RequestBody TrajetDTO trajetDTO) {
        return ResponseEntity.ok(trajetService.createTrajet(trajetDTO));
    }

    @GetMapping
    public ResponseEntity<List<TrajetDTO>> getAllTrajets() {
        return ResponseEntity.ok(trajetService.getAllTrajets());
    }
}