package org.generation.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioControllerTest {
	
	@Autowired
	private TestRestTemplate test;
	private Usuario criarUsuario;
	private Usuario alterarUsuario;
	
	@BeforeAll
	void start() {
	criarUsuario = new Usuario(0L,"Cristiano","cristianoeu@email.com","123456");
	alterarUsuario = new Usuario(1L,"Digo Alterado","rodrigoeu@email.com","12345678");
	
	}
	
	@Disabled
	@Test
	void saveNewUserInBankReturnStatus201() {
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(criarUsuario);
		ResponseEntity<Usuario> response = test.exchange("/prototype/blog/users/salvar",
				HttpMethod.POST,request, Usuario.class);
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
	}
	
	@Disabled
	@Test
	void findAllReturnStatus200() {
		ResponseEntity<String> resposta= test.withBasicAuth("cristianoeu@email.com","1234567")
				.exchange("/prototype/blog/users/allusers", HttpMethod.GET,null,String.class);
		assertEquals(HttpStatus.OK,resposta.getStatusCode());
	}
	
	//@Disabled
	@Test
	void updateUserInBankReturnStatus201() {
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(alterarUsuario);
		ResponseEntity<Usuario> response = test.exchange("/prototype/blog/users/atualizar",
				HttpMethod.PUT,request, Usuario.class);
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
	}
	

	

}
