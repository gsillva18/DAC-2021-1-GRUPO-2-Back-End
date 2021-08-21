package com.bookstore.model.dto;

import java.util.List;

public class JwtRespostaDto {
	
	private String token;
	private String type = "Bearer";
	private Long id;
	private String login;
	private String email;
	private String nome;
	private List<String> roles;
	
	public JwtRespostaDto(String token, Long id, String nome, String username, String email, List<String> roles) {
		super();
		this.token = token;
		this.id = id;
		this.nome = nome;
		this.login = username;
		this.email = email;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String acessToken) {
		this.token = acessToken;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
}