package com.bookstore.facades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.model.Fornecedor;
import com.bookstore.service.FornecedorService;

@Component
public class FacadeFornecedor {

	@Autowired
	private FornecedorService fornecedorService;
	
	public void criarFornecedor(Fornecedor fornecedor) {
		
		fornecedorService.salvar(fornecedor);
	}
	
	public Fornecedor recuperarFornecedor(Long id) throws Exception {
		
		return fornecedorService.recuperarPeloId(id);
	}
	
	public List<Fornecedor> recuperarFornecedores(){
				
		return fornecedorService.recuperarFornecedores();
	}
	
	public void atualizarFornecedor(Fornecedor fornecedor, Long id) throws Exception {
				
		Fornecedor fornecedorTemp = recuperarFornecedor(id);
		
		fornecedorTemp.setCnpj(fornecedor.getCnpj());
		fornecedorTemp.setNome(fornecedor.getNome());
		fornecedorTemp.setTelefone(fornecedor.getTelefone());
		fornecedorTemp.setComplemento(fornecedor.getComplemento());
		
		fornecedorService.atualizarFornecedor(fornecedorTemp);
	}
	
	public void deletarFornecedor(Long id) throws Exception {
		
		fornecedorService.deletarFornecedor(id);
	}
	
	public boolean verificarSeTemRegistrosNoBd() {
		return fornecedorService.recuperarFornecedores().size() > 0;
	}
}
