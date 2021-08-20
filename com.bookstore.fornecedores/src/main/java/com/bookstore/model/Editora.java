package com.bookstore.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TB_EDITORA")
public class Editora {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank(message = "Digite o nome da editora")
	@Size(min = 2, message = "O nome deve conter no mínimo {min} caracteres")
	@Column(name = "NOME")
	private String nome;
	
	@NotBlank(message = "Digite o nome da cidade da editora")
	@Size(min = 2, message = "O nome deve conter no mínimo {min} caracteres")
	@Column(name = "CIDADE")
	private String cidade;
	
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "editora")
	private Set<Livro> livros = new LinkedHashSet<Livro>();
	
	public Editora() {

	}
	
	public Editora(String nome, String cidade) {
		this.nome = nome;
		this.cidade = cidade;
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Set<Livro> getLivros() {
		return livros;
	}

	
	public void setLivros(Set<Livro> livros) {
		this.livros = livros;
	}
	
	public void addLivro(Livro livro) {
		this.livros.add(livro);
	}

	public boolean equals(Editora editora) {
		if (this == editora)
			return true;

		if (editora == null)
			return false;

		if (getClass() != editora.getClass())
			return false;

		Editora novaEditora = (Editora) editora;
		if ((id == null && novaEditora.getId() != null) || !id.equals(novaEditora.getId()))
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return "\n\nDados da Editora " + nome + ": "
				+ "\nID: " + id
				+ "\nCidade: " + cidade;
	}
}
