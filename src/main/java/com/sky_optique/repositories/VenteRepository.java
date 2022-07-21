package com.sky_optique.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sky_optique.entities.Produit;
import com.sky_optique.entities.Vente;

public interface VenteRepository extends CrudRepository<Vente, Long>{
	public List<Vente> findVentesByFacture_Id(long id);
}
