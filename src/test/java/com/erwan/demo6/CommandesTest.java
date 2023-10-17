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
import com.erwan.demo6.entities.Produits;

@SpringBootTest
public class CommandesTest {
    
    @Autowired 
    private CommandesRepo commandesRepo;

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
       

        String sDate1="2023-02-02";
        Date date1 = null; 
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1); 
        } catch (Exception e) {
            System.out.println("problème de date");
        }
        
        Commandes commande = new Commandes();
        commande.setCommandId(0L);
        commande.setCommandDate(date1);
        commandesRepo.save(commande);
    }


}
