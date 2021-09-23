package org.generation.blogPessoal.exceptions.model;

/**
 * Classe pelo construtor da exceção de IdUsuario inválido.
 * 
 * @Since 1.0
 * @author Cristiano
 *
 */
public class ExcecaoIdUsuarioNaoExistente extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExcecaoIdUsuarioNaoExistente(Long idUsuario) {
		super("Id "+idUsuario+" não existente!");
	}

}
