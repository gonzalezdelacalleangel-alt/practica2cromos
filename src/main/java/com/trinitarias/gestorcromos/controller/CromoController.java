package com.trinitarias.gestorcromos.controller;

import org.springframework.web.bind.annotation.*;  // provides annotations for handling HTTP requests (GET, POST, PUT, DELETE, etc.)
/*
 * which contents:
 * import org.springframework.web.bind.annotation.RestController;   // marks class as REST controller (returns JSON)
 * import org.springframework.web.bind.annotation.RequestMapping;   // sets the base URL path for the controller
 * import org.springframework.web.bind.annotation.GetMapping;       // handles GET requests
 * import org.springframework.web.bind.annotation.PostMapping;      // handles POST requests
 * import org.springframework.web.bind.annotation.PutMapping;       // handles PUT requests
 * import org.springframework.web.bind.annotation.DeleteMapping;    // handles DELETE requests
 * import org.springframework.web.bind.annotation.PathVariable;     // extracts values from URL paths
 * import org.springframework.web.bind.annotation.RequestParam;     // extracts query parameters from URLs
 * import org.springframework.web.bind.annotation.RequestBody;      // maps JSON request body to a Java object
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.trinitarias.gestorcromos.Cromo;
import com.trinitarias.gestorcromos.service.CromoService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@RestController  //this class will handle HTTP requests 
@RequestMapping("api/v1/cromos") //sets the base URL for this controller — all routes will start with "/api/v1/cromos" 

public class CromoController {

	@Autowired
    private CromoService cromoService; // instance that Spring will inject 
	
	@PostMapping
	public ResponseEntity<?> createCromo(@RequestBody Cromo cromo){
		
		if(cromo.getNombre() == null  || cromo.getNombre().isBlank()) { //name cant be null or empty
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre no puede estar vacio.");
		}
		
		if(cromo.getNumero()<0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El número no puede ser negativo.");
		}
		
		if(cromo.getSerie() == null || cromo.getSerie().isBlank()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La serie no puede estar vacía.");
		}
		
		
		int currentYear = java.time.Year.now().getValue();
		if(cromo.getAnio() < 1990 || cromo.getAnio() > currentYear ) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El año debe estar en el 1990 y el año actual.");
			
		}
		
		Cromo savedCromo = cromoService.saveCromo(cromo);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCromo);
		
		
	}
	
	@GetMapping
	public ResponseEntity<List<Cromo>> getAllCromos(){
		
			List<Cromo> allCromos = cromoService.getAllCromos();
			if(allCromos.isEmpty()) {
				return ResponseEntity.noContent().build();
	}
			return ResponseEntity.ok(allCromos);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		Optional<Cromo> savedCromo = cromoService.getById(id);
		if(savedCromo == null ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado ningún cromo con ese ID");
		}
		return ResponseEntity.ok(savedCromo);
	}
	

}
