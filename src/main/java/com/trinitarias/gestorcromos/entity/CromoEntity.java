package com.trinitarias.gestorcromos.entity;


import com.trinitarias.gestorcromos.dto.CromoDto;

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
public class CromoEntity {

		@Id //marks the primary key field
		@GeneratedValue(strategy = GenerationType.IDENTITY) 	
		private Long id;
		
		
		@Column(name = "nombre")
		private String nombre;
		
		@Column(name = "serie")
		private String serie;
		
		@Column(name = "rareza")
		private String rareza;
		
		@Column(name = "numero")
		private int numero;
		
		@Column(name = "anio")
		private int anio;
		
		@Column(name = "repetido")
		private boolean repetido;
		
		public CromoEntity() {}
		
		
		
		public CromoEntity(Long id,String nombre, String serie, String rareza, int numero, int anio,
				boolean repetido) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.serie = serie;
			this.rareza = rareza;
			this.numero = numero;
			this.anio = anio;
			this.repetido = repetido;
		}



		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getSerie() {
			return serie;
		}
		public void setSerie(String serie) {
			this.serie = serie;
		}
		public String getRareza() {
			return rareza;
		}
		public void setRareza(String rareza) {
			this.rareza = rareza;
		}
		public int getNumero() {
			return numero;
		}
		public void setNumero(int numero) {
			this.numero = numero;
		}
		public int getAnio() {
			return anio;
		}
		public void setAnio(int anio) {
			this.anio = anio;
		}
		public boolean isRepetido() {
			return repetido;
		}
		public void setRepetido(boolean repetido) {
			this.repetido = repetido;
		}
		
		
		public static CromoEntity fromDtoToEntity(CromoDto cromo) {
			return new CromoEntity(cromo.getId(),cromo.getNombre(), cromo.getSerie(), cromo.getRareza(),cromo.getNumero(),cromo.getAnio(), cromo.isRepetido());
		}

		

}

