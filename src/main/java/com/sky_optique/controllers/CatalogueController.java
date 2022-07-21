/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky_optique.controllers;

import com.sky_optique.entities.Catalogue;
import com.sky_optique.repositories.CatalogueRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "/catalogue")
@CrossOrigin("*")
public class CatalogueController {
    
    @Autowired
    private CatalogueRepository catalogueRepository;
	
    @GetMapping(path = "/")
    public @ResponseBody Iterable <Catalogue> getAllCatalogue() {
        return catalogueRepository.findAll();
    }
	
    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Catalogue> getCatalogueById(@PathVariable long id) {
        return catalogueRepository.findById(id);
    }
	
    @PostMapping(path = "/")
    public @ResponseBody Catalogue addCatalogue(@RequestBody Catalogue catalogue) {
        return catalogueRepository.save(catalogue);
    }
	
    @PutMapping(path = "/{id}")
    public @ResponseBody Catalogue updateCatalogue(@PathVariable long id, @RequestBody Catalogue catalogue) {
    	catalogue.setId(id);
        return catalogueRepository.save(catalogue);
    }
	
    @DeleteMapping(path = "/{id}")
    public @ResponseBody boolean deleteCatalogueById(@PathVariable long id) {
    	catalogueRepository.deleteById(id);
        return true;
    }
}
