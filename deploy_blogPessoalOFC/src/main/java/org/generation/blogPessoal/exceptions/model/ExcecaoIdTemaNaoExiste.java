package org.generation.blogPessoal.exceptions.model;

/**
 * 
 * Classe responsável por construtor de excessão caso id de tema seja inválido
 * no sistema.
 * 
 * @since 1.0
 * @author Cristiano
 */

public class ExcecaoIdTemaNaoExiste extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcecaoIdTemaNaoExiste(Long idTema) {
		super("Id " + idTema + " não existente!");
	}

}
