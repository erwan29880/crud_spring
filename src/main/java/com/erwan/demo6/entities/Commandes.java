package com.erwan.demo6.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter 
@Setter
@Entity
@Table(name = "commandes")
public class Commandes {
    
    @Id 
    @Column(name = "commande_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commandId;

    @Column(name = "commande_date")
    private Date commandDate;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "consommateur_id")
    Consommateurs consommateurs;


    @ManyToMany(
        cascade = CascadeType.ALL, fetch = FetchType.EAGER
    )
    @JoinTable(
        name = "commandes_produits",
        joinColumns = @JoinColumn(name = "commande_id"), 
        inverseJoinColumns = @JoinColumn(name = "produit_id")
    )
    private List<Produits> produits;


    @Override
    public String toString() {
        return "commande";
    }
}
