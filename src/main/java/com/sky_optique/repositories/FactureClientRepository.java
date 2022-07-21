package com.sky_optique.repositories;

import java.util.List;

import com.sky_optique.entities.Proforma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.sky_optique.entities.FactureClient;
import org.springframework.data.repository.query.Param;

public interface FactureClientRepository extends CrudRepository<FactureClient, Long>{

    @Query("select u from FactureClient u where u.bordereau = null and u.couvertures.size > 0")
    public List<FactureClient> findAllByBordereauIsNullAndCouvertures();

    //public List<FactureClient> findAllByBordereauIsNullAndCouvertures();
    public List<FactureClient> findAllByBordereauIsNotNull();

    @Query("select m from FactureClient m join m.patient p where UPPER(CONCAT(p.nom, p.prenom)) like :pa")
    public Page<FactureClient> findByPatient(@Param("pa")String mc, Pageable pageable);

    @Query("select m from FactureClient m join m.patient p where exists (select c from Couverture c join c.assurance a where a.id =:as) and UPPER(CONCAT(p.nom, p.prenom)) like :pa")
    public Page<FactureClient> findByPatientWithAssurance(@Param("as")Long assurance, @Param("pa")String mc, Pageable pageable);


    @Query("select m from FactureClient m join m.patient p where exists (select c from Couverture c join c.entreprise e where  e.id =:en) and UPPER(CONCAT(p.nom, p.prenom)) like :pa")
    public Page<FactureClient> findByPatientWithEntreprise(@Param("en")Long entreprise, @Param("pa")String mc, Pageable pageable);

    @Query("select m from FactureClient m join m.patient p where exists (select c from Couverture c join c.assurance a join c.entreprise e where a.id =:as and e.id =:en) and UPPER(CONCAT(p.nom, p.prenom)) like :pa")
    public Page<FactureClient> findByPatientWithAssuranceAndEntreprise(@Param("as")Long assurance, @Param("en")Long entreprise, @Param("pa")String mc, Pageable pageable);



}
