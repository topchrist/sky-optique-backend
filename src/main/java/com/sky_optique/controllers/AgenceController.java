/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky_optique.controllers;

import com.sky_optique.entities.Agence;
import com.sky_optique.repositories.AgenceRepository;
 
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
@RequestMapping(path = "/agence")
@CrossOrigin("*")
public class AgenceController {
    
    @Autowired
    private AgenceRepository agenceRepository;
     
    @GetMapping(path = "/")
    public @ResponseBody Iterable <Agence> getAllAgence() {
        return agenceRepository.findAll();
    }
	
    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Agence> getAgenceById(@PathVariable long id) {
        return agenceRepository.findById(id);
    }
	
    @PostMapping(path = "/")
    public @ResponseBody Agence addAgence(@RequestBody Agence agence) {
    	return agenceRepository.save(agence);
    }
	
    @PutMapping(path = "/{id}")
    public @ResponseBody Agence updateAgence(@PathVariable long id, @RequestBody Agence agence) {
        //lentille.setId(id);
    	agence.setId(agence.getId());
        return agenceRepository.save(agence);
    }
	
    @DeleteMapping(path = "/{id}")
    public @ResponseBody boolean deleteAgenceById(@PathVariable long id) {
    	agenceRepository.deleteById(id);
        return true;
    }

}
