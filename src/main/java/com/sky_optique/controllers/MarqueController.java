/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky_optique.controllers;

import com.sky_optique.entities.Marque;
import com.sky_optique.repositories.MarqueRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Chris
 */
@RestController
@RequestMapping(path = "/marque")
@CrossOrigin("*")
public class MarqueController {
    
    @Autowired
    private MarqueRepository marqueRepository;


    @GetMapping(path = "/find")
    public Page<Marque> list(@RequestParam(name = "mc", defaultValue = "") String mc,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "2") int size) {
        return marqueRepository.chercher("%"+mc.toUpperCase()+"%", PageRequest.of(page, size));
    }
	
    @GetMapping(path = "/")
    public @ResponseBody Iterable <Marque> getAllMarque() {
        return marqueRepository.findAll();
    }
	
    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Marque> getMarqueById(@PathVariable long id) {
        return marqueRepository.findById(id);
    }
	
    @PostMapping(path = "/")
    public @ResponseBody Marque addMarque(@RequestBody Marque marque) {
        return marqueRepository.save(marque);
    }
	
    @PutMapping(path = "/{id}")
    public @ResponseBody Marque updateMarque(@PathVariable long id, @RequestBody Marque marque) {
    	marque.setId(id);
        return marqueRepository.save(marque);
    }
	
    @DeleteMapping(path = "/{id}")
    public @ResponseBody boolean deleteMontureById(@PathVariable long id) {
    	marqueRepository.deleteById(id);
        return true;
    }
}
