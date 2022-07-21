/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky_optique.controllers;

import com.sky_optique.entities.Lentille;
import com.sky_optique.entities.Produit;
import com.sky_optique.entities.Stock;
import com.sky_optique.repositories.LentilleRepository;
import com.sky_optique.repositories.CatalogueRepository;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chris
 */
@RestController
@RequestMapping(path = "/lentille")
@CrossOrigin("*")
public class LentilleController {
    
    @Autowired
    private LentilleRepository lentilleRepository;
    @Autowired
    private CatalogueRepository catalogueRepository;
	
    @GetMapping(path = "/")
    public @ResponseBody Iterable <Lentille> getAllLentille() {
        return lentilleRepository.findAll();
    }
	
    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Lentille> getLentilleById(@PathVariable long id) {
        return lentilleRepository.findById(id);
    }
	
    @PostMapping(path = "/")
    public @ResponseBody Lentille addLentille(@RequestBody Lentille lentille) {
    	if(lentille.getCatalogue() !=null && lentille.getCatalogue().getId() == null)
    		lentille.setCatalogue(catalogueRepository.save(lentille.getCatalogue()));
    	return lentilleRepository.save(lentille);
    }
	
    @PutMapping(path = "/{id}")
    public @ResponseBody Lentille updateLentille(@PathVariable long id, @RequestBody Lentille lentille) {
        lentille.setId(lentille.getId());
        if(lentille.getCatalogue() !=null && lentille.getCatalogue().getId() == null)
    		lentille.setCatalogue(catalogueRepository.save(lentille.getCatalogue()));
        return lentilleRepository.save(lentille);
    }
	
    @DeleteMapping(path = "/{id}")
    public @ResponseBody boolean deleteLentilleById(@PathVariable long id) {
        lentilleRepository.deleteById(id);
        return true;
    }

}
