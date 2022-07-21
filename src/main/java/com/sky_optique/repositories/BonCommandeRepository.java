package com.sky_optique.repositories;

import org.springframework.data.repository.CrudRepository;
import com.sky_optique.entities.BonCommande;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

public interface BonCommandeRepository extends CrudRepository<BonCommande, Long>{

}
