package com.erwan.demo6;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.*;
import com.erwan.demo6.entities.Consommateurs;
import com.erwan.demo6.entities.Commandes;
import com.erwan.demo6.repos.CommandesRepo;
import com.erwan.demo6.repos.ConsommateursRepo;
import com.erwan.demo6.entities.Produits;

@SpringBootTest
public class CommandesTest {
    
    @Autowired 
    private CommandesRepo commandesRepo;

    @Autowired 
    private ConsommateursRepo consommateurRepo;


    @Test 
    public void getAll() {
        List<Commandes> comm = commandesRepo.findAll();
        assertEquals("nom1", comm.get(0).getConsommateurs().getNom());
        Produits prod = comm.get(0).getProduits().get(0);
        assertEquals("oeuf", prod.getProduit());
    }

    @Test 
    public void getByLastId() {
        Commandes comm = commandesRepo.findByLastId();
        boolean bo = (comm.getCommandId() > 1L);
        assertTrue(bo);
    }

    @Test void insert() {
        Long id = 0L;    
        Date date1 = null; 
        try {date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02"); }
        catch (Exception e) {}

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
        
        
        Commandes commande = Commandes.builder()
                                .commandDate(date1)
                                .build();
        Commandes comma = commandesRepo.save(commande);
        commandesRepo.updateCommande(id, comma.getCommandId());
    }


    @Test 
    public void updateCommande() {
        commandesRepo.updateCommande(1L, 10L);
    }

}
