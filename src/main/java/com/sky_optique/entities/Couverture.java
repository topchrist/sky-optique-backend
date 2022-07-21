package com.sky_optique.entities;

import lombok.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
public class Couverture extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Temporal(TemporalType.DATE)
    private Date dateDocument;
    private String numeroDocument;
    private String relation;
    private Double couvertureVerre;
    private Double couvertureMonture;
    private BigDecimal priseEnCharge;
    private BigDecimal franchise;
    protected String NumeroBcp;

    @ManyToOne
    @JoinColumn(name = "id_assurePrincipal", nullable = false)
    private Personne assurePrincipal;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "id_facture", nullable = false)
    private Facture facture;
    
    @ManyToOne
    @JoinColumn(name = "id_assurance", nullable = false)
    private Assurance assurance;

    @ManyToOne
    @JoinColumn(name = "id_entreprise")
    private Compagni entreprise;

}
