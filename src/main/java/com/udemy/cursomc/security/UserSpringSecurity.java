package com.udemy.cursomc.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.udemy.cursomc.domain.enums.Perfil;

public class UserSpringSecurity implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;
	private String senha;
	
	public UserSpringSecurity() {
	}
	
	public UserSpringSecurity(Integer id, String email, Set<Perfil> perfis, String senha ) {
		super();
		this.id = id;
		this.email = email;
		this.authorities = perfis.stream().map(p -> new SimpleGrantedAuthority(p.getDescricao())).collect(Collectors.toList());
		this.senha = senha;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
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
	
	public boolean hasRole(Perfil perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}

}
