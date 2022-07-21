package com.sky_optique.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
public class Bordereau extends AbstractEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
	
	@Column(unique = true)
	private String numero;
	
	private Date dateDebut;
	private Date dateFin;
	
	@ManyToOne
    @JoinColumn(name = "id_assurance", nullable = false)
    private Compagni assurance;
	
	//@JsonIgnore
    @OneToMany(mappedBy = "bordereau", fetch = FetchType.LAZY)
    private Collection<FactureClient> factureClients;
	

}
