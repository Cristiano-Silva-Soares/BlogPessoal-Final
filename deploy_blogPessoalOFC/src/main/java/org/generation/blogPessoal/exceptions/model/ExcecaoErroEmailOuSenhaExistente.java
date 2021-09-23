package org.generation.blogPessoal.exceptions.model;

/**
 * Classe responsável pelo construtor de excecao caso o email ou senha do
 * usuario estiverem inválidos
 * 
 * @since 1.0
 * @author Cristiano
 */

public class ExcecaoErroEmailOuSenhaExistente extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcecaoErroEmailOuSenhaExistente() {
		super("Email ou senha inválidos!");
	}

}
