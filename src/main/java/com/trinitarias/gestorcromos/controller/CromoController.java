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


@RestController  //this class will handle HTTP requests 
@RequestMapping("api/v1/cromos") //sets the base URL for this controller — all routes will start with "/api/v1/cromos" 

public class CromoController {

	

}
