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

    /**
     * toutes les commandes, avec consommateur et produits associés
     * @return la liste des commandes dans un objet CommandeModele
     */
    @GetMapping("/commande")
    public List<CommandesModele> comm() {
        return service.findAll();
    } 

    /**
     * enregistrer une commande
     * avec éventuellement un nouveau consommateur
     * et la liste des produits
     * @param cm un objet CommandeModele
     * @return un objet commandeModele avec l'id de la commande
     */ 
    @CrossOrigin
    @PostMapping("/commande")
    public CommandesModele postt(@RequestBody CommandesModele cm) {
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
