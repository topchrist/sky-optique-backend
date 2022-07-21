package com.sky_optique.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.sky_optique.entities.Compagni;


public interface CompagniRepository extends CrudRepository<Compagni, Long>{

}
