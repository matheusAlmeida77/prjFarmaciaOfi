package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Fornecedor;
import com.example.demo.repositories.FornecedorRepository;

@Service
public class FornecedorService {
	private final FornecedorRepository fornecedorRepository;
	
	@Autowired
	public FornecedorService(FornecedorRepository fornecedorRepository) {
		this.fornecedorRepository = fornecedorRepository;
	}
	
	public Fornecedor saveFornecedor(Fornecedor fornecedor) {
		return fornecedorRepository.save(fornecedor);
	}
	
	public Fornecedor getFornecedorById(Long id) {
		return fornecedorRepository.findById(id).orElse(null);
	}
	
	public List<Fornecedor> getAllFornecedors(){
		return fornecedorRepository.findAll();
	}
	
	public void deleteFornecedorById(Long id) {
		fornecedorRepository.deleteById(id);
	}
	
	public Fornecedor updateFornecedor(Long id, Fornecedor updatedFornecedor) {
		Optional<Fornecedor> existentFornecedor = fornecedorRepository.findById(id);
		if (existentFornecedor.isPresent()){
			Fornecedor fornecedor = existentFornecedor.get();
			fornecedor.setRazaoSocial(updatedFornecedor.getRazaoSocial());
			fornecedor.setNomeFantasia(updatedFornecedor.getNomeFantasia());
			fornecedor.setCnpj(updatedFornecedor.getCpnj());
			fornecedor.setName(updatedFornecedor.getEndereco());
			fornecedor.setFone(updatedFornecedor.getFone());
			fornecedor.setEmail(updatedFornecedor.getEmail());
			fornecedor.setInscricaoEstadual(updatedFornecedor.getInscricaoEstadual());
			return fornecedorRepository.save(fornecedor);
		} else {
			return null;
		}
	}
}