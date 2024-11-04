package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Drogaria;
import com.example.demo.repositories.DrogariaRepository;

@Service
public class DrogariaService {
	private final DrogariaRepository drogariaRepository;
	
	@Autowired
	public DrogariaService(DrogariaRepository drogariaRepository) {
		this.drogariaRepository = drogariaRepository;
	}
	
	public Drogaria saveDrogaria(Drogaria drogaria) {
		return drogariaRepository.save(drogaria);
	}
	
	public Drogaria getDrogariaById(Long id) {
		return drogariaRepository.findById(id).orElse(null);
	}
	
	public List<Drogaria> getAllDrogarias(){
		return drogariaRepository.findAll();
	}
	
	public void deleteDrogariaById(Long id) {
		drogariaRepository.deleteById(id);
	}
	
	public Drogaria updateDrogaria(Long id, Drogaria updatedDrogaria) {
		Optional<Drogaria> existentDrogaria = drogariaRepository.findById(id);
		if (existentDrogaria.isPresent()){
			Drogaria drogaria = existentDrogaria.get();
			drogaria.setNome(updatedDrogaria.getNome());
			drogaria.setCnpj(updatedDrogaria.getCnpj());
			return drogariaRepository.save(drogaria);
		} else {
			return null;
		}
	}
}