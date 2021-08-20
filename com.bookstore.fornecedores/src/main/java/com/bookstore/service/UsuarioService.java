package com.bookstore.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.model.Pedido;
import com.bookstore.model.Usuario;
import com.bookstore.repository.UsuarioRepository;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Essa classe é reposnsável por métodos do CRUD de Usuario.
 *
 *
 */
@Service
public class UsuarioService {
	
	@Autowired
	public UsuarioRepository usuarioRepository;
	
	
	/**
	 * Esse método salva um objeto(Usuario) no banco de dados.
	 * @param usuario Usuario que deseja-se salvar
	 * @throws Exception Caso já exista um usuario cadastrado com endereço de email do usuario passado por parametro. 
	 */
	public void salvar(Usuario usuario) throws Exception {
		if(!verificarExistencia(usuario.getEmail())) {
			usuarioRepository.save(usuario);			
		}else {
			String mensagem = "Ja existe um usuario cadastrado com o email " + usuario.getEmail();
			throw new Exception(mensagem);			
		}
	}
	
	/**
	 * Essa metodo atualiza as informações de um Usuario já cadastrado no banco de dados.
	 * @param usuario Usuario com as atualizações.
	 * @throws Exception Se o usuario não estiver cadastrado ou se o email tenha sido alterado. 
	 */
	public void atualizar(Usuario usuario) throws Exception {
		Usuario userTemp = recuperarPeloId(usuario.getId());
		if(userTemp.equals(usuario)) {
			usuarioRepository.save(usuario);
		}else {
			throw new Exception("O Email nao pode ser altereado");
		}
	}
	
	/**
	 * Esse metodo deleta um objeto(Usuario) do banco de dados.
	 * @param email email do Usuario que deseja-se excluir.
	 * @throws Exception Caso nenhum usuario seja encontrado com o email passado por parametro.
	 */
	public void excluir(String email) throws Exception {
		usuarioRepository.delete(recuperarPeloEmail(email));		
	}
	
	/**
	 * Esse metodo deleta todas as tuplas de Usuario no banco de dados.
	 */
	public void excluirTudo() {
		usuarioRepository.deleteAll();
	}
	
	/**
	 * Esse metodo busca um usuario no banco de dados pelo email.
	 * @param email Email do usuario que deseja-se encontrar
	 * @return Objeto(Usuario) encontrado
	 * @throws Exception Caso nenhum usuario seja encontrado com o email passado por parametro
	 */
	public Usuario recuperarPeloEmail(String email) throws Exception {
		if(usuarioRepository.findByEmailIs(email).size() >= 1) 
			return (Usuario) usuarioRepository.findByEmailIs(email).get(0);
		
		throw new Exception("[ERRO] Usuário " + email + " não encontrado");
		
	}
	
	/**
	 * Esse metodo busca todos os usuaios com o nome iqual ao passado por parametro no banco  de dados.
	 * @param nome nome dos usuarios que deseja-se encontrar.
	 * @return Uma lista de usuarios encontrados
	 * @throws Exception caso nenhum usuario seja encontrado.
	 */
	public List<Usuario> recuperarPeloNome(String nome) throws Exception {
		List<Usuario> usersTemp = usuarioRepository.findByNome(nome);
		if(usersTemp.size()>=1)
			return usersTemp;
		
		throw new Exception("[ERRO] Usuário " + nome + " não encontrado");
	}
	
	public Usuario recuperarPeloId(Long id) throws Exception {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		
		if(optional.isPresent())
			return optional.get();
		
		throw new Exception("[ERRO] Usuário não encontrado");
	}
	
	/**
	 * Esse metodo recupera o total de usuarios cadastrado no banco de dados.
	 * @return Long com a quantidade.
	 */
	public Long quantidadeDeUsuariosCadastrados() {
		return usuarioRepository.count();
	}
	
	public List<Pedido> recuperarPedidosFinalizadosCancelados(Long idUsuario){
		
		return usuarioRepository.listarPedidosFinalizadosDoUsuario(idUsuario);
	}
	
	/**
	 * Esse metodo verifica se existe algum usuario cadastrado com o endereço de email passado por parametro.
	 * @param email Email o qual deseja-se fazer a verificação.
	 * @return retorna um valor booleano, true caso exista um usuario cadastrado com o email passado por parametro e false caso não.
	 */
	public boolean verificarExistencia(String email) {
		try {
			recuperarPeloEmail(email);
		}catch (Exception e){
			return false;
		}
		
		return true;
	}
	
}
