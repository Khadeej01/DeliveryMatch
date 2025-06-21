package org.deliverymatch.backend.service.impl;

import org.deliverymatch.backend.dto.DemandeTransportDTO;
import org.deliverymatch.backend.model.DemandeTransport;
import org.deliverymatch.backend.repository.DemandeTransportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DemandeTransportServiceTest {

    @Mock
    private DemandeTransportRepository demandeTransportRepository;

    @InjectMocks
    private DemandeTransportService demandeTransportService;

    private DemandeTransport demandeTransport;
    private DemandeTransportDTO demandeTransportDTO;

    @BeforeEach
    void setUp() {
        demandeTransport = new DemandeTransport();
        demandeTransport.setId(1L);
        demandeTransport.setUserId(1L);
        demandeTransport.setTrajetId(1L);
        demandeTransport.setDimensions(100.0);
        demandeTransport.setPoids(10.5);
        demandeTransport.setTypeColis("Fragile");
        demandeTransport.setStatut("PENDING");

        demandeTransportDTO = new DemandeTransportDTO();
        demandeTransportDTO.setUserId(1L);
        demandeTransportDTO.setTrajetId(1L);
        demandeTransportDTO.setDimensions(100.0);
        demandeTransportDTO.setPoids(10.5);
        demandeTransportDTO.setTypeColis("Fragile");
    }

    @Test
    void testCreateDemande_Success() {

        when(demandeTransportRepository.save(any(DemandeTransport.class))).thenReturn(demandeTransport);


        DemandeTransportDTO result = demandeTransportService.createDemande(demandeTransportDTO);


        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getUserId());
        assertEquals(1L, result.getTrajetId());
        assertEquals(100.0, result.getDimensions());
        assertEquals(10.5, result.getPoids());
        assertEquals("Fragile", result.getTypeColis());
        assertEquals("PENDING", result.getStatut());

        verify(demandeTransportRepository).save(any(DemandeTransport.class));
    }

    @Test
    void testCreateDemande_WithNullTrajetId() {

        demandeTransportDTO.setTrajetId(null);
        demandeTransport.setTrajetId(null);
        when(demandeTransportRepository.save(any(DemandeTransport.class))).thenReturn(demandeTransport);


        DemandeTransportDTO result = demandeTransportService.createDemande(demandeTransportDTO);

        assertNotNull(result);
        assertNull(result.getTrajetId());
        assertEquals("PENDING", result.getStatut());

        verify(demandeTransportRepository).save(any(DemandeTransport.class));
    }

    @Test
    void testCreateDemande_WithZeroDimensions() {

        demandeTransportDTO.setDimensions(0.0);
        demandeTransport.setDimensions(0.0);
        when(demandeTransportRepository.save(any(DemandeTransport.class))).thenReturn(demandeTransport);


        DemandeTransportDTO result = demandeTransportService.createDemande(demandeTransportDTO);


        assertNotNull(result);
        assertEquals(0.0, result.getDimensions());

        verify(demandeTransportRepository).save(any(DemandeTransport.class));
    }

    @Test
    void testCreateDemande_WithZeroPoids() {

        demandeTransportDTO.setPoids(0.0);
        demandeTransport.setPoids(0.0);
        when(demandeTransportRepository.save(any(DemandeTransport.class))).thenReturn(demandeTransport);


        DemandeTransportDTO result = demandeTransportService.createDemande(demandeTransportDTO);


        assertNotNull(result);
        assertEquals(0.0, result.getPoids());

        verify(demandeTransportRepository).save(any(DemandeTransport.class));
    }

    @Test
    void testCreateDemande_WithEmptyTypeColis() {

        demandeTransportDTO.setTypeColis("");
        demandeTransport.setTypeColis("");
        when(demandeTransportRepository.save(any(DemandeTransport.class))).thenReturn(demandeTransport);


        DemandeTransportDTO result = demandeTransportService.createDemande(demandeTransportDTO);


        assertNotNull(result);
        assertEquals("", result.getTypeColis());

        verify(demandeTransportRepository).save(any(DemandeTransport.class));
    }

    @Test
    void testCreateDemande_RepositoryThrowsException() {

        when(demandeTransportRepository.save(any(DemandeTransport.class)))
                .thenThrow(new RuntimeException("Database error"));


        assertThrows(RuntimeException.class, () -> {
            demandeTransportService.createDemande(demandeTransportDTO);
        });

        verify(demandeTransportRepository).save(any(DemandeTransport.class));
    }

    @Test
    void testCreateDemande_VerifyDefaultStatut() {

        when(demandeTransportRepository.save(any(DemandeTransport.class))).thenReturn(demandeTransport);


        DemandeTransportDTO result = demandeTransportService.createDemande(demandeTransportDTO);


        assertEquals("PENDING", result.getStatut());


        verify(demandeTransportRepository).save(argThat(demande ->
                "PENDING".equals(demande.getStatut())
        ));
    }

    @Test
    void testCreateDemande_VerifyAllFieldsMapped() {

        when(demandeTransportRepository.save(any(DemandeTransport.class))).thenReturn(demandeTransport);


        demandeTransportService.createDemande(demandeTransportDTO);


        verify(demandeTransportRepository).save(argThat(demande ->
                demande.getUserId().equals(demandeTransportDTO.getUserId()) &&
                        demande.getTrajetId().equals(demandeTransportDTO.getTrajetId()) &&
                        demande.getDimensions() == demandeTransportDTO.getDimensions() &&
                        demande.getPoids() == demandeTransportDTO.getPoids() &&
                        demande.getTypeColis().equals(demandeTransportDTO.getTypeColis())
        ));
    }
}