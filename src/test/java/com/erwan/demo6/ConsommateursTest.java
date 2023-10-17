package com.erwan.demo6;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;
import com.erwan.demo6.entities.Consommateurs;
import com.erwan.demo6.repos.ConsommateursRepo;
import java.util.List;

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
        Long id = repo.deleteByNomAndPrenom("nom3", "prenom3");
        assertNotEquals(0L, id);
    }

    @Test 
    public void deleteByemail () {
        Long id = repo.deleteByEmail("fakeemail@com");
        assertEquals(0L, id);
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
