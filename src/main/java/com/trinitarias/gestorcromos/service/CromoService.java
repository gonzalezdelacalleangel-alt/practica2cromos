package com.trinitarias.gestorcromos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;   // marks this class as part of the service (business logic) layer

import com.trinitarias.gestorcromos.dto.CromoDto;
import com.trinitarias.gestorcromos.entity.CromoEntity;
import com.trinitarias.gestorcromos.repository.CromoRepository;


@Service
public class CromoService {
	
	@Autowired
	private CromoRepository repo;
	
	
	public CromoDto saveCromo(CromoDto cromo) {
		CromoEntity newCromo;
		newCromo = CromoEntity.fromDtoToEntity(cromo);
		repo.save(newCromo);
		cromo.setId(newCromo.getId());
		return cromo;
	}
	
	public List<CromoDto> getAllCromos (){
		return CromoDto.fromEntityToDto(repo.findAll());
	}
	
	
	
	public Optional<CromoDto> getById(Long id) {
	    return repo.findById(id)
	               .map(CromoDto::fromEntityToDto);
	}
	
	public Optional<CromoDto> update(Long id, CromoDto newCromo){
		Optional<CromoEntity> oldCromo  = repo.findById(id);
		if(oldCromo.isEmpty()) {
			return Optional.empty();
		}
		newCromo.setId(oldCromo.get().getId());
		repo.save(CromoEntity.fromDtoToEntity(newCromo));
		return Optional.of(newCromo);
	}
	
	public boolean deleteById (Long id){
		Optional<CromoEntity> cromo = repo.findById(id);
		if(cromo.isEmpty()) {
			return false;
		}
		repo.deleteById(id);
		return true;
	}
	
	public List<CromoDto> getBySerie(String serie){	
		return CromoDto.fromEntityToDto(repo.findAllBySerieIgnoreCase(serie));
	}
}
