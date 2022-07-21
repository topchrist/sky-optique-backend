package com.sky_optique.repositories;

import com.sky_optique.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends CrudRepository<Patient, Long> {
    @Query("select m from Patient m where UPPER(CONCAT(m.nom, m.prenom)) like :x")
    public Page<Patient> chercher(@Param("x")String mc, Pageable pageable);
}
