package org.deliverymatch.backend.repository;






import org.deliverymatch.backend.model.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrajetRepository extends JpaRepository<Trajet, Long> {
    // Vous pouvez ajouter des requêtes dérivées si nécessaire, par exemple :
    // List<Trajet> findByUserId(Long userId);
}