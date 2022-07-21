package com.sky_optique;

import com.sky_optique.entities.Utilisateur;
import com.sky_optique.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkyOptiqueApplication implements CommandLineRunner {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public static void main(String[] args) {
            SpringApplication.run(SkyOptiqueApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception  {

        if(utilisateurRepository.getUtilisateurByNom("administrateur") == null){
            Utilisateur utilisateur1 = new Utilisateur("administrateur", "admin", "admin");
            utilisateurRepository.save(utilisateur1);
        }
    }

}
