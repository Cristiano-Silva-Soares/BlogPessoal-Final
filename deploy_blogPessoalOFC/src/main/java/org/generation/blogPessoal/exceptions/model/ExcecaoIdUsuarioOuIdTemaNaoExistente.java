package org.generation.blogPessoal.exceptions.model;

/**
 * Classe responsável por construtuor de exceção caso idUsuario ou idTema sejam
 * inválidos no sistema.
 * 
 * @since 1.0
 * @author Cristiano
 */

public class ExcecaoIdUsuarioOuIdTemaNaoExistente extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcecaoIdUsuarioOuIdTemaNaoExistente() {
		super("O Id do usuario ou tema está inválido!");
	}

}
