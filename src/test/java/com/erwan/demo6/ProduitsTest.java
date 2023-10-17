package com.erwan.demo6;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals("oeuf", prods.get(0).getProduit());
    }
}
