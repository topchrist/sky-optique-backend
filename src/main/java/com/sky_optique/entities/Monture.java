package com.sky_optique.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@DiscriminatorValue("M")
public class Monture extends Produit{

    private static final long serialVersionUID = 1L;
    
    @Column(nullable = false)
    private String reference;
	private String modele;
    private String couleur;
    private String genre;
    private String taille;
    private String matiere;
    private String forme;
    private boolean avecBoitier;
	
	@ManyToOne
    @JoinColumn(name = "id_marque")
    private Marque marque;
	
	public Monture(String libelle, String reference, String modele, String couleur, String genre) {
		super(libelle);
		this.reference = reference;
		this.modele = modele;
		this.couleur = couleur;
		this.genre = genre;
	}

	

}
