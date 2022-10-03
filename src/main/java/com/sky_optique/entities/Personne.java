package com.sky_optique.entities;

import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_entite")
@DiscriminatorValue("PERSONNE")
public class Personne extends AbstractEntity{
	
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(nullable = false)
    protected String nom;
    protected String email;
    protected String adresse;
    protected String tel1;
    protected String tel2;
    protected String bp;

    protected String prenom;
    protected LocalDate dateNaiss;
    protected String civilite;

    @ManyToOne
    @JoinColumn(name = "id_entreprise", nullable = true)
    protected Compagni entreprise;
    
    @OneToMany(mappedBy = "assurePrincipal", fetch = FetchType.LAZY)
    @JsonIgnore
    protected Collection<Couverture> couvertures;

    @Column(name="type_entite", insertable=false, updatable=false)
    private String discriminator;


}
