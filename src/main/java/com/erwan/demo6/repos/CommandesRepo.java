package com.erwan.demo6.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.erwan.demo6.entities.Commandes;

import jakarta.transaction.Transactional;

@Repository
public interface CommandesRepo extends JpaRepository<Commandes, Long> {

    @Query(value = "select * from commandes order by commande_id desc limit 1", nativeQuery=true)
    Commandes findByLastId();

    @Modifying
    @Transactional
    @Query(value = "update commandes set consommateur_id=?1 where commande_id=?2", nativeQuery = true)
    void updateCommande(Long consommateurId, Long CommandeId);
}
