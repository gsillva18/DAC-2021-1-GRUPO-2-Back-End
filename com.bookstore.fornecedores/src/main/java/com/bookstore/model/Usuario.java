package com.bookstore.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Essa classe tem relação 1:N para com as classes Endereço e Pedido.
 *
 */
@Entity
@Table(name = "TB_USUARIO")
public class Usuario implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 97970437639126611L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Size(min = 3, max = 60, message = "O nome deve conter algo entre 3 e 60 caracteres.")
	@Column(name = "NOME")
	private String nome;
	
	@Email(message = "Você deve informar um email com formato válido.")
	@Column(name = "EMAIL")
	private String email;
	
	@Size(min = 8, message = "A senha deve ter, no mínimo, 8 caracteres.")
	@Column(name = "SENHA", columnDefinition = "TEXT")
	private String senha;
	
//	@NotNull(message = "Você deve informar se o usuário é um administrador ou não.")
//	@Column(name = "ADMINISTRADOR")
//	private boolean isAdmin;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable( name = "TB_USUARIO_PERFIL",
	   joinColumns = @JoinColumn(name = "FK_USUARIO"),
	   inverseJoinColumns = @JoinColumn(name = "FK_PERFIL"))
	private List<Perfil> perfis = new ArrayList<Perfil>();
	
	@OneToMany(cascade = CascadeType.MERGE)
	@JoinColumn(name = "USUARIO_FK")
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "usuario")
	private Set<Pedido> pedidos = new LinkedHashSet<Pedido>();

	public Long getId() {
		return id;
	}

	public void setId(Long iD) {
		id = iD;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

//	public boolean isAdmin() {
//		return isAdmin;
//	}
//
//	public void setAdmin(boolean isAdmin) {
//		this.isAdmin = isAdmin;
//	}
	
	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
	
	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil);
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	public void addEndereco(Endereco endereco) {
		enderecos.add(endereco);
	}
	
	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Usuario newUser = (Usuario) obj;
		if ((id == null && newUser.id != null) || !id.equals(newUser.id))
			return false;
		
		return true;
	
	}
	
	@Override
	public String toString() {
		return "Usuario: {"
			+ "\n	id: " + id 
			+ "\n	nome: " + nome 
			+ "\n	email: " + email 
			+ "\n	senha: " + senha 
//			+ "\n	administrador: " + isAdmin 
			+ "\n}";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { // Permissões desse usuário
		return this.perfis;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true; // Impedindo que as contas expirem
	}

	@Override
	public boolean isAccountNonLocked() {
		return true; // Impedindo que as contas sejam bloqueadas 
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true; // Impedindo que as credenciais expirem
	}

	@Override
	public boolean isEnabled() {
		return true; // Mantendo as contas habilitadas
	}
}
