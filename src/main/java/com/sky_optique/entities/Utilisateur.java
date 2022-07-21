package com.sky_optique.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Utilisateur extends AbstractEntity {
	 
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(nullable = false)
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String tel1;
    private String civilite;
    @Column(nullable = false, unique = true)
    private String pseudo;
    @Column(nullable = false)
    private String password;
    /*
    @ManyToOne
    @JoinColumn(name = "id_agence")
    private Agence agence;
    */
    //@Column(nullable = false)
    private boolean statut;

    public Utilisateur(String nom, String pseudo, String password) {
        this.nom = nom;
        this.pseudo = pseudo;
        this.password = password;
    }
    
    
    
}
