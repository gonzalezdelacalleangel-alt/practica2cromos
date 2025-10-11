package com.trinitarias.gestorcromos.repository;

import org.springframework.stereotype.Repository;  // marks this interface as a Spring-managed repository component
import org.springframework.data.jpa.repository.JpaRepository; // provides CRUD methods (find, save, delete) automatically

import com.trinitarias.gestorcromos.Cromo;  // imports the entity that this repository manages

@Repository
public interface CromoRepository extends JpaRepository<Cromo, Long> {

}
