package com.sky_optique.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Commande extends AbstractEntity{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    //@Column(nullable = false, unique = true)
    private String reference;
    @Column(nullable = false)
    private int qte;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bonCommande")
    private BonCommande bonCommande;
    
    @ManyToOne
    @JoinColumn(name = "id_produit")
    private Produit produit;

}
