package com.sky_optique.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.rest.core.annotation.RestResource;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
public class BonLivraison extends AbstractEntity{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(nullable = false)  
    private String reference;

    //@Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateLivraison;

    @ManyToOne
    @JoinColumn(name = "id_fournisseur")
    //@RestResource(exported = false)
    private Fournisseur fournisseur;

    //@JsonIgnore
    @OneToMany(mappedBy = "bonLivraison", fetch = FetchType.LAZY)
    private Collection<Livraison> livraisons;



}
