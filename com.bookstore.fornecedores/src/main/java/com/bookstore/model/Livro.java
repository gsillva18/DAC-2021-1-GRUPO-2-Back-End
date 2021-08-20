package com.bookstore.model;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.ISBN.Type;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "TB_LIVRO")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Insira a quantidade desse livro no estoque")
	@PositiveOrZero(message = "Insira apenas números positivos")
	@Column(name = "QNTD_ESTOQUE")
	private Integer quantidadeEmEstoque;
	
	@ISBN(type = Type.ISBN_13, message = "Informe um ISBN válido")
	@NotBlank(message = "Digite o ISBN")
	@Column(name = "ISBN")
	private String isbn;

	@NotBlank(message = "Digite o título do livro")
	@Size(min = 2, message = "O título deve conter no mínimo {min} caracteres")
	@Column(name = "TITULO")
	private String titulo;

	@NotBlank(message = "Descreva algo sobre o livro")
	@Size(min = 5, message = "A descrição deve conter no mínimo {min} caracteres")
	@Column(name = "DESCRICAO", columnDefinition = "LONGTEXT")
	private String descricao;

	@NotNull(message = "Insira o preço")
	@Positive(message = "O preço deve ser maior que zero")
	@Column(name = "PRECO")
	private BigDecimal preco;

	@NotBlank(message = "Adicione a URL da imagem da capa do livro")
	@URL(message = "Insira uma url correta")
	@Column(name="IMAGEM_CAPA")
	private String urlImagemCapa;

	@NotNull(message = "Insira a edição")
	@Positive(message = "A edição deve ser maior que zero")
	@Column(name = "EDICAO")
	private Integer edicao;
	
	@NotNull(message = "Insira o ano de publicação")
	@Min(value = 1000, message = "Insira um ano de publicação válido")
	@Column(name = "ANO_PUBLICACAO")
	private Integer anoPublicacao;
	
//  Não excluir estas anotações
//	@ElementCollection(fetch = FetchType.LAZY, targetClass = Categoria.class)
//	@Enumerated(EnumType.STRING)
//	@Column(name = "CATEGORIA", length = 30)
//	@JoinTable(name = "TB_CATEGORIA")
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "livros")
	private Set<Categoria> categorias = new LinkedHashSet<Categoria>();

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "livros")
	private Set<Autor> autores = new LinkedHashSet<Autor>();
	
	@NotNull(message = "Escolha uma editora")
	@ManyToOne
	@JoinColumn(name = "EDITORA_FK")
	private Editora editora;

	public Livro(
			String isbn,
			String titulo,
			String descricao,
			BigDecimal preco,
			Integer edicao,
			Integer anoPublicacao,
			Integer quantidadeEmEstoque) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.descricao = descricao;
		this.preco = preco;
		this.edicao = edicao;
		this.anoPublicacao = anoPublicacao;
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}
	
	public Livro() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String iSBN) {
		isbn = iSBN;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	public String getUrlImagemCapa() {
		return urlImagemCapa;
	}

	public void setUrlImagemCapa(String urlImagemCapa) {
		this.urlImagemCapa = urlImagemCapa;
	}

	public Integer getEdicao() {
		return edicao;
	}
	public void setEdicao(Integer edicao) {
		this.edicao = edicao;
	}

	public Integer getAnoPublicacao() {
		return anoPublicacao;
	}
	public void setAnoPublicacao(Integer anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public Set<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void addCategoria(Categoria categoria) {
		this.categorias.add(categoria);
	}

	public Set<Autor> getAutores() {
		return autores;
	}

	public void setAutores(Set<Autor> autores) {
		this.autores = autores;
	}
			
	public Integer getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}

	public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public boolean equals(Livro livro) {
		if (this == livro)
			return true;

		if (livro == null)
			return false;

		if (getClass() != livro.getClass())
			return false;

		Livro novoLivro = (Livro) livro;
		if ((id == null && novoLivro.getId() != null) || !id.equals(novoLivro.getId()))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "\n\nDados do Livro " + titulo + ": "
				+ "\nISBN: " + isbn
				+ "\nDescrição: " + descricao
				+ "\nPreço: R$" + preco
				+ "\nEdição: " + edicao
				+ "\nAno de Publicação: " + anoPublicacao
				+ "\nQuantidade disponível no estoque: " + quantidadeEmEstoque + " unidades";
	}
}
