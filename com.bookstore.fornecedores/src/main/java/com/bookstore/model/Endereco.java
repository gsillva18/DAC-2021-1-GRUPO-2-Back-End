package com.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Essa classe é dependente da classe Usuario e tem relacionamento N:1 com a mesma.
 *
 */
@Entity
@Table(name = "TB_ENDERECO")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Size(min = 3, max = 60, message = "A rua deve conter algo entre 3 e 60 caracteres.")
	@Column(name = "RUA")
	private String rua;
	
	@Positive(message = "O número do endereço deve ser maior que 0.")
	@Column(name = "NUMERO")
	private Integer numero;
	
	@Size(min = 3, max = 60, message = "O bairro deve conter algo entre 3 e 60 caracteres.")
	@Column(name = "BAIRRO")
	private String bairro;
	
	@Size(min = 2, max = 2, message = "Selecione um estado válido.")
	@Column(name = "UF")
	private String UF;
	
	@NotBlank
	@Size(min = 3, max = 60, message = "A cidade deve conter algo entre 3 e 60 caracteres.")
	@Column(name = "CIDADE")
	private String cidade;
	
	@NotBlank
	@Size(min = 10, max = 200, message = "O complemento deve ter algo entre 10 e 200 caracteres.")
	@Column(name = "COMPLEMENTO")
	private String complemento;
	
	@Min(value = 10000000, message = "O CEP deve conter 8 números")
    @Max(value = 99999999, message = "O CEP deve conter 8 números")
	@Column(name = "CEP")
	private Long CEP;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getUF() {
		return UF;
	}
	public void setUF(String uF) {
		UF = uF;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public Long getCEP() {
		return CEP;
	}
	public void setCEP(Long cEP) {
		CEP = cEP;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Endereco newEndereco = (Endereco) obj;
		if ((id == null && newEndereco.id != null) || !id.equals(newEndereco.id))
			return false;
		
		return true;
	
	}
	
	@Override
	public String toString() {
		return "Endereço: {"
			+ "\n	id: " + id + ","
			+ "\n	Rua: " + rua + ","
			+ "\n	Numero: " + numero + ", "
			+ "\n	Bairro: " + bairro + ", "
			+ "\n	UF: " + UF + ", "
			+ "\n	Cidade: " + cidade + ", "
			+ "\n	Complemento: " + complemento + ", "
			+ "\n	CEP: " + CEP + ", "
			+ "\n}";
	}
	
}
