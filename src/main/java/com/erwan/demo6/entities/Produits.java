package com.erwan.demo6.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter 
@Setter 
@Entity 
@Table(name = "produits")
public class Produits {

    @Id 
    @Column(name = "produit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produitId; 

    private String produit;

    public Produits(String produit) {
        this.produit = produit;
    }

    @ManyToMany(mappedBy = "produits")
    List<Commandes> commandes;

    @Override
    public String toString() {
        return "produits";
    }
}
