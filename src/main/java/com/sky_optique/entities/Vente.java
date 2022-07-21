package com.sky_optique.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author chris
 */
@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Vente extends AbstractEntity{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(nullable = false)
    private String libelle;
    @Column(nullable = false)
    private BigDecimal pu;
    @Column(nullable = false)
    private int qte;
    //@Column(nullable = false)
    private double tva; //en paourcentage
    private double autreTaxe; //en paourcentage
    @Column(nullable = false)
    private double remise;
    @Column(nullable = false)
    private BigDecimal montant;
    @Column(nullable = false)
    private BigDecimal total;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "id_facture")
    private Facture facture;
    
    @ManyToOne
    @JoinColumn(name = "id_stock", nullable = false)
    private Stock stock;
    


    
    
}
