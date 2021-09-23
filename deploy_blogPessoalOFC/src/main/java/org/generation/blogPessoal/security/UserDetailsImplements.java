package org.generation.blogPessoal.security;

import java.util.Collection;
import java.util.List;

import org.generation.blogPessoal.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserDetailsImplements implements UserDetails {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String userName;
	private String password;
	private List<GrantedAuthority> autorizacoes;

	public UserDetailsImplements(Usuario user) {
		this.userName = user.getEmail();
		this.password = user.getSenha();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return autorizacoes;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
