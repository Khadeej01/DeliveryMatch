package org.deliverymatch.backend.repository;




import org.deliverymatch.backend.model.utilisateur.Conducteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConducteurRepository extends JpaRepository<Conducteur, Long> {
}