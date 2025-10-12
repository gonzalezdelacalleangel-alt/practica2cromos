package com.trinitarias.gestorcromos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;   // marks this class as part of the service (business logic) layer

import com.trinitarias.gestorcromos.Cromo;
import com.trinitarias.gestorcromos.repository.CromoRepository;


@Service
public class CromoService {
	
	@Autowired
	private CromoRepository repo;
	
	
	public Cromo saveCromo(Cromo cromo) {
		return repo.save(cromo);
	}
	
	public List<Cromo> getAllCromos (){
		return repo.findAll();
	}
	
	public Optional<Cromo> getById(Long id) {
		return repo.findById(id);
	}
	
	public Optional<Cromo> update(Long id, Cromo newCromo){
		Optional<Cromo> oldCromo  = repo.findById(id);
		if(oldCromo== null) {
			return Optional.empty();
		}
		newCromo.setId(oldCromo.get().getId());
		repo.save(newCromo);
		return Optional.of(newCromo);
	}
}
