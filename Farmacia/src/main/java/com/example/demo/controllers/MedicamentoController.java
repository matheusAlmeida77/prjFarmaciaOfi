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

import com.example.demo.entities.Medicamento;
import com.example.demo.services.MedicamentoService;

@RestController
@RequestMapping("/Medicamento")
public class MedicamentoController {
	private final MedicamentoService medicamentoService;
	
	@Autowired
	public MedicamentoController(MedicamentoService medicamentoService) {
		this.medicamentoService = medicamentoService;	
	}
	
	@PostMapping("/create")
	public ResponseEntity<Medicamento> createMedicamento(@RequestBody Medicamento medicamento) {
		Medicamento savedMedicamento = medicamentoService.saveMedicamento(medicamento);
		if(savedMedicamento != null) {
			return ResponseEntity.ok(savedMedicamento);
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping
	public List<Medicamento> getAll(){
		return medicamentoService.getAllMedicamentos();
	}
	
	@GetMapping("/{id}")
	public Medicamento getById(@PathVariable Long id) {
		return medicamentoService.getMedicamentoById(id);
	}
		
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		medicamentoService.deleteMedicamentoById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Medicamento> updateMedicamento(@PathVariable Long id, @RequestBody Medicamento medicamento) {
		Medicamento updatedMedicamento = medicamentoService.updateMedicamento(id, medicamento);
		if(updatedMedicamento!= null) {
			return ResponseEntity.ok(updatedMedicamento);
		}else {
			return null;
		}
	}
}
