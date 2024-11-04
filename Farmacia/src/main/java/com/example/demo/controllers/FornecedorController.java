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

import com.example.demo.entities.Fornecedor;
import com.example.demo.services.FornecedorService;

@RestController
@RequestMapping("/Fornecedor")
public class FornecedorController {
	private final FornecedorService fornecedorService;
	
	@Autowired
	public FornecedorController(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;	
	}
	
	@PostMapping("/create")
	public Fornecedor createFornecedor(@RequestBody Fornecedor fornecedor) {
		return fornecedorService.saveFornecedor(fornecedor);
	}
	
	@GetMapping
	public List<Fornecedor> getAll(){
		return fornecedorService.getAllFornecedors();
	}
	
	@GetMapping("/{id}")
	public Fornecedor getById(@PathVariable Long id) {
		return fornecedorService.getFornecedorById(id);
	}
		
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		fornecedorService.deleteFornecedorById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Fornecedor> updateFornecedor(@PathVariable Long id, @RequestBody Fornecedor fornecedor) {
		Fornecedor updatedFornecedor = fornecedorService.updateFornecedor(id, fornecedor);
		if(updatedFornecedor!= null) {
			return ResponseEntity.ok(updatedFornecedor);
		}else {
			return null;
		}
	}
}
