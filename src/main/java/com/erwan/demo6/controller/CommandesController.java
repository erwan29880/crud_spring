package com.erwan.demo6.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.erwan.demo6.modeles.*;
import com.erwan.demo6.services.CommandesService;



@RestController
public class CommandesController {
    
    @Autowired
    private CommandesService service;

    @GetMapping("/commande")
    public List<CommandesModele> comm() {
        return service.findAll();
    } 

    // test save commande 
    @CrossOrigin
    @PostMapping("/commande")
    public CommandesModele postt(@RequestBody CommandesModele cm) {
        System.out.println(cm.getProduits());
        return service.saveCommande(cm);
    }

    /**
     * get by id 
     * obligation de passer par un modèle, il n'est pas possible de renvoyer directement l'objet requêté
     * problème de boucle infinie
     * @return
     */
    @GetMapping("/commande/{id}") 
    public CommandesModele findById(@PathVariable final Long id) {
        return service.findById(id);
    }
}
