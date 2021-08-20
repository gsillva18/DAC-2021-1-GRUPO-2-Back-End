package com.bookstore.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * @author NPG
 *
 */
@Entity
@Table(name = "TB_PERFIL")
public class Perfil implements GrantedAuthority {

	private static final long serialVersionUID = -7008932640209485151L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOME_PERFIL")
	private String nomePerfil;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "perfis")
	private Set<Usuario> usuarios = new LinkedHashSet<Usuario>();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNomePerfil() {
		return nomePerfil;
	}
	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}
	@Override
	public String getAuthority() {
		return nomePerfil;
	}
}
