package org.generation.blogPessoal.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UsuarioModelTest {
	
	public Usuario user;
	
	@Autowired
	public ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	@BeforeEach
	public void start() {
		user = new Usuario(0L,"Cristiano","cristianoeu@email.com","123456");
	}
	
	@Test
	void validateCorrectUserReturnsTrue() {
		Set<ConstraintViolation<Usuario>> object = validator.validate(user);
		assertTrue(object.isEmpty());
	}
	
	@Test
	void validateWrongUserReturnsFalse() {
		Usuario userFailed = new Usuario(0L,"Cristiano","","");
		Set<ConstraintViolation<Usuario>> object = validator.validate(userFailed);
		assertFalse(object.isEmpty());
	}
	
	
	}


