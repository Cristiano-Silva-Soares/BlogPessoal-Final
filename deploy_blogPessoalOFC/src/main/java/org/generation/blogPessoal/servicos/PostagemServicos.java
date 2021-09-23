package org.generation.blogPessoal.servicos;

import java.util.Optional;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.generation.blogPessoal.repository.TemaRepository;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostagemServicos {

	@Autowired
	private PostagemRepository repository6;
	@Autowired
	private TemaRepository repository5;
	@Autowired
	private UsuarioRepository repository4;

	/**
	 * Método usado para cadastrar uma nova postagem, validando se o
	 * uausrio(criador) é existente. O idUsuario e o idTema deve ser repassado
	 * dentro do objetoPostagem para que a devida criação seja efetuada. Caso o
	 * idUsuario não for repassado ou não existir, há um retorno Optional.empty().
	 *
	 * @param novaPostagem do tipo Postagem
	 * @return Optional com Postagem
	 * @since 1.0
	 * @author Cristiano
	 */

	public Optional<?> criaPostagem(Postagem novaPostagem) {
		Optional<Tema> existentObject = repository5.findById(novaPostagem.getTemaRelacionado().getIdTema());
		return repository4.findById(novaPostagem.getCriador().getIdUsuario()).map(usuarioExistente -> {
			if (existentObject.isPresent()) {
				novaPostagem.setCriador(usuarioExistente);
				novaPostagem.setTemaRelacionado(existentObject.get());
				return Optional.ofNullable(repository6.save(novaPostagem));

			} else {
				return Optional.empty();
			}
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

	/**
	 * Método utilizado para alterar uma postagem. O mesmo retorna um Optional com
	 * Postagem caso correto, ou Optional.empty() caso idPostagem não exista.
	 * 
	 * @param postagemParaAlterar do tipo Postagem
	 * @return Optional com postagem alterada
	 * @since 1.0
	 * @author Cristiano
	 */

	public Optional<Postagem> alterarPostagem(Postagem postagemParaAlterar) {
		return repository6.findById(postagemParaAlterar.getId()).map(postagemExistente -> {
			postagemExistente.setTitulo(postagemParaAlterar.getTitulo());
			postagemExistente.setTexto(postagemParaAlterar.getTexto());
			return Optional.ofNullable(repository6.save(postagemParaAlterar));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
}
