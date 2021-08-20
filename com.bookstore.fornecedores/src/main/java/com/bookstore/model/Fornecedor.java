package com.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name = "TB_FORNECEDOR")
public class Fornecedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOME")
	@NotBlank(message = "Insira um nome válido")
	@Size(min = 3, message = "O nome precisa conter no mínimo {min} dígitos")
	private String nome;
	
	@Column(name = "CNPJ")
	@CNPJ(message = "digite um cnpj válido")
	private String cnpj;
	
	@Column(name = "TELEFONE")
	@NotBlank(message = "Insira um número de contato")
	@Size(min = 11, message = "O número precisa conter no mínimo {min} dígitos")
	private String telefone;
	
	@Column(name = "COMPLEMENTO")
	@NotBlank(message = "Insira um complemento a respeito do fornecedor")
	@Size(min = 10, message = "O complemento precisa conter no mínimo {min} dígitos")
	private String complemento;
	
	
	public Fornecedor(
			String nome,
			String cnpj,
			String telefone,
			String complemento) {
		
		this.nome = nome;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.complemento = complemento;

	}
	
	public Fornecedor() {}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	
	
}
