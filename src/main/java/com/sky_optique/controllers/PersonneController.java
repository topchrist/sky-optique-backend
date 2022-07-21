package com.sky_optique.controllers;

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
import com.sky_optique.entities.Personne;
import com.sky_optique.repositories.PersonneRepository;
import com.sky_optique.repositories.PrescripteurRepository;

@RestController
@RequestMapping(path = "/personne")
@CrossOrigin("*")
public class PersonneController {
	
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private PrescripteurRepository prescripteurRepository;
     
    
	@GetMapping(path = "/")
    public @ResponseBody Iterable <Personne> getAllPersonne() {
        return personneRepository.findAll();
    }
 
	@GetMapping(path = "/{id}")
    public @ResponseBody Optional<Personne> getPersonneById(@PathVariable long id) {
        return personneRepository.findById(id);
    }
	
	@PostMapping(path = "/")
    public @ResponseBody
    Personne addPersonne(@RequestBody Personne personne) {
        return personneRepository.save(personne);
    }
	
	@PutMapping(path = "/{id}")
    public @ResponseBody
    Personne updatePersonne(@PathVariable long id, @RequestBody Personne personne) {
		personne.setId(id);
        return personneRepository.save(personne);
    }
	
	@DeleteMapping(path = "/{id}")
    public @ResponseBody boolean deletePersonneById(@PathVariable long id) {
        personneRepository.deleteById(id);
        return true;
    }

}
