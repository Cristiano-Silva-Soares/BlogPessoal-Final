package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.exceptions.model.ExcecaoEmailExistente;
import org.generation.blogPessoal.exceptions.model.ExcecaoErroEmailOuSenhaExistente;
import org.generation.blogPessoal.exceptions.model.ExcecaoIdUsuarioNaoExistente;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.model.utilities.UsuarioDTO;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.generation.blogPessoal.servicos.UsuarioServicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1.5/blog/users")
public class UsuarioController {
	@Autowired
	private UsuarioRepository repositoryU;
	@Autowired
	private UsuarioServicos repositoryS;

	@GetMapping("/allusers")
	public ResponseEntity<List<Usuario>> GetAll() {
		List<Usuario> objetoUsers = repositoryU.findAll();

		if (objetoUsers.isEmpty()) {

			return ResponseEntity.status(204).build();

		} else {

			return ResponseEntity.status(200).body(objetoUsers);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<Object> salvar(@Valid @RequestBody Usuario novoUsuario) {
		Optional<Object> objectOptionalI = repositoryS.cadastrarUsuario(novoUsuario);

		if (objectOptionalI.isPresent()) {
			return ResponseEntity.status(200).body(objectOptionalI);

		} else {

			throw new ExcecaoEmailExistente(novoUsuario.getEmail());
		}
	}

	@PutMapping("/login")
	public ResponseEntity<Object> credenciaisUsuario(@Valid @RequestBody UsuarioDTO usuarioParaAutenticar) {
		Optional<?> objectOptionalII = repositoryS.pegarCredenciaisUsuario(usuarioParaAutenticar);

		if (objectOptionalII.isEmpty()) {
			return ResponseEntity.status(200).body(objectOptionalII.get());

		} else {

			throw new ExcecaoErroEmailOuSenhaExistente();
		}
	}

	@GetMapping("/searchid/{id_usuario}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable(value = "id_usuario") Long idUsuario) {
		Optional<Usuario> objetoUsersI = repositoryU.findById(idUsuario);

		if (objetoUsersI.isPresent()) {
			return ResponseEntity.status(200).body(objetoUsersI.get());

		} else {

			return ResponseEntity.status(204).build();
		}
	}

	@GetMapping("/searchname/{nome_usuario}")
	public ResponseEntity<List<Usuario>> buscaPorNomeI(@PathVariable(value = "nome_usuario") String nome) {
		List<Usuario> objetoUsersII = repositoryU.findAllByNomeUsuarioContainingIgnoreCase(nome);

		if (objetoUsersII.isEmpty()) {
			return ResponseEntity.status(204).build();

		} else {

			return ResponseEntity.status(200).body(objetoUsersII);
		}
	}

	@GetMapping("/search")
	public ResponseEntity<List<Usuario>> buscaPorNomeII(@RequestParam(defaultValue = "") String nome) {
		List<Usuario> objetoUsersIII = repositoryU.findAllByNomeUsuarioContainingIgnoreCase(nome);

		if (objetoUsersIII.isEmpty()) {
			return ResponseEntity.status(204).build();

		} else {

			return ResponseEntity.status(200).body(objetoUsersIII);
		}

	}

	@PutMapping("/update")
	public ResponseEntity<Object> atualizar(@Valid @RequestBody UsuarioDTO usuarioParaAtualizar) {
		Optional<?> objectUsuario = repositoryS.alterarUsuario(usuarioParaAtualizar);

		if (objectUsuario.isPresent()) {
			return ResponseEntity.status(201).body(objectUsuario.get());

		} else {

			throw new ExcecaoIdUsuarioNaoExistente(usuarioParaAtualizar.getIdUsuario());
		}
	}

	@DeleteMapping("/delete/{id_usuario}")
	public ResponseEntity<Object> deletarId(@PathVariable(value = "id_usuario") Long idUsuario) {
		Optional<Usuario> objectDelete = repositoryU.findById(idUsuario);

		if (objectDelete.isPresent()) {
			repositoryU.deleteById(idUsuario);
			return ResponseEntity.status(200).build();

		} else {

			throw new ExcecaoIdUsuarioNaoExistente(idUsuario);
		}
	}

}
