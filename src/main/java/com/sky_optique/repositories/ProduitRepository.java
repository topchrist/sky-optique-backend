package com.sky_optique.repositories;

import org.springframework.data.repository.CrudRepository;
import com.sky_optique.entities.Produit;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


public interface ProduitRepository extends CrudRepository<Produit, Long> {
    public List<Produit> findProduitsByLibelleContains(String str);
}
