package com.erwan.demo6.modeles;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * classe modèle pour renvoi des données au front 
 * sans transférer le résultat de la requête dans le modèle une boucle infinie est créée
 */
@Getter
@Setter
public class CommandesModele {
    
    private Long commandeId;
    private String date;
    private List<String> produits;
    private String nom;
    private String prenom;
    private String email;
    private int mobile;

    public CommandesModele(Long id, String date, List<String> produits, String nom, String prenom, String email,int mobile) {
        this.commandeId = id;
        this.date = date;
        this.produits = produits;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mobile = mobile;
    }

    public CommandesModele(Long id, Date date, List<String> produits, String nom, String prenom, String email,int mobile) {
        String date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").format(date); 
        } catch (Exception e) {}

        this.commandeId = id;
        this.date = date1;
        this.produits = produits;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mobile = mobile;
    }

    public CommandesModele() {}

    public void setDate(Date date) {
        String date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").format(date); 
        } catch (Exception e) {}
        this.date = date1;
    }
}
