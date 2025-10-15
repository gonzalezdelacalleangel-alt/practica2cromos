package com.trinitarias.gestorcromos.repository;

import org.springframework.stereotype.Repository;  // marks this interface as a Spring-managed repository component

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; // provides CRUD methods (find, save, delete) automatically

  // imports the entity that this repository manages
import com.trinitarias.gestorcromos.entity.CromoEntity;

@Repository
public interface CromoRepository extends JpaRepository<CromoEntity, Long> {
	List<CromoEntity> findAllBySerieIgnoreCase(String serie);
		
	
}
