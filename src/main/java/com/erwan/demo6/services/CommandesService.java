package com.erwan.demo6.services;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

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

    /**
     * trouver toutes les commandes avec consommateur et produits
     * @return un modèle à envoyer au front
     */
    public List<CommandesModele> findAll(){
        // variables
        List<CommandesModele> commandesModele = new ArrayList<CommandesModele>();
        
        // récupération données
        List<Commandes> commandes = commandesRepo.findAll();

        commandes.forEach(c -> {
            // mettre les produits en liste
            List<String> produits = new ArrayList<String>();
            c.getProduits().forEach(p -> produits.add(p.getProduit()));

            // insérer un objet commandeModèle dans la liste commandeModele
            commandesModele.add(new CommandesModele(
                c.getId(),
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
        // variables
        CommandesModele commandes = new CommandesModele();
        
        // vérification que l'entrée existe
        boolean check = commandesRepo.existsById(id);

        // traitement de l'optional si l'entrée existe
        if (check) {
            Optional<Commandes> co = commandesRepo.findById(id);
            
            co.ifPresent(c -> {
                // mise en liste des produits
                List<String> produits = new ArrayList<String>();
                c.getProduits().forEach(p -> produits.add(p.getProduit()));
    
                commandes.setCommandeId(c.getId());
                commandes.setDate(c.getCommandDate());
                commandes.setProduits(produits);
                commandes.setNom(c.getConsommateurs().getNom());
                commandes.setPrenom(c.getConsommateurs().getPrenom());
                commandes.setEmail(c.getConsommateurs().getEmail());
                commandes.setMobile(c.getConsommateurs().getMobile());
            });
            return commandes;
        }
        // retourner l'objet vide le cas échéant
        return commandes;
    }


    public CommandesModele saveCommande(CommandesModele cm) {
        // variables
        CommandesModele cmToSend = new CommandesModele();
        
        try {
            // variables
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
            cmToSend.setCommandeId(comma.getId());
            commandesRepo.updateCommande(id, comma.getId());
            
            // insertion dans la table de jointure des produits
            if (produitsExistingIndices.size() != 0) {
                produitsExistingIndices.forEach(ind -> commandesRepo.insertCommandeProduit(comma.getId(), ind));
            }
            if (produitsNonExistingIndices.size() != 0) {
                produitsNonExistingIndices.forEach(ind -> commandesRepo.insertCommandeProduit(comma.getId(), ind));            
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // retourner un objet CommandesModele avec uniquement l'id de l'entrée enregistrée dans la table commande
        return cmToSend;
    }


    public boolean deleteById(Long id) {
        boolean check = false;
        Optional<Commandes> c = commandesRepo.findById(id);
        if (c.isPresent()) {
            try {
                commandesRepo.delete(c.get());
                check = true;
            } catch (Exception e) {}
        }
        return check;
    }
}
