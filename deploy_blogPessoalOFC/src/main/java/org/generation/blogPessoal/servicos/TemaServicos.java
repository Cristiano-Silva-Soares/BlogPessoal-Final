package org.generation.blogPessoal.servicos;

import java.util.Optional;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemaServicos {

	@Autowired
	private TemaRepository repository5;

	/**
	 * Metódo usado para alterar um tema. O mesmo retorna um Optional com tema, caso
	 * correto ou um Optional.empty() caso o idTema digitado não exista.
	 * 
	 * @param temaParaAlterar do tipo Tema
	 * @return Optional com o tema alterado
	 * @since 1.0
	 * @author Cristiano
	 */

	public Optional<Tema> alterarTema(Tema temaParaAlterar) {
		return repository5.findById(temaParaAlterar.getIdTema()).map(temaExistente -> {
			temaExistente.setTema(temaParaAlterar.getTema());
			return Optional.ofNullable(repository5.save(temaExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
}
