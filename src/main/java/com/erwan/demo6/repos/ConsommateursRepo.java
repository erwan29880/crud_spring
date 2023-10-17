package com.erwan.demo6.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

import com.erwan.demo6.entities.Consommateurs;

@Repository
public interface ConsommateursRepo extends JpaRepository<Consommateurs, Long>{
    
    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "delete from consommateurs where nom=?1 and prenom=?2", nativeQuery = true)
    void deleteByNomAndPrenom(String nom, String prenom);
    

    @Modifying
    @Transactional
    @Query(value ="delete from consommateurs where email=?1", nativeQuery = true)
    void deleteByEmail(String email);
    
    @Query(value = "select consommateur_id from consommateurs where email=?1 limit 1", nativeQuery = true)
    Long findIdByEmail(String email);
}
