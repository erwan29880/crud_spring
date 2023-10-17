package com.erwan.demo6.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "consommateurs")
public class Consommateurs {
    
    @Id 
    @Column(name = "consommateur_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consommateurId;
 
    private String prenom;
    private String nom;
    private String email;

    @OneToMany(mappedBy = "consommateurs")
    private List<Commandes> commandes;

    @Column(length=10)
    private int mobile;

    @Override
    public String toString() {
        return "consommateurs";
    }
}
