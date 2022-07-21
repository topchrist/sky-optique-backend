package com.sky_optique.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.sky_optique.entities.Marque;

 
public interface MarqueRepository extends CrudRepository<Marque, Long>{
    @Query("select m from Marque m where UPPER(m.nom) like :x")
    public Page<Marque> chercher(@Param("x")String mc, Pageable pageable);
}
