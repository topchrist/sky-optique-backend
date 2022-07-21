package com.sky_optique.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sky_optique.entities.Utilisateur;
import com.sky_optique.repositories.UtilisateurRepository;
import javax.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@RequestMapping(path = "/utilisateur")
@CrossOrigin("*")
public class UtilisateurController {
	
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    
    @PostMapping(value = "/login")
    public @ResponseBody Optional<Utilisateur> processLogin(@RequestBody Utilisateur utilisateur) {
        return utilisateurRepository.login(utilisateur.getPseudo(), utilisateur.getPassword());
    }

    @GetMapping(path = "/")
    public @ResponseBody Iterable <Utilisateur> getAllUtilisateur() {
        return utilisateurRepository.findAll();
    }
    
    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Utilisateur> getUtilisateurById(@PathVariable long id) {
        return utilisateurRepository.findById(id);
    }

    @GetMapping(path = "/find/{nom}")
    public @ResponseBody Optional<Utilisateur> getUtilisateurByName(@PathVariable String nom) {
        return utilisateurRepository.getUtilisateurByNom(nom);
    }
	
    @PostMapping(path = "/")
    public @ResponseBody Utilisateur addUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }
	
    @PutMapping(path = "/{id}")
    public @ResponseBody Utilisateur updateUtilisateur(@PathVariable long id, @RequestBody Utilisateur utilisateur) {
        utilisateur.setId(id);
        return utilisateurRepository.save(utilisateur);
    }
	
    @DeleteMapping(path = "/{id}")
    public @ResponseBody boolean deleteUtilisateurById(@PathVariable long id) {
        utilisateurRepository.deleteById(id);
        return true;
    }
	 
}
