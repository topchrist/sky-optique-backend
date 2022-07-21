/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky_optique.controllers;

import com.sky_optique.entities.Patient;
import com.sky_optique.repositories.CompagniRepository;
import com.sky_optique.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 *
 * @author Chris
 */
@RestController
@RequestMapping(path = "/patient")
@CrossOrigin("*")
public class PatientController {
    
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private CompagniRepository compagniRepository;

    @GetMapping(path = "/find")
    public Page<Patient> list(@RequestParam(name = "mc", defaultValue = "") String mc,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "2") int size) {
        return patientRepository.chercher("%"+mc.toUpperCase()+"%", PageRequest.of(page, size));
    }
	
    @GetMapping(path = "/")
    public @ResponseBody Iterable <Patient> getAllPatient() {
        return patientRepository.findAll();
    }
	
    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Patient> getPatientById(@PathVariable long id) {
        return patientRepository.findById(id);
    }
	
    @PostMapping(path = "/")
    public @ResponseBody Patient addPatient(@RequestBody Patient patient) {
        if(patient.getEntreprise() !=null && patient.getEntreprise().getId() == null)
            patient.setEntreprise(compagniRepository.save(patient.getEntreprise()));
        return patientRepository.save(patient);
    }
	
    @PutMapping(path = "/{id}")
    public @ResponseBody Patient updatePatient(@PathVariable long id, @RequestBody Patient patient) {
    	patient.setId(id);
        if(patient.getEntreprise() !=null && patient.getEntreprise().getId() == null)
            patient.setEntreprise(compagniRepository.save(patient.getEntreprise()));
        return patientRepository.save(patient);
    }
	
    @DeleteMapping(path = "/{id}")
    public @ResponseBody boolean deletePatientById(@PathVariable long id) {
        patientRepository.deleteById(id);
        return true;
    }

}
