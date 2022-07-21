package com.sky_optique.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@DiscriminatorValue("CLIENT")
public class Patient extends Personne {

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Facture> factures;

}
