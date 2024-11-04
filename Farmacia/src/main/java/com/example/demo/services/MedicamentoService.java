package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Fornecedor;
import com.example.demo.entities.Medicamento;
import com.example.demo.repositories.FornecedorRepository;
import com.example.demo.repositories.MedicamentoRepository;

@Service
public class MedicamentoService {
	private final MedicamentoRepository medicamentoRepository;
	private final FornecedorRepository fornecedorRepository;
	
	@Autowired
	public MedicamentoService(MedicamentoRepository medicamentoRepository, FornecedorRepository fornecedorRepository) {
		this.medicamentoRepository = medicamentoRepository;
		this.fornecedorRepository = fornecedorRepository;
	}
	
	public Medicamento saveMedicamento(Medicamento medicamento) {
		if(medicamento.getFornecedor() != null && medicamento.getFornecedor().getId() != null) {
			Optional<Fornecedor> fornecedor = fornecedorRepository.findById(medicamento.getFornecedor().getId());
			if(fornecedor.isPresent()) {
				medicamento.setFornecedor(fornecedor.get());
				return medicamentoRepository.save(medicamento);
			}else {
				throw new RuntimeException("Fornecedor não encontrado");
			}
		}else {
			throw new RuntimeException("O ID do Fornecedor é obrigatório");
		}
	}
	
	public Medicamento getMedicamentoById(Long id) {
		return medicamentoRepository.findById(id).orElse(null);
	}
	
	public List<Medicamento> getAllMedicamentos(){
		return medicamentoRepository.findAll();
	}
	
	public void deleteMedicamentoById(Long id) {
		medicamentoRepository.deleteById(id);
	}
	
	public Medicamento updateMedicamento(Long id, Medicamento updatedMedicamento) {
		Optional<Medicamento> existentMedicamento = medicamentoRepository.findById(id);
		if (existentMedicamento.isPresent()){
			Medicamento medicamento = existentMedicamento.get();
			medicamento.setNome(updatedMedicamento.getNome());
			medicamento.setBula(updatedMedicamento.getBula());
			medicamento.setDataValidade(updatedMedicamento.getDataValidade());
			if(updatedMedicamento.getFornecedor() != null) {
				medicamento.setFornecedor(updatedMedicamento.getFornecedor());
			}
			return medicamentoRepository.save(medicamento);
		} else {
			return null;
		}
	}
}