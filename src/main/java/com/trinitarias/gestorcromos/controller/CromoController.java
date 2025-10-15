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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.trinitarias.gestorcromos.dto.CromoDto;
import com.trinitarias.gestorcromos.service.CromoService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@RestController  //this class will handle HTTP requests 
@RequestMapping("api/v1/cromos") //sets the base URL for this controller — all routes will start with "/api/v1/cromos" 

public class CromoController {

	@Autowired
    private CromoService cromoService; // instance that Spring will inject 
	
	@PostMapping
	public ResponseEntity<?> createCromo(@RequestBody CromoDto cromo){
		try {
		
			ResponseEntity<?> validationError = dataValidation(cromo);
			if(validationError != null ) {
				return validationError;
			}
			
			CromoDto savedCromo = cromoService.saveCromo(cromo);
			 URI location = ServletUriComponentsBuilder
		                .fromCurrentRequest()       // /api/cromos
		                .path("/{id}")              // añade /{id}
		                .buildAndExpand(savedCromo.getId()) // reemplaza {id} con el ID real
		                .toUri();
		    return ResponseEntity.created(location).body(savedCromo);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del sistema");
		}
		
	}
	
	@GetMapping
	public ResponseEntity<List<CromoDto>> getAllCromos(){
		try {
		
				List<CromoDto> allCromos = cromoService.getAllCromos();
				if(allCromos.isEmpty()) {
					return ResponseEntity.noContent().build();
		}
				return ResponseEntity.ok(allCromos);
		}catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		try {
			Optional<CromoDto> savedCromo = cromoService.getById(id);
			if(savedCromo.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado ningún cromo con ese ID");
			}
			return ResponseEntity.ok(savedCromo);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del sistema");
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update (@PathVariable Long id, @RequestBody CromoDto newCromo){
		try {
			ResponseEntity<?> validationError = dataValidation(newCromo);
			if (validationError != null) {
				return validationError;
			}
			Optional<CromoDto> savedCromo = cromoService.update(id, newCromo);
			if(savedCromo.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado ningún cromo con ese ID");
			}
			return ResponseEntity.ok(savedCromo);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del sistema");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById (@PathVariable Long id){
		try {
			boolean isDeleted = cromoService.deleteById(id);
			if(!isDeleted) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado ningún cromo con ese ID");
			}
			return ResponseEntity.noContent().build();
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del sistema");
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> getAllBySerie (@RequestParam String serie){
		try {
			List<CromoDto> cromos =  cromoService.getBySerie(serie);
			if(cromos.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado ningún cromo con esa serie.");
			}
			return ResponseEntity.ok(cromos);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del sistema");
		}
	}
	
	
	
	public ResponseEntity<?> dataValidation(CromoDto cromo){

		if(cromo.getNombre() == null  || cromo.getNombre().isBlank()) { //name cant be null or empty
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre no puede estar vacio.");
		}
		
		if(cromo.getNumero()<=0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El número debe ser mayor que 0.");
		}
		
		if(cromo.getSerie() == null || cromo.getSerie().isBlank()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La serie no puede estar vacía.");
		}
		
		
		int currentYear = java.time.Year.now().getValue();
		if(cromo.getAnio() < 1990 || cromo.getAnio() > currentYear ) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El año debe estar en el 1990 y el año actual.");
			
		}
		return null;
	}

}


