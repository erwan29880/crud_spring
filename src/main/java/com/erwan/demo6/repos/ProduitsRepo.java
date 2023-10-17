package com.erwan.demo6.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.erwan.demo6.entities.Produits;

@Repository
public interface ProduitsRepo extends JpaRepository<Produits, Long> {
    
    @Query(value = "select * from produits where produit=?1 limit 1", nativeQuery = true)
    Produits findByProduit(String produit);
}
