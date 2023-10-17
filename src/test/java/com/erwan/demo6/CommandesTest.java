package com.erwan.demo6;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.text.SimpleDateFormat;
import java.util.*;
import com.erwan.demo6.entities.Consommateurs;
import com.erwan.demo6.entities.Commandes;
import com.erwan.demo6.repos.CommandesRepo;
import com.erwan.demo6.entities.Produits;

@SpringBootTest
public class CommandesTest {
    
    @Autowired 
    private CommandesRepo repo;

    @Test 
    public void getAll() {
        List<Commandes> comm = repo.findAll();
        assertEquals("nom1", comm.get(0).getConsommateurs().getNom());
        Produits prod = comm.get(0).getProduits().get(0);
        assertEquals("oeuf", prod.getProduit());
    }

    @Test void insert() {
        Consommateurs consommateur = new Consommateurs();
        consommateur.setNom("nom3");
        consommateur.setPrenom("prenom3");
        consommateur.setEmail("exemple@com");
        consommateur.setMobile(123456987);

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

        repo.save(commande);
    }


}
