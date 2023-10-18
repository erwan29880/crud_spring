package com.erwan.demo6.services;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erwan.demo6.entities.*;
import com.erwan.demo6.modeles.CommandesModele;
import com.erwan.demo6.repos.CommandesRepo;
import com.erwan.demo6.repos.ConsommateursRepo;
import com.erwan.demo6.repos.ProduitsRepo;

/**
 * classe de service pour tester par print console
 * permet d'obtenir les requêtes sql JPA, là ou les tests ne les montrent pas
 * le rest controller appelle les services pour les tests console
 */
@Service
public class CommandesService {

    @Autowired 
	private CommandesRepo commandesRepo;

    @Autowired 
    private ProduitsRepo produitRepo;

    @Autowired 
    private ConsommateursRepo consommateurRepo;



    public List<CommandesModele> findAll(){
        List<CommandesModele> commandesModele = new ArrayList<CommandesModele>();
        List<Commandes> commandes = commandesRepo.findAll();

        commandes.forEach(c -> {
            List<String> produits = new ArrayList<String>();
            c.getProduits().forEach(p -> produits.add(p.getProduit()));

            commandesModele.add(new CommandesModele(
                c.getCommandId(),
                c.getCommandDate(),
                produits,
                c.getConsommateurs().getNom(),
                c.getConsommateurs().getPrenom(),
                c.getConsommateurs().getEmail(),
                c.getConsommateurs().getMobile()
            ));
        });
        return commandesModele;
    }

    public CommandesModele findById(Long id) {
        CommandesModele commandes = new CommandesModele();
        boolean check = commandesRepo.existsById(id);

        if (check) {
            Optional<Commandes> co = commandesRepo.findById(id);
            
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
        return commandes;
    }


    public CommandesModele saveCommande(CommandesModele cm) {
        Long id = 0L;    
        List<Long> produitsExistingIndices = new ArrayList<Long>();
        List<Long> produitsNonExistingIndices = new ArrayList<Long>();

        // gestion consommateur --> existe déjà ou non (le mail est unique)
        Consommateurs c = Consommateurs.builder()
                            .nom(cm.getNom())
                            .prenom(cm.getPrenom())
                            .email(cm.getEmail())
                            .mobile(cm.getMobile())
                            .build();
        if (consommateurRepo.existsByEmail(c.getEmail())) {
            id  = consommateurRepo.findIdByEmail(c.getEmail());
        } else {
            Consommateurs cons = consommateurRepo.save(c);
            id = cons.getConsommateurId();
        }
        
        
        // gestion produits -> ajouter nouveau produit ou non si il existe
        // conserver les résultats dans des listes pour remplir plus tard la table de jointure commandes produits
        cm.getProduits().forEach(prod -> {
            if (produitRepo.existsByProduit(prod)) {
                produitsExistingIndices.add(produitRepo.findByProduit(prod).getProduitId());
            }
            else {
                Produits newProduct = produitRepo.save(new Produits(0L, prod, null));
                produitsNonExistingIndices.add(newProduct.getProduitId());
            }
        });

        // insertion commande
        Commandes commande = new Commandes(cm.getDate());
        Commandes comma = commandesRepo.save(commande);
        commandesRepo.updateCommande(id, comma.getCommandId());

        // insertion dans la table de jointure des produits
        produitsExistingIndices.forEach(ind -> commandesRepo.insertCommandeProduit(comma.getCommandId(), ind));
        produitsNonExistingIndices.forEach(ind -> commandesRepo.insertCommandeProduit(comma.getCommandId(), ind));

        // récupérer la dernière commande pour récupérer les produits
        Commandes commandeSaved = commandesRepo.findByLastId();

        // formatter les produits selon le modèle commandesModele
        List<String> produitsSaved = new ArrayList<String>();
        commandeSaved.getProduits().forEach(p ->  produitsSaved.add(p.getProduit()));
    
        return new CommandesModele(
            commandeSaved.getCommandId(),
            commandeSaved.getCommandDate(),
            produitsSaved,
            commandeSaved.getConsommateurs().getNom(),
            commandeSaved.getConsommateurs().getPrenom(),
            commandeSaved.getConsommateurs().getEmail(),
            commandeSaved.getConsommateurs().getMobile()
        );
    }

}
