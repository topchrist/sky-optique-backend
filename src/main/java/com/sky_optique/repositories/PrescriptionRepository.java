package com.sky_optique.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sky_optique.entities.Prescription;

public interface PrescriptionRepository extends CrudRepository<Prescription, Long>{

}
