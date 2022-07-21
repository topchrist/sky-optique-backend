package com.sky_optique.entities;

import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@DiscriminatorValue("PRESCRIPTEUR")
public class Prescripteur extends Personne {
    protected String titre;
	@JsonIgnore
    @OneToMany(mappedBy = "prescripteur", fetch = FetchType.LAZY)
    private Collection<Prescription> prescriptions;

}
