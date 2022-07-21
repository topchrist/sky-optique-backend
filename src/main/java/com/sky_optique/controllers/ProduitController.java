/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky_optique.controllers;

import com.sky_optique.entities.Monture;
import com.sky_optique.entities.Produit;
import com.sky_optique.repositories.ProduitRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Chris
 */
@RestController
@RequestMapping(path = "/produit")
@CrossOrigin("*")
public class ProduitController {
    
    @Autowired
    private ProduitRepository produitRepository;
	
    @GetMapping(path = "/")
    public @ResponseBody Iterable <Produit> getAllProduit() {
        return produitRepository.findAll();
    }

    @GetMapping(path = "/filterProduit")
    public @ResponseBody Iterable <Produit> filterProduit(@RequestParam("str") String str) {
        return produitRepository.findProduitsByLibelleContains(str);
    }
	
    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Produit> getProduitById(@PathVariable long id) {
        return produitRepository.findById(id);
    }
    
    @PostMapping(path = "/")
    public @ResponseBody Produit addProduit(@RequestBody Produit produit) {
        return produitRepository.save(produit);
    }
    
    @DeleteMapping(path = "/{id}")
    public @ResponseBody boolean deleteProduitById(@PathVariable long id) {
        produitRepository.deleteById(id);
        return true;
    }

}
