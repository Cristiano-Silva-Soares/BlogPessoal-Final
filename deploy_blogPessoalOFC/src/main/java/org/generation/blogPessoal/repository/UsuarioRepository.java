package org.generation.blogPessoal.repository;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório de usuários com metódo utilizado para se fazer pesquisas na coluna "nomeUsuario" ContainingIgnoreCase
 * @param nomeUsuario do tipo String
 * @return List de Usuarios
 * @author Cristiano
 * 
 */

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario,Long>
{
	public List<Usuario>findAllByNomeUsuarioContainingIgnoreCase(String nomeUsuario);
	
	/**
	 * Método utilizado para pesquisar coluna Email
	 * 
	 * @param email do tipo String
	 * @return Optional com Usuario
	 * @author Cristiano
	 * @since 1.0
	 * 
	 */
	Optional<Usuario> findByEmail(String email);
}
