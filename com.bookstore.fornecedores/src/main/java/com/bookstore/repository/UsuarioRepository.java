package com.bookstore.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.model.Livro;
import com.bookstore.model.Pedido;
import com.bookstore.model.Usuario;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 *
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	/**
	 * Metodo que busca todos os usuarios cadastrados com o nome passado por parametro.
	 * @param nome Nome do usuário que deseja-se pesquisar.
	 * @return Uma lista com os usuarios encontrados.
	 */
	public List<Usuario> findByNome(String nome);
	
	/**
	 * Metodo que busca todos os usuario cadastrados pelo email passado por parametro.
	 * @param email Email do usuário que deseja-se pesquisar.
	 * @return Uma lista com os usuarios encontrados.
	 */
	public List<Usuario> findByEmailIs(String email);
	
	/**
	 * Método usado para recuperar um optionar de usuário com base no email informado
	 * @param email Email do usuário que deseja-se pesquisar.
	 * @return O optional de usuário
	 */
	public Optional<Usuario> findByEmail(String email);
	
	
	//Essa query faz uma listagem por pedidos finalizados ou cancelados do usuario
	@Query(value = "select * from tb_pedido where status_pedido = 'Cancelado' or status_pedido = 'Finalizado' and usuario_id = :id", nativeQuery = true)
	public List<Pedido> listarPedidosFinalizadosDoUsuario(@Param("id") Long id);
	
	
	
	
	
}
