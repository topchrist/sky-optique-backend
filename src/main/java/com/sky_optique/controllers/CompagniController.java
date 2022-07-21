/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky_optique.controllers;

import com.sky_optique.entities.Compagni;
import com.sky_optique.repositories.CompagniRepository;

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
@RequestMapping(path = "/compagni")
@CrossOrigin("*")
public class CompagniController {
    
    @Autowired
    private CompagniRepository compagniRepository;
	
    @GetMapping(path = "/")
    public @ResponseBody Iterable <Compagni> getAllCompagni() {
        return compagniRepository.findAll();
    }
	
    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Compagni> getCompagniById(@PathVariable long id) {
        return compagniRepository.findById(id);
    }
	
    @PostMapping(path = "/")
    public @ResponseBody Compagni addCompagni(@RequestBody Compagni compagni) {
        return compagniRepository.save(compagni);
    }
	
    @PutMapping(path = "/{id}")
    public @ResponseBody Compagni updateCompagni(@PathVariable long id, @RequestBody Compagni compagni) {
    	compagni.setId(id);
        return compagniRepository.save(compagni);
    }
	
    @DeleteMapping(path = "/{id}")
    public @ResponseBody boolean deleteCompagniById(@PathVariable long id) {
    	compagniRepository.deleteById(id);
        return true;
    }

}
