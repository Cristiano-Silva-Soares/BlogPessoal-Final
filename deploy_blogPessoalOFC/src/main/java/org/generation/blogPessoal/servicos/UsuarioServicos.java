package org.generation.blogPessoal.servicos;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.model.utilities.UsuarioDTO;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicos {
	@Autowired
	private UsuarioRepository repository4;

	/**
	 * Método utilizado para a criação de um novo usuario no sistema
	 * 
	 * @param novoUsuario do tipo Usuario
	 * @return Optional com um Usuario Criado
	 * @author Cristiano
	 * @since Prototype
	 * 
	 */
	public Optional<Object> cadastrarUsuario(Usuario novoUsuario) {
		return repository4.findByEmail(novoUsuario.getEmail()).map(usuarioExistente -> {
			return Optional.empty();
		}).orElseGet(() -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String result = encoder.encode(novoUsuario.getSenha());
			novoUsuario.setSenha(result);
			return Optional.ofNullable(repository4.save(novoUsuario));
		});
	}

	/**
	 * Metódo utilizado para pegar credenciais do usuario com token(Formato
	 * "Basic"), este metódo será utilizado para retornar ao fornt o token utilizado
	 * para ter acesso aos dados do usuário,além de mante-lo igualmente logado no
	 * sistema.
	 *
	 * @param usuarioParaAutenticar do tipo UsuarioLoginDTO; necessário email e
	 *                              senha para a sua validação.
	 * 
	 * @return UsuarioLoginDTO preênchido com informações mais o token.
	 * 
	 * @since Prototype
	 * 
	 * @author Cristiano
	 */

	public Optional<?> pegarCredenciaisUsuario(UsuarioDTO usuarioParaAutenticar) {
		return repository4.findByEmail(usuarioParaAutenticar.getEmail()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			if (encoder.matches(usuarioParaAutenticar.getSenha(), usuarioExistente.getSenha())) {

				String estruturaBasic = usuarioParaAutenticar.getEmail() + ":" + usuarioParaAutenticar.getSenha();
				byte[] autorizacaoBase64 = Base64.encodeBase64(estruturaBasic.getBytes(Charset.forName("US-ASCII")));
				String autorizacaoHeader = "Basic " + new String(autorizacaoBase64);

				usuarioParaAutenticar.setToken(autorizacaoHeader);
				usuarioParaAutenticar.setIdUsuario(usuarioExistente.getIdUsuario());
				usuarioParaAutenticar.setNomeUsuario(usuarioExistente.getNomeUsuario());
				usuarioParaAutenticar.setSenha(usuarioExistente.getSenha());
				usuarioParaAutenticar.setFoto(usuarioExistente.getFoto());
				usuarioParaAutenticar.setTipoUsuario(usuarioExistente.getTipoUsuario());
				return Optional.ofNullable(usuarioParaAutenticar);// Usuario Credenciado.
			} else {

				return Optional.empty();// Caso a senha esteja incorreta ou seja inexistente.
			}

		}).orElseGet(() -> {
			return Optional.empty();// Caso o email esteja incorreto ou seja inexistente.
		});
	}

	/**
	 * Metodo utilizado para alterar um usuario fornecido pelo FRONT, O mesmo
	 * retorna um Optional com entidade Usuario dentro e senha criptografada. Caso
	 * falho retorna um Optional.empty()
	 * 
	 * @param usuarioParaAlterar do tipo Usuario
	 * @return Optional com Usuario Alterado
	 * @since 1.0
	 * @author Cristiano
	 */

	public Optional<?> alterarUsuario(UsuarioDTO usuarioParaAlterar) {
		return repository4.findById(usuarioParaAlterar.getIdUsuario()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(usuarioParaAlterar.getSenha());

			usuarioExistente.setNomeUsuario(usuarioParaAlterar.getNomeUsuario());
			usuarioExistente.setSenha(senhaCriptografada);
			usuarioExistente.setEmail(usuarioParaAlterar.getEmail());
			usuarioExistente.setFoto(usuarioParaAlterar.getFoto());
			usuarioExistente.setTipoUsuario(usuarioParaAlterar.getTipoUsuario());
			return Optional.ofNullable(repository4.save(usuarioExistente));
		}).orElseGet(() -> {

			return Optional.empty();
		});

	}

}
