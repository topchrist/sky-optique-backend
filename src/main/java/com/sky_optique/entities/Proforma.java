package com.sky_optique.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.*;


@ToString
@Entity
@Data
@DiscriminatorValue("Proformat")
@EqualsAndHashCode(callSuper=false)
public class Proforma extends Facture {
	
private static final long serialVersionUID = 1L;

}
