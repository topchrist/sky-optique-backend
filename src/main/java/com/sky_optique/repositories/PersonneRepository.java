package com.sky_optique.repositories;

import org.springframework.data.repository.CrudRepository;
import com.sky_optique.entities.Personne;
 
public interface PersonneRepository extends CrudRepository<Personne, Long>{
}
