package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.exceptions.model.ExcecaoIdPostagemNaoExistente;
import org.generation.blogPessoal.exceptions.model.ExcecaoIdUsuarioOuIdTemaNaoExistente;
import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.generation.blogPessoal.servicos.PostagemServicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/v1.5/blog/post")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	@Autowired
	private PostagemRepository repositoryP;
	@Autowired
	private PostagemServicos repositoryS;

	@GetMapping("/allposts")
	public ResponseEntity<List<Postagem>> GetAll() {
		List<Postagem> objetoPost = repositoryP.findAll();

		if (objetoPost.isEmpty()) {

			return ResponseEntity.status(204).build();

		} else {

			return ResponseEntity.status(200).body(objetoPost);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<Object> salvar(@Valid @RequestBody Postagem novaPostagem) {
		Optional<?> objectSaved = repositoryS.criaPostagem(novaPostagem);

		if (objectSaved.isPresent()) {
			return ResponseEntity.status(201).body(objectSaved);

		} else {

			throw new ExcecaoIdUsuarioOuIdTemaNaoExistente();
		}
	}

	@GetMapping("/searchid/{id_postagem}")
	public ResponseEntity<Postagem> buscarPorId(@PathVariable(value = "id_postagem") Long idPostagem) {
		Optional<Postagem> objetoPostI = repositoryP.findById(idPostagem);

		if (objetoPostI.isPresent()) {
			return ResponseEntity.status(200).body(objetoPostI.get());

		} else {

			return ResponseEntity.status(204).build();
		}
	}

	@GetMapping("/searchtitle/{titulo}")
	public ResponseEntity<List<Postagem>> buscaPorTituloI(@PathVariable(value = "titulo") String titulo) {
		List<Postagem> objetoPostII = repositoryP.findAllByTituloContainingIgnoreCase(titulo);

		if (objetoPostII.isEmpty()) {
			return ResponseEntity.status(204).build();

		} else {

			return ResponseEntity.status(200).body(objetoPostII);
		}
	}

	@GetMapping("/search")
	public ResponseEntity<List<Postagem>> buscarPorTituloII(@RequestParam(defaultValue = "") String titulo) {
		List<Postagem> objetoPostIII = repositoryP.findAllByTituloContainingIgnoreCase(titulo);

		if (objetoPostIII.isEmpty()) {
			return ResponseEntity.status(204).build();

		} else {

			return ResponseEntity.status(200).body(objetoPostIII);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Object> atualizar(@Valid @RequestBody Postagem postagemParaAtualizar) {
		Optional<Postagem> objectUpdate = repositoryS.alterarPostagem(postagemParaAtualizar);

		if (objectUpdate.isPresent()) {
			return ResponseEntity.status(201).body(objectUpdate.get());

		} else {

			throw new ExcecaoIdPostagemNaoExistente(postagemParaAtualizar.getId());
		}
	}

	@DeleteMapping("/delete/{id_Postagem}")
	public void deletarPostagemPorId(@PathVariable(value = "id_Postagem") long idPostagem) {
		repositoryP.deleteById(idPostagem);
	}

}
