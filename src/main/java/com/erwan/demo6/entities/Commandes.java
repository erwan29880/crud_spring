package com.erwan.demo6.entities;

import jakarta.persistence.*;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter 
@Setter
@Builder
@Entity
@Table(name = "commandes")
public class Commandes {
    
    @Id 
    @Column(name = "commande_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "commande_date")
    private Date commandDate;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "consommateur_id", updatable = false, insertable = false)
    Consommateurs consommateurs;

    public Commandes(String date) {
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (Exception e) {}
        this.commandDate = date1;
    }


    @ManyToMany(
        cascade = CascadeType.PERSIST, fetch = FetchType.EAGER
    )
    @JoinTable(
        name = "commandes_produits",
        joinColumns = @JoinColumn(name = "commande_id", updatable = false, insertable = false),
        inverseJoinColumns = @JoinColumn(name = "produit_id", updatable = false, insertable = false)
    )
    private List<Produits> produits;


    @Override
    public String toString() {
        return "commande";
    }
}
