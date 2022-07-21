package com.sky_optique.repositories;

import org.springframework.data.repository.CrudRepository;
import com.sky_optique.entities.Utilisateur;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {
    @Query("select u from Utilisateur u where u.pseudo like :ps and u.password like :pw")
    public Optional<Utilisateur> login(@Param("ps")String pseudo, @Param("pw")String password);
    public Optional<Utilisateur> getUtilisateurByNom(@Param("nom") String nom);
}
