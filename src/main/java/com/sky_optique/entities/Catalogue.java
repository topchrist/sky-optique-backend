package com.sky_optique.entities;


import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data  
@NoArgsConstructor @AllArgsConstructor
public class Catalogue {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
	@Column(nullable = false)
	private String nom;
	
	@JsonIgnore
    @OneToMany(mappedBy = "catalogue", fetch = FetchType.LAZY)
    private Collection<Lentille> lentilles;

}
