package com.sky_optique.entities;

import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_entite")
@DiscriminatorValue("ENTREPRISE")
public class Compagni extends AbstractEntity {

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
    
    protected String niu;
    protected String rccm;
    protected String type;

    @JsonIgnore
    @OneToMany(mappedBy = "entreprise", fetch = FetchType.LAZY)
    private Collection<Personne> personnes;
     
    @JsonIgnore
    @OneToMany(mappedBy = "entreprise", fetch = FetchType.LAZY)
    private Collection<Couverture> couverturesEntreprise;

    
}
