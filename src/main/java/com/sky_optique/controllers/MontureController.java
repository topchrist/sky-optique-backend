/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky_optique.controllers;

import com.sky_optique.entities.Marque;
import com.sky_optique.entities.Monture;
import com.sky_optique.repositories.MarqueRepository;
import com.sky_optique.repositories.MontureRepository;
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
@RequestMapping(path = "/monture")
@CrossOrigin("*")
public class MontureController {
    
    @Autowired
    private MontureRepository montureRepository;
    @Autowired
    private MarqueRepository marqueRepository;
	
    @GetMapping(path = "/")
    public @ResponseBody Iterable <Monture> getAllMonture() {
        return montureRepository.findAll();
    }
	
    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Monture> getMontureById(@PathVariable long id) {
        return montureRepository.findById(id);
    }

    @Transactional
    @PostMapping(path = "/")
    public @ResponseBody Monture addMonture(@RequestBody Monture monture) {
		if(monture.getMarque() !=null && monture.getMarque().getId() == null)
        	monture.setMarque(marqueRepository.save(monture.getMarque()));
        return montureRepository.save(monture);
    }

    @Transactional
    @PutMapping(path = "/{id}")
    public @ResponseBody Monture updateMonture(@PathVariable long id, @RequestBody Monture monture) {
		monture.setId(id);
		if(monture.getMarque() !=null && monture.getMarque().getId() == null)
        	monture.setMarque(marqueRepository.save(monture.getMarque()));

        return montureRepository.save(monture);
    }
	
    @DeleteMapping(path = "/{id}")
    public @ResponseBody boolean deleteMontureById(@PathVariable long id) {
        montureRepository.deleteById(id);
        return true;
    }
}
