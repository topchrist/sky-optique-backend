/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky_optique.controllers;

import com.sky_optique.entities.Assurance;
import com.sky_optique.repositories.AssuranceRepository;

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
@RequestMapping(path = "/assurance")
@CrossOrigin("*")
public class AssuranceController {
    
    @Autowired
    private AssuranceRepository assuranceRepository;
	
    @GetMapping(path = "/")
    public @ResponseBody Iterable <Assurance> getAllAssurance() {
        return assuranceRepository.findAll();
    }
	
    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Assurance> getAssuranceById(@PathVariable long id) {
        return assuranceRepository.findById(id);
    }
	
    @PostMapping(path = "/")
    public @ResponseBody Assurance addAssurance(@RequestBody Assurance assurance) {
        return assuranceRepository.save(assurance);
    }
	
    @PutMapping(path = "/{id}")
    public @ResponseBody Assurance updateAssurance(@PathVariable long id, @RequestBody Assurance assurance) {
    	assurance.setId(id);
        return assuranceRepository.save(assurance);
    }
	
    @DeleteMapping(path = "/{id}")
    public @ResponseBody boolean deleteAssuranceById(@PathVariable long id) {
    	assuranceRepository.deleteById(id);
        return true;
    }

}
