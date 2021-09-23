package org.generation.blogPessoal.exceptions.model;

/**
 * Classe responsável por construtor de excecao caso email seja existente no
 * cadastro de um novo usuario
 * 
 * @since 1.0
 * @author Cristiano
 *
 */
public class ExcecaoEmailExistente extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcecaoEmailExistente(String emailExistente) {
		super("Email "+emailExistente+ " já existente...");
	}

}
