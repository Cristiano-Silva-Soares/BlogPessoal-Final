package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.exceptions.model.ExcecaoIdTemaNaoExiste;
import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.generation.blogPessoal.servicos.TemaServicos;
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
@RequestMapping("/v1.5/blog/theme")
public class TemaController {
	@Autowired
	private TemaRepository repositoryT;

	@Autowired
	private TemaServicos repositoryS;

	@GetMapping("/allthemes")
	public ResponseEntity<List<Tema>> GetAll() {
		List<Tema> objetoTheme = repositoryT.findAll();

		if (objetoTheme.isEmpty()) {
			return ResponseEntity.status(204).build();

		} else {

			return ResponseEntity.status(200).body(objetoTheme);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<Tema> salvar(@Valid @RequestBody Tema novoTema) {
		return ResponseEntity.status(201).body(repositoryT.save(novoTema));
	}

	@GetMapping("searchid/{id_tema}")
	public ResponseEntity<Tema> buscarIdTema(@PathVariable(value = "id_tema") Long idTema) {
		Optional<Tema> objetoThemeI = repositoryT.findById(idTema);

		if (objetoThemeI.isEmpty()) {
			return ResponseEntity.status(204).build();

		} else {

			return ResponseEntity.status(200).body(objetoThemeI.get());

		}
	}

	@GetMapping("/searchtheme/{tema}")
	public ResponseEntity<List<Tema>> buscaPorTemaI(@PathVariable(value = "tema") String tema) {
		List<Tema> objetoThemeII = repositoryT.findAllByTemaContainingIgnoreCase(tema);

		if (objetoThemeII.isEmpty()) {
			return ResponseEntity.status(204).build();

		} else {

			return ResponseEntity.status(200).body(objetoThemeII);
		}
	}

	@GetMapping("/search")
	public ResponseEntity<List<Tema>> buscarPorTemaII(@RequestParam(defaultValue = "") String tema) {
		List<Tema> objetoThemeIII = repositoryT.findAllByTemaContainingIgnoreCase(tema);

		if (objetoThemeIII.isEmpty()) {
			return ResponseEntity.status(204).build();

		} else {

			return ResponseEntity.status(200).body(objetoThemeIII);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Object> atualizar(@Valid @RequestBody Tema temaParaAtualizar) {
		Optional<Tema> objectTema = repositoryS.alterarTema(temaParaAtualizar);

		if (objectTema.isPresent()) {
			return ResponseEntity.status(201).body(objectTema.get());
		} else {

			throw new ExcecaoIdTemaNaoExiste(temaParaAtualizar.getIdTema());
		}
	}

	@DeleteMapping("/delete/{id_Tema}")
	public void deletarTemaPorId(@PathVariable(value = "id_Tema") Long idTema) {
		repositoryT.deleteById(idTema);
	}
}
