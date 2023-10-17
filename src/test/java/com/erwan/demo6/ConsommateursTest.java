package com.erwan.demo6;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.dao.DataIntegrityViolationException;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.erwan.demo6.entities.Consommateurs;
import com.erwan.demo6.repos.ConsommateursRepo;

@SpringBootTest
public class ConsommateursTest {

    @Autowired 
    private ConsommateursRepo repo;

    @Test 
    public void findAll() {
        Iterable<Consommateurs> c = repo.findAll();
        assertNotNull(c);
        for (Consommateurs s: c) {
            assertEquals("nom1",  s.getNom()); 
            break;
        }
    }

    @Test 
    public void insert() {
        Consommateurs c = new Consommateurs();
        c.setNom("nom3");
        c.setPrenom("prenom3");
        c.setEmail("email3@truc.com");
        c.setMobile(1234567895);
        assertDoesNotThrow(() -> repo.save(c));
    }

    @Test 
    public void deleteByNomAndPrenom () {
        Long id = repo.deleteByNomAndPrenom("nom3", "prenom3");
        assertNotEquals(0L, id);
    }
}
