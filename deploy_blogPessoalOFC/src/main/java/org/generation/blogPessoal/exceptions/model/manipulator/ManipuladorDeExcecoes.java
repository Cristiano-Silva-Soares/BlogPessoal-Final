package org.generation.blogPessoal.exceptions.model.manipulator;

import java.util.HashMap;
import java.util.Map;

import org.generation.blogPessoal.exceptions.model.ExcecaoEmailExistente;
import org.generation.blogPessoal.exceptions.model.ExcecaoErroEmailOuSenhaExistente;
import org.generation.blogPessoal.exceptions.model.ExcecaoIdPostagemNaoExistente;
import org.generation.blogPessoal.exceptions.model.ExcecaoIdTemaNaoExiste;
import org.generation.blogPessoal.exceptions.model.ExcecaoIdUsuarioNaoExistente;
import org.generation.blogPessoal.exceptions.model.ExcecaoIdUsuarioOuIdTemaNaoExistente;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe reponsável pelo gerenciamento dos retornos em caso de erros
 * excepcionais.
 * 
 * @since 1.0
 * @author Cristiano
 */

@ControllerAdvice
public class ManipuladorDeExcecoes {

	/**
	 * Metódo responsável por alterar mensagem de erro em BAD_REQUEST, caso o email
	 * cadastrado já exista no sistema, á saida será 400.
	 * 
	 * @param Do tipo ExcecaoEmailExistente
	 * @return Map com a resposta padronizada
	 */

	@ExceptionHandler(ExcecaoEmailExistente.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> emailExistenteNotFoundHandler(ExcecaoEmailExistente e) {
		Map<String, String> response = new HashMap<>();
		response.put("status", "400");
		response.put("mensagem", "Tente um e-mail diferente!");
		response.put("problema", e.getMessage());

		return response;
	}

	/**
	 * Metódo responsável por retornar excecção de e-mail ou senha inválidos na
	 * solicitação de login.
	 * 
	 * @param 'e' do tipo ExcecaoErroEmailOuSenha
	 * @return Map com resposta padronizada
	 */

	@ExceptionHandler(ExcecaoErroEmailOuSenhaExistente.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> emailOuSenhaInvalidoNotFoundHandler(ExcecaoErroEmailOuSenhaExistente e) {
		Map<String, String> response = new HashMap<>();
		response.put("status", "400");
		response.put("mensagem", "Entre com as credenciais validadas ou faça cadastro!");
		response.put("problema", e.getMessage());

		return response;
	}

	/**
	 * Metódo responsável por retornar excecao caso o id do usuario não seja
	 * identificado no sistema.
	 *
	 * @param 'e' do tipo ExcecaoIdUsuarioNaoExistente
	 * @return Map com resposta padronizada
	 */

	@ExceptionHandler(ExcecaoIdUsuarioNaoExistente.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> idUsuarioNotFoundHandler(ExcecaoIdUsuarioNaoExistente e) {
		Map<String, String> response = new HashMap<>();
		response.put("status", "400");
		response.put("mensagem", "Passe o id do usuário como parâmetro!");
		response.put("problema", e.getMessage());

		return response;
	}

	/**
	 * Metódo responsável por retornar excecao caso o IdTema não seja identificado
	 * no sistema.
	 * 
	 * @param 'e' do tipo ExcecaoIdTemaNaoExiste
	 * @return Map com resosta padronizada
	 */

	@ExceptionHandler(ExcecaoIdTemaNaoExiste.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> idTemaInvalidoNotFoundHandler(ExcecaoIdTemaNaoExiste e) {
		Map<String, String> response = new HashMap<>();
		response.put("status", "400");
		response.put("mensagem", "Passe o id do tema como paramêtro!");
		response.put("problema", e.getMessage());

		return response;
	}

	/**
	 * Método responsável por retornar exceção caso o id do usuario ou tema não seja
	 * cadastrado no sistema.
	 * 
	 * @param 'e' do tipo ExcecaoIdUsuarioOuIdTemaNaoExistente
	 * @return Map com resposta padronizada
	 */

	@ExceptionHandler(ExcecaoIdUsuarioOuIdTemaNaoExistente.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> idUsuarioOuTemaInválidoNotFoundHandler(ExcecaoIdUsuarioOuIdTemaNaoExistente e) {
		Map<String, String> response = new HashMap<>();
		response.put("status", "400");
		response.put("mensagem", "Passe o id de usuário ou tema como parâmetro!");
		response.put("problema", e.getMessage());

		return response;
	}

	/**
	 * Metódo responsável por retornar exceção de id da postagem não identificado no
	 * sistema.
	 * 
	 * @param 'e' do tipo ExcecaoIdPostagemNaoExistente
	 * @return Map com resposta padronizada
	 */

	@ExceptionHandler(ExcecaoIdPostagemNaoExistente.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> idPostagemInvalidoNotFoundHandler(ExcecaoIdPostagemNaoExistente e) {
		Map<String, String> response = new HashMap<>();
		response.put("status", "400");
		response.put("mensagem", "Passe o id da postagem como parâmetro!");
		response.put("problema", e.getMessage());

		return response;

	}

}
