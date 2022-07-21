package com.sky_optique.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="type_produit")
public class Produit extends AbstractEntity{
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String libelle;

    @OneToMany(mappedBy = "produit", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Commande> commandes;
    
    @JsonIgnore
    @OneToMany(mappedBy = "produit", fetch = FetchType.LAZY)
    private Collection<Livraison> livraisons;
    
    @JsonIgnore
    @OneToOne( mappedBy = "produit")
    private Stock stock;
   
    @Column(name="type_produit", insertable=false, updatable=false)
    private String discriminator;

    public Produit(String libelle) {
        this.libelle = libelle;
    }



}
