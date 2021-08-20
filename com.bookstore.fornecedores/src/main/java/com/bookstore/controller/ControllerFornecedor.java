package com.bookstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bookstore.facades.FacadeFornecedor;
import com.bookstore.model.Fornecedor;

@RestController
@RequestMapping("/fornecedor")
public class ControllerFornecedor {

	@Autowired
	private FacadeFornecedor facadeFornecedor;

	@GetMapping("/listar")
	public List<Fornecedor> recuperarFornecedores() {

		if(!facadeFornecedor.verificarSeTemRegistrosNoBd()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não há registros de fornecedores.");
		}
		
		return facadeFornecedor.recuperarFornecedores();
	}

	@GetMapping("/consultar/{id}")
	public Fornecedor recuperarFornecedor(@PathVariable("id") Long id) throws Exception {

		try {

			return facadeFornecedor.recuperarFornecedor(id);

		} catch (Exception e) {
			e.printStackTrace();

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@PostMapping("/criar")
	@ResponseStatus(code = HttpStatus.CREATED, reason = "Fornecedor criado com sucesso")
	public void criarFornecedor(@Valid @RequestBody Fornecedor fornecedor, BindingResult result) throws Exception {

		if (!result.hasErrors()) {

			facadeFornecedor.criarFornecedor(fornecedor);

		} else {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, result.toString());
		}

	}

	@PutMapping("/atualizar/{id}")
	@ResponseStatus(code = HttpStatus.OK, reason = "Fornecedor encontrado com sucesso")
	public void atualizarFornecedor(@Valid @RequestBody Fornecedor fornecedor, BindingResult result,
			@PathVariable("id") Long id) throws Exception {

		if (!result.hasErrors()) {

			try {

				facadeFornecedor.atualizarFornecedor(fornecedor, id);

			} catch (Exception e) {

				e.printStackTrace();
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
			}

		} else {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, result.toString());
		}
	}

	@DeleteMapping("/deletar/{id}")
	public void removerFornecedor(@PathVariable("id") Long id) throws Exception {

		try {

			facadeFornecedor.deletarFornecedor(id);

		} catch (Exception e) {
			e.printStackTrace();

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
