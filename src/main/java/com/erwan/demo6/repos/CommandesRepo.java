package com.erwan.demo6.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.erwan.demo6.entities.Commandes;

@Repository
public interface CommandesRepo extends JpaRepository<Commandes, Long> {
}
