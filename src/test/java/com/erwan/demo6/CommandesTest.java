package com.erwan.demo6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.persistence.Tuple;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

import com.erwan.demo6.repos.*;
import com.erwan.demo6.entities.*;

@SpringBootTest
public class CommandesTest {
    
    @Autowired 
    private CommandesRepo commandesRepo;

    @Autowired 
    private ConsommateursRepo consommateurRepo;

    @Autowired 
    private ProduitsRepo produitRepo;

    @Test 
    public void getAll() {
        List<Commandes> comm = commandesRepo.findAll();
        assertEquals("nom1", comm.get(0).getConsommateurs().getNom());
        Produits prod = comm.get(0).getProduits().get(0);
        assertEquals("oeuf", prod.getProduit());
    }

   
    @Test void insert() {
        Long id = 0L;    
        List<Long> produitsExistingIndices = new ArrayList<Long>();
        List<Long> produitsNonExistingIndices = new ArrayList<Long>();
        Date date1 = null; 

        try {date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02"); } catch (Exception e) {}

        // gestion consommateur --> existe déjà ou non (le mail est unique)
        Consommateurs c = Consommateurs.builder()
                            .nom("nom3")
                            .prenom("prenom3")
                            .email("email3@truc.com")
                            .mobile(1234567895)
                            .build();
        if (consommateurRepo.existsByEmail(c.getEmail())) {
            id  = consommateurRepo.findIdByEmail(c.getEmail());
        } else {
            Consommateurs cons = consommateurRepo.save(c);
            id = cons.getConsommateurId();
        }
        
        
        // gestion produits 
        List<Produits> produits = Arrays.asList(new Produits[]{new Produits("riz au lait"), new Produits("gateau"), new Produits("oeuf")});
        produits.forEach(prod -> {
            if (produitRepo.existsByProduit(prod.getProduit())) {
                produitsExistingIndices.add(produitRepo.findByProduit(prod.getProduit()).getProduitId());
            }
            else {
                Produits newProduct = produitRepo.save(prod);
                produitsNonExistingIndices.add(newProduct.getProduitId());
            }
        });

        Commandes commande = Commandes.builder()
                                .commandDate(date1)
                                .build();
        Commandes comma = commandesRepo.save(commande);
        commandesRepo.updateCommande(id, comma.getId());

        produitsExistingIndices.forEach(ind -> commandesRepo.insertCommandeProduit(comma.getId(), ind));
        produitsNonExistingIndices.forEach(ind -> commandesRepo.insertCommandeProduit(comma.getId(), ind));
    }


    @Test 
    public void updateCommande() {
        commandesRepo.updateCommande(1L, 10L);
    }

    @Test
    public void insertCommandeProduit() {
        commandesRepo.insertCommandeProduit(1L, 1L);
    }

    @Test 
    public void deleteCommande() {
        Commandes command = commandesRepo.findByLastId(); 
        Long idDelete = command.getId(); 
        long co = commandesRepo.countByConsommateurid(command.getConsommateurs().getConsommateurId());
        
        // commandes
        Optional<Commandes> c = commandesRepo.findById(idDelete);
        if (c.isPresent()) {
            commandesRepo.delete(c.get());
        }
        assertTrue(commandesRepo.findById(idDelete).isEmpty());
        
        // consommateurs
        if (co == 1L) {
            consommateurRepo.deleteByEmail(command.getConsommateurs().getEmail());
        }
        
        // commandes_produits
        commandesRepo.deleteCommandeProduit(idDelete);
        List<Tuple> cp = commandesRepo.findCommandeProduitByCommandeId(idDelete);
        assertEquals(0, cp.size());
    }

    @Test 
    public void findCommandeProduitByCommandeId() {
        List<Tuple> cp = commandesRepo.findCommandeProduitByCommandeId(1L);
        Tuple c = cp.get(0);
        assertEquals(1L, c.get(0));
        assertEquals(1L, c.get(1));
    }

    @Test 
    public void countConsommateurs() {
        Long idDelete = commandesRepo.findByLastId().getConsommateurs().getConsommateurId();
        long co = commandesRepo.countByConsommateurid(idDelete);
        assertTrue(co >= 1L);
    }

    @Test 
    public void existsById() {
        boolean check = commandesRepo.existsById(1L);
        assertTrue(check);
    }

    @Test 
    public void findFirstByOrderByIdDesc() {
        Commandes comm = commandesRepo.findFirstByOrderByIdDesc();
        assertNotNull(comm.getProduits());
        assertNotNull(comm.getConsommateurs().getNom());
    }
}
