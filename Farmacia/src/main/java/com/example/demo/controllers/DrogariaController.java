package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Drogaria;
import com.example.demo.services.DrogariaService;

@RestController
@RequestMapping("/Drogaria")
public class DrogariaController {
	private final DrogariaService drogariaService;
	
	@Autowired
	public DrogariaController(DrogariaService drogariaService) {
		this.drogariaService = drogariaService;	
	}
	
	@PostMapping("/create")
	public Drogaria createDrogaria(@RequestBody Drogaria drogaria) {
		return drogariaService.saveDrogaria(drogaria);
	}
	
	@GetMapping
	public List<Drogaria> getAll(){
		return drogariaService.getAllDrogarias();
	}
	
	@GetMapping("/{id}")
	public Drogaria getById(@PathVariable Long id) {
		return drogariaService.getDrogariaById(id);
	}
		
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		drogariaService.deleteDrogariaById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Drogaria> updateDrogaria(@PathVariable Long id, @RequestBody Drogaria drogaria) {
		Drogaria updatedDrogaria = drogariaService.updateDrogaria(id, drogaria);
		if(updatedDrogaria!= null) {
			return ResponseEntity.ok(updatedDrogaria);
		}else {
			return null;
		}
	}
}
