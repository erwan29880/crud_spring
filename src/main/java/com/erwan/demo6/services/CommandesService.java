package com.erwan.demo6.services;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erwan.demo6.entities.*;
import com.erwan.demo6.repos.CommandesRepo;

/**
 * classe de service pour tester par print console
 * permet d'obtenir les requêtes sql JPA, là ou les tests ne les montrent pas
 * le rest controller appelle les services pour les tests console
 */
@Service
public class CommandesService {

    @Autowired 
	private CommandesRepo repo;

    public List<Commandes> findAll(){
        return repo.findAll();
    }

    public Optional<Commandes> findById(Long id) {
        return repo.findById(id);
    }


    public Commandes save() {
        // TODO : implémenter selon commandesTest
        Consommateurs consommateur = new Consommateurs();
        consommateur.setConsommateurId(0L);
        consommateur.setNom("nom2");
        consommateur.setPrenom("prenom2");
        consommateur.setEmail("email2@truc.com");
        consommateur.setMobile(1234567891);

        List<Produits> produits = new ArrayList<Produits>();
        Produits produit = new Produits();
        produit.setProduit("toffu");
        produits.add(produit);
        produit = new Produits();
        produit.setProduit("oeuf"); 
        produits.add(produit);

        String sDate1="2023-02-02";
        Date date1 = null; 
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1); 
        } catch (Exception e) {
            System.out.println("bof");
        }
        
        Commandes commande = new Commandes();
        commande.setConsommateurs(consommateur);
        commande.setCommandId(0L);
        commande.setProduits(produits);
        commande.setCommandDate(date1);

        return repo.save(commande);
    }

}
