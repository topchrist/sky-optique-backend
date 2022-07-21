/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky_optique.controllers;

import com.sky_optique.entities.Vente;
import com.sky_optique.repositories.VenteRepository;
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
@RequestMapping(path = "/vente")
@CrossOrigin("*")
public class VenteController {
    
    @Autowired
    private VenteRepository venteRepository;
	
    @GetMapping(path = "/")
    public @ResponseBody Iterable <Vente> getAllVente() {
        return venteRepository.findAll();
    }
    
    @GetMapping(path = "/getAllVentesByIdFacture/{id}")
    public @ResponseBody Iterable <Vente> findVentesByFacture_Id(@PathVariable long id) {
        return venteRepository.findAll();
    }
	
    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Vente> getVenteById(@PathVariable long id) {
        return venteRepository.findById(id);
    }
	
    @PostMapping(path = "/")
    public @ResponseBody Vente addVente(@RequestBody Vente vente) {
        return venteRepository.save(vente);
    }
	
    @PutMapping(path = "/{id}")
    public @ResponseBody Vente updateVente(@PathVariable long id, @RequestBody Vente vente) {
    	vente.setId(id);
        return venteRepository.save(vente);
    }
	
    @DeleteMapping(path = "/{id}")
    public @ResponseBody boolean deleteVenteById(@PathVariable long id) {
    	venteRepository.deleteById(id);
        return true;
    }

}
