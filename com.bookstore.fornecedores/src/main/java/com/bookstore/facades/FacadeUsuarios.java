package com.bookstore.facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.model.Endereco;
import com.bookstore.model.Perfil;
import com.bookstore.model.Usuario;
import com.bookstore.service.UsuarioService;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Classe Facade responsável por métodos relacionados a Usuario. Esse padrão de projeto
 * foi escolhido para ser implementado, pois o mesmo busca facilitar a utilização de 
 * métodos de classes distintas que se relacionam entre si, por parte das classes clientes.
 *
 */
@Component
public class FacadeUsuarios {
	
	@Autowired
	private UsuarioService usuarioService;

	/**
	 * Metodo responsavel por criar um objeto(Usuario).
	 * @param nome Nome do usuario
	 * @param email Email do usuario
	 * @param senha Senha do usuario
	 * @return Um objeto(Usuario)
	 * @throws Exception Caso já exista um usuario cadastrado com este endereço de email.
	 */
	public Usuario cadastrarUsuario(
			String nome, 
			String email, 
			String senha,
			boolean isAdmin) throws Exception {
		
		if(estaPresenteNoBd(email))
			throw new Exception("Não pode haver mais de um email cadastrado no sistema.");
		
		Perfil perfil = new Perfil();
		perfil.setNomePerfil(isAdmin ? "ADMIN" : "CLIENT");
		
		Usuario usuarioTemp = new Usuario();
		usuarioTemp.setNome(nome);
		usuarioTemp.setEmail(email);
		usuarioTemp.setSenha(senha);
		usuarioTemp.addPerfil(perfil);
		
		usuarioService.salvar(usuarioTemp);
		return usuarioService.recuperarPeloId(usuarioTemp.getId());
	}
	
	/**
	 * Metodo para facilitar a consulta de Usuario pelo endereço de email.
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public Usuario consultarPorEmail(String email) throws Exception {
		return usuarioService.recuperarPeloEmail(email);
	}
	
	/**
	 * Medoto responsalvel por adicionar Endereços a um Usuario
	 * @param emailUsuario Email do usuario que deseja-se adicionar o endereço.
	 * @param endereco endereço a ser adicionado.
	 * @throws Exception caso o usuario não seja encontrado.
	 */
	public void addEndereco(String emailUsuario , Endereco endereco) throws Exception {
		Usuario userTemp = usuarioService.recuperarPeloEmail(emailUsuario);
		userTemp.addEndereco(endereco);
		usuarioService.atualizar(userTemp);
	}
	
	/**
	 * Metodo que verifica se existe um usuario já cadastrado com o endereço de email passado como parametro.
	 * @param email Email a ser verificado.
	 * @return Um valor booleano, se existe ou não um usuario cadastrado com o endereço de email.
	 */
	public boolean estaPresenteNoBd(String email) {
		return usuarioService.verificarExistencia(email);
	}
}
 