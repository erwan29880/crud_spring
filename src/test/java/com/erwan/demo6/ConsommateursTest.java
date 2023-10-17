package com.erwan.demo6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

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
        Consommateurs c = Consommateurs.builder()
                            .nom("nom3")
                            .prenom("prenom3")
                            .email("email3@truc.com")
                            .mobile(1234567895)
                            .build();
        assertDoesNotThrow(() -> repo.save(c));
    }

    @Test 
    public void deleteByNomAndPrenom () {
        repo.deleteByNomAndPrenom("nom3", "prenom3");
    }

    @Test 
    public void deleteByemail () {
        Consommateurs c = Consommateurs.builder()
                            .nom("nom4")
                            .prenom("prenom4")
                            .email("fakeemail@com")
                            .mobile(1234567895)
                            .build();
        repo.save(c);
        repo.deleteByEmail("fakeemail@com");
    }

    @Test 
    public void existsByEmail() {
        String email = "email1@truc.com";
        boolean check = repo.existsByEmail(email);
        assertTrue(check);
    }

    @Test 
    public void findIdByEmail() {
        Long id = repo.findIdByEmail("email1@truc.com");
        assertEquals(1L, id);
    }
}
