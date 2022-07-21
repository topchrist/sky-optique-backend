/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky_optique.controllers;

import com.sky_optique.entities.Fournisseur;
import com.sky_optique.repositories.FournisseurRepository;
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
@RequestMapping(path = "/fournisseur")
@CrossOrigin("*")
public class FournisseurController {
    
    @Autowired
    private FournisseurRepository fournisseurRepository;
	
    @GetMapping(path = "/")
    public @ResponseBody Iterable <Fournisseur> getAllFournisseur() {
        return fournisseurRepository.findAll();
    }
	
    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Fournisseur> getFournisseurById(@PathVariable long id) {
        return fournisseurRepository.findById(id);
    }
	
    @PostMapping(path = "/")
    public @ResponseBody Fournisseur addFournisseur(@RequestBody Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }
	
    @PutMapping(path = "/{id}")
    public @ResponseBody Fournisseur updateFournisseur(@PathVariable long id, @RequestBody Fournisseur fournisseur) {
        fournisseur.setId(id);
        return fournisseurRepository.save(fournisseur);
    }
	
    @DeleteMapping(path = "/{id}")
    public @ResponseBody boolean deleteFournisseurById(@PathVariable long id) {
        fournisseurRepository.deleteById(id);
        return true;
    }

}
