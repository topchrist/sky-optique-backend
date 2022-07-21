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
@DiscriminatorValue("L")
public class Lentille extends Produit {
 
	private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private Double sphere;
    @Column(nullable = false)
    private Double cylindre;
    private Double axe;
    private Double addition;
    

    @ManyToOne
    @JoinColumn(name = "id_catalogue")
    private Catalogue catalogue;
    
    public Lentille(String libelle, double sphere, double cylindre, double axe, double addition) {
    	super(libelle);
    	this.sphere = sphere;
        this.cylindre = cylindre;
        this.axe = axe;
        this.addition = addition;
      
    }
    
    
    
        

}
