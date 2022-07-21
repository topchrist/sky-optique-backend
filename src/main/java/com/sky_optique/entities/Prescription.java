package com.sky_optique.entities;

import lombok.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Prescription extends AbstractEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eyeVision;
    private LocalDate datePrescription;
    private LocalDate deadline;
    private String port;
    @ManyToOne
    @JoinColumn(name = "id_prescripteur", nullable = false)
    private Prescripteur prescripteur;
    @JsonIgnore
    @OneToMany(mappedBy = "prescription", fetch = FetchType.LAZY)
    private Collection<Facture> factures;

}
