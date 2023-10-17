package com.erwan.demo6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import com.erwan.demo6.repos.ProduitsRepo;
import com.erwan.demo6.entities.Produits;


@SpringBootTest 
public class ProduitsTest {
    
    @Autowired 
    private ProduitsRepo repo;

    @Test 
    public void findAll() {
        List<Produits> prods = repo.findAll();
        List<String> st = new ArrayList<String>();
        prods.forEach(s -> st.add(s.getProduit()));
        boolean contains = st.contains("oeuf");
        assertTrue(contains);
    }

    @Test
    public void findByProduit() {
        Produits produit = repo.findByProduit("oeuf");
        assertEquals(1L, produit.getProduitId());
    }

    @Test 
    public void saveProduit() {
        Produits toSave = new Produits("orge");
        repo.save(toSave);
    }

    @Test
    public void deleteProduit() {
        Produits produit = repo.findByProduit("orge");
        repo.delete(produit);
    }

    @Test
    public void existsByProduit() {
        boolean bo = repo.existsByProduit("oeuf");
        assertTrue(bo);
    }
}
