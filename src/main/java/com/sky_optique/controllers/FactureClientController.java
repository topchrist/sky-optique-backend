/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky_optique.controllers;

import com.sky_optique.entities.*;
import com.sky_optique.repositories.AssuranceRepository;
import com.sky_optique.repositories.CompagniRepository;
import com.sky_optique.repositories.CouvertureRepository;
import com.sky_optique.repositories.FactureClientRepository;
import com.sky_optique.repositories.PersonneRepository;
import com.sky_optique.repositories.PrescripteurRepository;
import com.sky_optique.repositories.PrescriptionRepository;
import com.sky_optique.repositories.StockRepository;
import com.sky_optique.repositories.VenteRepository;

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
@RequestMapping(path = "/factureClient")
@CrossOrigin("*")
public class FactureClientController {
    
    @Autowired
    private AssuranceRepository assuranceRepository;
    @Autowired
    private CompagniRepository compagniRepository;
    @Autowired
    private FactureClientRepository factureClientRepository;
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
    public Page<FactureClient> list(@RequestParam(name = "mc", defaultValue = "") String mc,
                               @RequestParam(name = "assurance", defaultValue = "0") Long assurance,
                               @RequestParam(name = "entreprise", defaultValue = "0") Long entreprise,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "2") int size) {
        if(assurance > 0 &&  entreprise == 0){
            return factureClientRepository.findByPatientWithAssurance(assurance.longValue(), "%"+mc.toUpperCase()+"%", PageRequest.of(page, size));
        }
        else if(assurance == 0 &&  entreprise > 0){
            return factureClientRepository.findByPatientWithEntreprise(entreprise.longValue(), "%"+mc.toUpperCase()+"%", PageRequest.of(page, size));
        }
        else if(assurance > 0 &&  entreprise > 0){
            return factureClientRepository.findByPatientWithAssuranceAndEntreprise(assurance, entreprise, "%"+mc.toUpperCase()+"%", PageRequest.of(page, size));
        }
        else
            return factureClientRepository.findByPatient("%"+mc.toUpperCase()+"%", PageRequest.of(page, size));
    }
	
    @GetMapping(path = "/")
    public @ResponseBody Iterable <FactureClient> getAllFactureClient() {
        return factureClientRepository.findAll();
    }
    
    @GetMapping(path = "/wbordereau/")
    public @ResponseBody Iterable<FactureClient> getAllFactureClientWithoutBordereau() {
        return factureClientRepository.findAllByBordereauIsNullAndCouvertures();
    }
    
    @GetMapping(path = "/bordereau/")
    public @ResponseBody Iterable <FactureClient> getAllFactureClientWithBordereau() {
        return factureClientRepository.findAllByBordereauIsNotNull();
    }
	
    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<FactureClient> getFactureClientById(@PathVariable long id) {
        return factureClientRepository.findById(id);
    }
	
    @Transactional
    @PostMapping(path = "/")
    public @ResponseBody FactureClient addFactureClient(@RequestBody FactureClient facture) {
    	if(facture.getPrescription() != null) {
    		Prescription prescr = facture.getPrescription();
    		if(prescr.getPrescripteur().getId() == null)
        		facture.getPrescription().setPrescripteur(prescripteurRepository.save(prescr.getPrescripteur()));
    		facture.setPrescription(prescriptionRepository.save(prescr));
    	}
    	
    	if(facture.getPatient().getId() == null)
        	facture.setPatient(personneRepository.save(facture.getPatient()));
        
        facture.setCreateAt(Calendar.getInstance().getTime());
        FactureClient _facture = factureClientRepository.save(facture);
        
        for(Vente v : facture.getVentes()) {
        	v.setFacture(_facture);
        }
    	venteRepository.saveAll(facture.getVentes());
    	
    	if(facture.getCouvertures() != null && !facture.getCouvertures().isEmpty() ) {
        	Couverture couv = (Couverture) facture.getCouvertures().toArray()[0];
        	if(couv.getAssurePrincipal().getId() == null)
        		couv.setAssurePrincipal(personneRepository.save(couv.getAssurePrincipal()));
        	if(couv.getAssurance().getId() == null)
        		couv.setAssurance(assuranceRepository.save(couv.getAssurance()));
        	if(couv.getEntreprise().getId() == null)
        		couv.setEntreprise(compagniRepository.save(couv.getEntreprise()));
        	couv.setFacture(_facture);
        	couvertureRepository.save(couv);
        }
    	/*
    	for(Vente v : facture.getVentes()) {
        	Stock stock = stockRepository.findStockByProductId(v.getStock().getProduit().getId());
        	stock.setQte(stock.getQte() - v.getQte());
        	stockRepository.save(stock);
        }
    	*/
    	DecimalFormat nf = new DecimalFormat("00000000");
        Calendar cal = Calendar.getInstance();
        cal.setTime(cal.getTime());
        _facture.setNumero(nf.format(_facture.getId())+'-'+cal.get(Calendar.YEAR));
        _facture.setId(_facture.getId());
        _facture = factureClientRepository.save(_facture);
       
        return _facture;
    }
	
    @PutMapping(path = "/{id}")
    public @ResponseBody FactureClient updateFactureClient(@PathVariable long id, @RequestBody FactureClient facture) {
    	facture.setId(id);
        return factureClientRepository.save(facture);
    }
	
    @DeleteMapping(path = "/{id}")
    public @ResponseBody boolean deleteFactureClientById(@PathVariable long id) {
    	factureClientRepository.deleteById(id);
        return true;
    }

}
