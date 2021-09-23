package org.generation.blogPessoal.exceptions.model;

/**
 * Classe reponsável pelo construtor de excecao caso idPostagem seja inválido.
 * 
 * @since 1.0
 * @author Cristiano
 */
public class ExcecaoIdPostagemNaoExistente extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcecaoIdPostagemNaoExistente(Long idPostagem) {
		super("O Id" + idPostagem + "da postagem é inválido...");
	}
}
