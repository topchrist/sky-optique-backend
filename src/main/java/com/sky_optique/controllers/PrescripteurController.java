/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky_optique.controllers;

import com.sky_optique.entities.Prescripteur;
import com.sky_optique.repositories.PrescripteurRepository;
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
@RequestMapping(path = "/prescripteur")
@CrossOrigin("*")
public class PrescripteurController {
    
    @Autowired
    private PrescripteurRepository prescripteurRepository;
	
    @GetMapping(path = "/")
    public @ResponseBody Iterable <Prescripteur> getAllPrescripteur() {
        return prescripteurRepository.findAll();
    }
	
    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Prescripteur> getPrescripteurById(@PathVariable long id) {
        return prescripteurRepository.findById(id);
    }
	
    @PostMapping(path = "/")
    public @ResponseBody Prescripteur addPrescripteur(@RequestBody Prescripteur prescripteur) {
        return prescripteurRepository.save(prescripteur);
    }
	
    @PutMapping(path = "/{id}")
    public @ResponseBody Prescripteur updatePrescripteur(@PathVariable long id, @RequestBody Prescripteur prescripteur) {
    	prescripteur.setId(id);
        return prescripteurRepository.save(prescripteur);
    }
	
    @DeleteMapping(path = "/{id}")
    public @ResponseBody boolean deletePrescripteurById(@PathVariable long id) {
    	prescripteurRepository.deleteById(id);
        return true;
    }

}
