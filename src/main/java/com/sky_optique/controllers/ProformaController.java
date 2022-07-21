/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky_optique.controllers;

import com.sky_optique.entities.*;
import com.sky_optique.repositories.*;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Chris
 */
@RestController
@RequestMapping(path = "/proforma")
@CrossOrigin("*")
public class ProformaController {
    @Autowired
    private AssuranceRepository assuranceRepository;

    @Autowired
    private ProformaRepository proformaRepository;
 
    @Autowired
    private CompagniRepository compagniRepository;
 
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private PrescripteurRepository prescripteurRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private VenteRepository venteRepository;
    @Autowired
    private CouvertureRepository couvertureRepository;

    @GetMapping(path = "/find")
    public Page<Proforma> list(@RequestParam(name = "mc", defaultValue = "") String mc,
                               @RequestParam(name = "assurance", defaultValue = "0") Long assurance,
                               @RequestParam(name = "entreprise", defaultValue = "0") Long entreprise,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "2") int size) {
        if(assurance > 0 &&  entreprise == 0){
            return proformaRepository.findByPatientWithAssurance(assurance.longValue(), "%"+mc.toUpperCase()+"%", PageRequest.of(page, size));
        }
        else if(assurance == 0 &&  entreprise > 0){
            return proformaRepository.findByPatientWithEntreprise(entreprise.longValue(), "%"+mc.toUpperCase()+"%", PageRequest.of(page, size));
        }
        else if(assurance > 0 &&  entreprise > 0){
            return proformaRepository.findByPatientWithAssuranceAndEntreprise(assurance, entreprise, "%"+mc.toUpperCase()+"%", PageRequest.of(page, size));
        }
        else
            return proformaRepository.findByPatient("%"+mc.toUpperCase()+"%", PageRequest.of(page, size));
    }

    @GetMapping(path = "/")
    public @ResponseBody Iterable <Proforma> getAllProforma() {
        return proformaRepository.findAll();
    }
	
    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Proforma> getProformaById(@PathVariable long id) {
    	Optional<Proforma> proforma = proformaRepository.findById(id);
    	if(proforma != null) {
    		proforma.get().setVentes(venteRepository.findVentesByFacture_Id(id));
    	}
    	return proforma;	  
    }
	
    @Transactional
    @PostMapping(path = "/")
    public @ResponseBody Proforma addProforma(@RequestBody Proforma proforma) {
        System.out.println(proforma);
    	if(proforma.getPrescription() != null) {
    		Prescription prescr = proforma.getPrescription();
    		if(prescr.getPrescripteur().getId() == null)
    			proforma.getPrescription().setPrescripteur(prescripteurRepository.save(prescr.getPrescripteur()));
    		proforma.setPrescription(prescriptionRepository.save(prescr));
    	}
    	
    	if(proforma.getPatient().getId() == null)
    		proforma.setPatient(personneRepository.save(proforma.getPatient()));
        
    	proforma.setCreateAt(Calendar.getInstance().getTime());
    	Proforma _proforma = proformaRepository.save(proforma);
    	
        for(Vente v : proforma.getVentes()) {
        	v.setFacture(_proforma);
        }
    	venteRepository.saveAll(proforma.getVentes());
    	
    	if(proforma.getCouvertures() != null && !proforma.getCouvertures().isEmpty() ) {
        	Couverture couv = (Couverture) proforma.getCouvertures().toArray()[0];
        	if(couv.getAssurePrincipal().getId() == null)
        		couv.setAssurePrincipal(personneRepository.save(couv.getAssurePrincipal()));
        	if(couv.getAssurance().getId() == null)
        		couv.setAssurance(assuranceRepository.save(couv.getAssurance()));
        	if(couv.getEntreprise() != null && couv.getEntreprise().getId() == null)
        		couv.setEntreprise(compagniRepository.save(couv.getEntreprise()));
        	couv.setFacture(_proforma);
        	couvertureRepository.save(couv);
        }
    	
    	DecimalFormat nf = new DecimalFormat("00000000");
        Calendar cal = Calendar.getInstance();
        cal.setTime(cal.getTime());
        _proforma.setNumero(nf.format(_proforma.getId())+'-'+cal.get(Calendar.YEAR));
        _proforma.setId(_proforma.getId());
        _proforma = proformaRepository.save(_proforma);
    	
    	return _proforma;
    }
	
    @PutMapping(path = "/{id}")
    public @ResponseBody Proforma updateProforma(@PathVariable long id, @RequestBody Proforma proforma) {
    	proforma.setId(id);
        return proformaRepository.save(proforma);
    }
	
    @DeleteMapping(path = "/{id}")
    public @ResponseBody boolean deleteProformaById(@PathVariable long id) {
    	proformaRepository.deleteById(id);
        return true;
    }

}
