package org.deliverymatch.backend.repository;

import org.deliverymatch.backend.model.utilisateur.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}