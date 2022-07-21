package com.sky_optique.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_facture")
public class Facture extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(unique = true)
	private String numero;


    @OneToMany(mappedBy = "facture", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    protected Collection<Vente> ventes;   
    
    @ManyToOne
    @JoinColumn(name = "id_patient", nullable = false)
    private Patient patient;
     
    @ManyToOne
    @JoinColumn(name = "id_prescription")
    private Prescription prescription;

    @OneToMany(mappedBy = "facture", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    private Collection<Couverture> couvertures;
    
    @Column(name="type_facture", insertable=false, updatable=false)
    private String discriminator;

}
