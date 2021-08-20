package com.bookstore.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Classe relativa a entidade Categoria
 *
 */
@Entity
@Table(name = "TB_CATEGORIA")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Digite o nome da categoria")
	@Size(min = 2, message = "O nome deve conter no mínimo {min} caracteres")
	@Column(name = "NOME")
	private String nome;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( name = "TB_LIVRO_CATEGORIA",
			   joinColumns = @JoinColumn(name = "FK_CATEGORIA"), 
			   inverseJoinColumns = @JoinColumn(name = "FK_LIVRO"))
	private Set<Livro> livros = new LinkedHashSet<Livro>();

	public Categoria(String nome) {
		this.nome = nome;
	}
	
	public Categoria() {
	
	}

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
	
	public Set<Livro> getLivros() {
		return livros;
	}

	public void setLivros(Set<Livro> livros) {
		this.livros = livros;
	}
	
	public void adicionarLivro(Livro livro) {
		
		if(!livros.contains(livro)) {
			livros.add(livro);
		}
				
	}

	public boolean equals(Categoria categoria) {
		if (this == categoria)
			return true;

		if (categoria == null)
			return false;

		if (getClass() != categoria.getClass())
			return false;

		Categoria novaCategoria = (Categoria) categoria;
		if ((id == null && novaCategoria.getId() != null) || !id.equals(novaCategoria.getId()))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	/*
	 * INFORMATICA, ROMANCE, AVENTURA, INFANTIL, TERROR, ENGENHARIA, CLASSICO,
	 * FANTASIA, FICCAO_CIENTIFICA, DISTOPIA, DRAMA, BIOGRAFIA;
	 */
}
