package com.wipro.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.entity.UserCredential;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {
    Optional<UserCredential> findByName(String username);
}
