/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky_optique.controllers;

import com.sky_optique.entities.Bordereau;
import com.sky_optique.entities.FactureClient;
import com.sky_optique.entities.Vente;
import com.sky_optique.repositories.BordereauRepository;
import com.sky_optique.repositories.FactureClientRepository;

import java.text.DecimalFormat;
import java.util.Calendar;
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
@RequestMapping(path = "/bordereau")
@CrossOrigin("*")
public class BordereauController {
    
    @Autowired
    private BordereauRepository bordereauRepository;
    @Autowired
    private FactureClientRepository factureClientRepository;
	
    @GetMapping(path = "/")
    public @ResponseBody Iterable <Bordereau> getAllBordereau() {
        return bordereauRepository.findAll();
    }
	 
    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Bordereau> getBordereauById(@PathVariable long id) {
        return bordereauRepository.findById(id);
    }
    
    @Transactional
    @PostMapping(path = "/")
    public @ResponseBody Bordereau addBordereau(@RequestBody Bordereau bordereau) {
    	bordereau.setCreateAt(Calendar.getInstance().getTime());
    	Bordereau _bordereau = bordereauRepository.save(bordereau);
    	for(FactureClient f : bordereau.getFactureClients()) {
        	f.setBordereau(_bordereau);
        }
    	factureClientRepository.saveAll(bordereau.getFactureClients());
    	
    	DecimalFormat nf = new DecimalFormat("00000000");
        Calendar cal = Calendar.getInstance();
        cal.setTime(cal.getTime());
        _bordereau.setNumero(nf.format(_bordereau.getId())+'-'+cal.get(Calendar.YEAR));
        _bordereau.setId(_bordereau.getId());
        _bordereau = bordereauRepository.save(_bordereau);
    	
 
    	return _bordereau;
    }
	
    /*
    @PutMapping(path = "/{id}")
    public @ResponseBody Bordereau updateBordereau(@PathVariable long id, @RequestBody Bordereau bordereau) {
        //lentille.setId(id);
    	bordereau.setId(bordereau.getId());
        return bordereauRepository.save(bordereau);
    }
    */
	
    @DeleteMapping(path = "/{id}")
    public @ResponseBody boolean deleteBordereauById(@PathVariable long id) {
    	bordereauRepository.deleteById(id);
        return true;
    }

}
