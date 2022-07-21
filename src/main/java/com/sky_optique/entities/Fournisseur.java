/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky_optique.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Chris
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@DiscriminatorValue("FOURNISSEUR")
public class Fournisseur extends Compagni{
    
	private static final long serialVersionUID = 1L;
	
    @JsonIgnore
    @OneToMany(mappedBy = "fournisseur", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private Collection<BonCommande> bonCommandes;
    
    @JsonIgnore
    @OneToMany(mappedBy = "fournisseur", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private Collection<BonLivraison> bonLivraisons;

    public Fournisseur(String nom, String email, String tel1) {
        this.nom = nom;
        this.email = email;
        this.tel1 = tel1;
    }
    
}
