package com.erwan.demo6.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Data 
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToMany(mappedBy = "produits")
    List<Commandes> commandes;

    @Override
    public String toString() {
        return "produits";
    }
}
