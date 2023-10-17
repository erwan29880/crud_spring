package com.erwan.demo6.controller;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.erwan.demo6.entities.*;
import com.erwan.demo6.modeles.CommandesModele;
import com.erwan.demo6.services.CommandesService;



@RestController
@RequestMapping("/bla")
public class CommandesController {
    
    @Autowired
    private CommandesService service;

    @GetMapping("/")
    public List<Commandes> comm() {
        // TODO implémenter la logique dans le service -> boucle infinie voir findById
        return service.findAll();
    } 

    // test save commande 
    @GetMapping("/p")
    // TODO implémenter la méthode dans le service : voir commandesTest
    public Commandes postt() {
        return service.save();
    }


    /**
     * get by id 
     * obligation de passer par un modèle, il n'est pas possible de renvoyer directement l'objet requêté
     * problème de boucle infinie
     * @return
     */
    @GetMapping("/1") 
    public CommandesModele findById() {
        // TODO implémenter la logique dans le service
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
