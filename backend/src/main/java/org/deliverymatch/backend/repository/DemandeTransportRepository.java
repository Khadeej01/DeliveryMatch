package org.deliverymatch.backend.repository;



import org.deliverymatch.backend.model.DemandeTransport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeTransportRepository extends JpaRepository<DemandeTransport, Long> {
    // Ajoutez des requêtes si nécessaire, par exemple :
    // List<DemandeTransport> findByUserId(Long userId);
    // List<DemandeTransport> findByTrajetId(Long trajetId);
}
