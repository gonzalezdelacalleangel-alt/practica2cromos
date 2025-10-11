package com.trinitarias.gestorcromos;


import jakarta.persistence.*; //this single import gives you access to the annotations that turn Cromo into a database table definition.
/*
 * which contents:
 * import jakarta.persistence.Entity;          // marks the class as a database entity (table)
 * import jakarta.persistence.Table;           // sets the database table name
 * import jakarta.persistence.Id;              // defines the primary key
 * import jakarta.persistence.GeneratedValue;  // auto-generates primary key values
 * import jakarta.persistence.GenerationType;  // strategy for generating IDs
 */
@Entity //tells JPA (the database library) that this class represents a table in the database
public class Cromo {

		@Id //marks the primary key field
		@GeneratedValue(strategy = GenerationType.IDENTITY) 	
		private Long id;
		
		private String nombre, serie, rareza;
		private int numero, anio;
		private boolean repetido;
		
		
	

}
