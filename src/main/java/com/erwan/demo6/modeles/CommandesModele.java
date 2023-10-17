package com.erwan.demo6.modeles;

import com.erwan.demo6.entities.Produits;
import java.util.List; 
import java.util.Date;
import lombok.*;

@NoArgsConstructor 
@AllArgsConstructor 
@Getter
@Setter
public class CommandesModele {
    
    private Long commandeId;
    private Date date;
    private List<String> produits;
    private String nom;
    private String prenom;
    private String email;
    private int mobile;
}
