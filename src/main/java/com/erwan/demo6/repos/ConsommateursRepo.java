package com.erwan.demo6.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.erwan.demo6.entities.Consommateurs;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
public interface ConsommateursRepo extends JpaRepository<Consommateurs, Long>{
    @Transactional
    Long deleteByNomAndPrenom(String nom, String prenom);

    boolean existsByEmail(String email);
}
