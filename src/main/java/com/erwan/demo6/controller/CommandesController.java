package com.erwan.demo6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.erwan.demo6.entities.*;
import com.erwan.demo6.modeles.CommandesModele;
import com.erwan.demo6.modeles.Fake;

import java.util.*;
import org.springframework.web.bind.annotation.*;
import com.erwan.demo6.services.CommandesService;


@RestController
@RequestMapping("/bla")
public class CommandesController {
    
    @Autowired
    private CommandesService service;

    @GetMapping("/")
    public List<Commandes> comm() {
        return service.findAll();
    } 

    @GetMapping("/p")
    public Commandes postt() {
        return service.save();
    }

    @GetMapping("/1") 
    public CommandesModele findById() {
        CommandesModele commandes = new CommandesModele();
        Optional<Commandes> co = service.findById(1L);
        
        co.ifPresent(c -> {
            List<String> produits = new ArrayList<String>();
            c.getProduits().forEach(p -> {
                produits.add(p.getProduit());
            });

            commandes.setCommandeId(c.getCommandId());
            commandes.setDate(c.getCommandDate());
            commandes.setProduits(produits);
            commandes.setNom(c.getConsommateurs().getNom());
            commandes.setPrenom(c.getConsommateurs().getPrenom());
            commandes.setEmail(c.getConsommateurs().getEmail());
            commandes.setMobile(c.getConsommateurs().getMobile());
            
        });

        return commandes;
    }


}
