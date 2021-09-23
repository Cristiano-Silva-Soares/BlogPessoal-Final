package org.generation.blogPessoal.model.utilities;

/**
 * Classe responsável por validar o acesso do usuário no sistema. É necessário
 * que seja passado pelo usuário o email e a sua senha de autenticação.
 * 
 * @author Cristiano
 * @since Prototype
 * 
 */

public class UsuarioDTO {
	
	
	private String email;

	private String senha;

	private Long idUsuario;
	private String nomeUsuario;
	private String token;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
