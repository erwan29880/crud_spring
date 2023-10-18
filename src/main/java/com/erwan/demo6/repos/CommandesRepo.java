package com.erwan.demo6.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.erwan.demo6.entities.Commandes;

import java.util.List;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;

@Repository
public interface CommandesRepo extends JpaRepository<Commandes, Long> {

    boolean existsById(Long id);
    // Commandes findFirstByOrderByIdDesc();

    @Query(value = "select count(consommateur_id) from commandes where consommateur_id=?1", nativeQuery = true)
    long countByConsommateurid(Long id);

    @Query(value = "select * from commandes order by commande_id desc limit 1", nativeQuery=true)
    Commandes findByLastId();

    @Modifying
    @Transactional
    @Query(value = "update commandes set consommateur_id=?1 where commande_id=?2", nativeQuery = true)
    void updateCommande(Long consommateurId, Long CommandeId);

    @Modifying
    @Transactional
    @Query(value = "insert into commandes_produits(commande_id, produit_id) values (?1, ?2)", nativeQuery = true)
    void insertCommandeProduit(Long commandeId, Long produitId);

    @Query(value = "select * from commandes_produits where commande_id=?1", nativeQuery = true)
    List<Tuple> findCommandeProduitByCommandeId(Long commandeId);

    @Modifying
    @Transactional
    @Query(value = "delete from commandes_produits where commande_id=?1", nativeQuery = true)
    void deleteCommandeProduit(Long commandeId);
}
