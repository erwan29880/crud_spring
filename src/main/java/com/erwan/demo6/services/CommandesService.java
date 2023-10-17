package com.erwan.demo6.services;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erwan.demo6.entities.Commandes;
import com.erwan.demo6.entities.Consommateurs;
import com.erwan.demo6.entities.Produits;
import com.erwan.demo6.repos.CommandesRepo;
import com.erwan.demo6.repos.ConsommateursRepo;

import jakarta.persistence.Tuple;


@Service
public class CommandesService {

    @Autowired 
	private CommandesRepo repo;

    @Autowired
    private ConsommateursRepo crepo;

    public List<Commandes> findAll(){
        return repo.findAll();
    }

    public boolean jjjjj() {
        Commandes commande = repo.findByLastId();
        Long idDelete = commande.getConsommateurs().getConsommateurId();
        long co = repo.countByConsommateurid(idDelete);
        if (co == 1L) {
            crepo.deleteByEmail(commande.getConsommateurs().getEmail());
        }
        return true;
    }


    public List<Tuple> findCommandeProduitByCommandeId() {
        List<Tuple> tup =  repo.findCommandeProduitByCommandeId(1L);
        tup.forEach(c -> {
            System.out.println(c.get(1));
        });
        return tup;
    }


    public Optional<Commandes> findById(Long id) {
        return repo.findById(id);
    }

    public Commandes save() {
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
