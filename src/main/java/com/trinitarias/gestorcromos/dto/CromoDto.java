package com.trinitarias.gestorcromos.dto;

import java.util.List;

import com.trinitarias.gestorcromos.entity.CromoEntity;

public class CromoDto {
	public Long id;
	private String nombre, serie, rareza;
	private int numero, anio;
	private boolean repetido;
	
	public CromoDto() {
		super();
	}
	public CromoDto(Long id,String nombre, String serie, String rareza, int numero, int anio, boolean repetido) {
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
		this.id= id;
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
	
	
	 public static CromoDto fromEntityToDto(CromoEntity cromo) {
	        return new CromoDto(
	            cromo.getId(),
	            cromo.getNombre(),
	            cromo.getSerie(),
	            cromo.getRareza(),
	            cromo.getNumero(),
	            cromo.getAnio(),
	            cromo.isRepetido()
	        );
	    }

	    // List converter
	    public static List<CromoDto> fromEntityToDto(List<CromoEntity> cromos) {
	        return cromos.stream()
	                     .map(CromoDto::fromEntityToDto)
	                     .toList();
	    }
	
	}

	
