package com.sky_optique.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.sky_optique.entities.Proforma;
import org.springframework.data.repository.query.Param;

public interface ProformaRepository extends CrudRepository<Proforma, Long>{

    @Query("select m from Proforma m join m.patient p where UPPER(CONCAT(p.nom, p.prenom)) like :pa")
    public Page<Proforma> findByPatient(@Param("pa")String mc, Pageable pageable);

    @Query("select m from Proforma m join m.patient p where exists (select c from Couverture c join c.assurance a where a.id =:as) and UPPER(CONCAT(p.nom, p.prenom)) like :pa")
    public Page<Proforma> findByPatientWithAssurance(@Param("as")Long assurance, @Param("pa")String mc, Pageable pageable);


    @Query("select m from Proforma m join m.patient p where exists (select c from Couverture c join c.entreprise e where  e.id =:en) and UPPER(CONCAT(p.nom, p.prenom)) like :pa")
    public Page<Proforma> findByPatientWithEntreprise(@Param("en")Long entreprise, @Param("pa")String mc, Pageable pageable);

    @Query("select m from Proforma m join m.patient p where exists (select c from Couverture c join c.assurance a join c.entreprise e where a.id =:as and e.id =:en) and UPPER(CONCAT(p.nom, p.prenom)) like :pa")
    public Page<Proforma> findByPatientWithAssuranceAndEntreprise(@Param("as")Long assurance, @Param("en")Long entreprise, @Param("pa")String mc, Pageable pageable);



}
