package com.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.model.Fornecedor;
import com.bookstore.repository.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	
	public void salvar(Fornecedor fornecedor) {
			
		fornecedorRepository.save(fornecedor);
	}
	
	public Fornecedor recuperarPeloId(Long id) throws Exception {
		
		Optional<Fornecedor> optional = fornecedorRepository.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		
		throw new Exception("Fornecedor não encontrado");
		
	}
	
	public List<Fornecedor> recuperarFornecedores(){
		
		return fornecedorRepository.findAll();
	}
	
	public void atualizarFornecedor(Fornecedor fornecedor) throws Exception {
		
		salvar(fornecedor);
		
	}
	
	public void deletarFornecedor(Long id) throws Exception {
		
		recuperarPeloId(id);
		
		fornecedorRepository.deleteById(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
