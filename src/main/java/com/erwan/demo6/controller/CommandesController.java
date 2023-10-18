package com.erwan.demo6.controller;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.erwan.demo6.entities.*;
import com.erwan.demo6.modeles.CommandesModele;
import com.erwan.demo6.services.CommandesService;



@RestController
@RequestMapping("/bla")
public class CommandesController {
    
    @Autowired
    private CommandesService service;

    @GetMapping("/")
    public List<CommandesModele> comm() {
        return service.findAll();
    } 

    // test save commande 
    @CrossOrigin
    @PostMapping("/p")
    public CommandesModele postt(@RequestBody CommandesModele cm) {
        return service.saveCommande(cm);
    }


    /**
     * get by id 
     * obligation de passer par un modèle, il n'est pas possible de renvoyer directement l'objet requêté
     * problème de boucle infinie
     * @return
     */
    @GetMapping("/{id}") 
    public CommandesModele findById(@PathVariable final Long id) {
        return service.findById(id);
    }
}
