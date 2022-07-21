/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky_optique.controllers;

import com.sky_optique.entities.Facture;
import com.sky_optique.repositories.FactureRepository;
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
@RequestMapping(path = "/facture")
@CrossOrigin("*")
public class FactureController {
    
    @Autowired
    private FactureRepository factureRepository;
	
    @GetMapping(path = "/")
    public @ResponseBody Iterable <Facture> getAllFacture() {
        return factureRepository.findAll();
    }
	
    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Facture> getFactureById(@PathVariable long id) {
        return factureRepository.findById(id);
    }
	
    @PostMapping(path = "/")
    public @ResponseBody Facture addFacture(@RequestBody Facture facture) {
        return factureRepository.save(facture);
    }
	
    @PutMapping(path = "/{id}")
    public @ResponseBody Facture updateFacture(@PathVariable long id, @RequestBody Facture facture) {
    	facture.setId(id);
        return factureRepository.save(facture);
    }
	
    @DeleteMapping(path = "/{id}")
    public @ResponseBody boolean deleteFactureById(@PathVariable long id) {
    	factureRepository.deleteById(id);
        return true;
    }

}
