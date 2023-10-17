package com.erwan.demo6.modeles;

import java.util.List; 
import java.util.Date;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * classe modèle pour renvoi des données au front 
 * sans transférer le résultat de la requête dans le modèle une boucle infinie est créée
 */
@NoArgsConstructor 
@AllArgsConstructor 
@Getter
@Setter
public class CommandesModele {
    
    private Long commandeId;
    private Date date;
    private List<String> produits;
    private String nom;
    private String prenom;
    private String email;
    private int mobile;
}
