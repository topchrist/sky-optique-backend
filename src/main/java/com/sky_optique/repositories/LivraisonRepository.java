package com.sky_optique.repositories;

import org.springframework.data.repository.CrudRepository;
import com.sky_optique.entities.Livraison;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
 
public interface LivraisonRepository extends CrudRepository<Livraison, Integer>{

}
