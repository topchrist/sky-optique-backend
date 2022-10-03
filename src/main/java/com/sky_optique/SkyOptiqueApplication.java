package com.sky_optique;

import com.sky_optique.entities.Utilisateur;
import com.sky_optique.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SkyOptiqueApplication implements CommandLineRunner {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public static void main(String[] args) {
            SpringApplication.run(SkyOptiqueApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

    @Override
    public void run(String... args) throws Exception  {

        if(utilisateurRepository.getUtilisateurByNom("Administrateur") == null){
            Utilisateur utilisateur1 = new Utilisateur("Administrateur", "admin", "admin");
            utilisateurRepository.save(utilisateur1);
        }
    }

}
