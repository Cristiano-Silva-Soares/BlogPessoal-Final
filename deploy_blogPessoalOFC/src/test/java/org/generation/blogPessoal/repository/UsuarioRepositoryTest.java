package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository repository;

	@BeforeEach
	void start() {
		Usuario objectI = new Usuario(0L,"Cristiano Silva", "cristianoeu@email.com", "123456");

		if (!repository.findByEmail(objectI.getEmail()).isPresent())
			repository.save(objectI);

		Usuario objectII = new Usuario(0L,"Nicole Silva", "nicoleoeu@email.com", "645321");

		if (!repository.findByEmail(objectII.getEmail()).isPresent()) 	
			repository.save(objectII);
		
	
	}

	@Test
	void searchForExisitingEmailReturnsTrue() {
		Usuario objectCristiano = repository.findByEmail("cristianoeu@email.com").get();
		assertTrue(objectCristiano.getEmail().equals("cristianoeu@email.com"));
	}

	@Test
	void findAllByNomeUsuarioContainingIgnoreCaseReturnTwoUsers() {
		List<Usuario> objectLista = repository.findAllByNomeUsuarioContainingIgnoreCase("Silva");
		assertEquals(2, objectLista.size());
	}

	@AfterAll
	void end() {
		System.out.println("Teste finalizado!");
	}

}
