package com.sky_optique.controllers;

import com.sky_optique.entities.BonCommande;
import com.sky_optique.entities.Commande;
import com.sky_optique.entities.Fournisseur;
import com.sky_optique.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import javax.print.attribute.standard.DateTimeAtCompleted;

/**
 *
 * @author Chris
 */
@RestController
@RequestMapping(path = "/bonCommande")
@CrossOrigin("*")
public class BonCommandeController {
    
    @Autowired
    private BonCommandeRepository bonCommandeRepository;
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private FournisseurRepository fournisseurRepository;

    @GetMapping(path = "/")
    public @ResponseBody Iterable <BonCommande> getAllBonCommande() {
        return bonCommandeRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<BonCommande> getBonCommandeById(@PathVariable long id) {
        return bonCommandeRepository.findById(id);
    }

    @PostMapping(path = "/")
    @Transactional
    public @ResponseBody
    BonCommande addBonCommande(@RequestBody BonCommande bonCommande) {
        System.out.println(bonCommande);
        bonCommande.setCreateAt(Calendar.getInstance().getTime());
   
        return bonCommandeRepository.save(bonCommande);
    }

    @PutMapping(path = "/{id}")
    @Transactional
    public @ResponseBody BonCommande updateBonCommande(@PathVariable long id, @RequestBody BonCommande bonCommande) {
        bonCommande.setId(id);
        return bonCommandeRepository.save(bonCommande);
    }
	
    @DeleteMapping(path = "/{id}")
    @Transactional
    public @ResponseBody boolean deleteBonCommandeById(@PathVariable long id) {
        bonCommandeRepository.deleteById(id);
        return true;
    }

}
