package com.erwan.demo6.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.erwan.demo6.entities.Commandes;

@Repository
public interface CommandesRepo extends JpaRepository<Commandes, Long> {

    @Query(value = "select * from commandes order by commande_id desc limit 1", nativeQuery=true)
    Commandes findByLastId();
}
