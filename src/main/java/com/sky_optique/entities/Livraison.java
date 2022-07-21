package com.sky_optique.entities;

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
import org.springframework.data.rest.core.annotation.RestResource;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Livraison extends AbstractEntity{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    //@Column(nullable = false, unique = true)
    private String reference;

    @Column(nullable = false)
    private BigDecimal prixAchat ;
    @Column(nullable = false)
    private int qte;
    //@Column(nullable = false)
    //private BigDecimal prixVente;

    @ManyToOne
    @JoinColumn(name = "id_produit")
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "id_bonLivraison")
    private BonLivraison bonLivraison;

}
