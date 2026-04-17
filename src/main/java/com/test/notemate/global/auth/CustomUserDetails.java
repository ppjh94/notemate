package com.test.notemate.global.auth;

import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.test.notemate.domain.user.entity.User;
import com.test.notemate.domain.user.entity.UserRole;

import lombok.Getter;

@Getter
public class CustomUserDetails implements UserDetails {

	private final Long id;
	private final String email;
	private final String password;
	private final UserRole role;
	
	public CustomUserDetails(User user) {
		this.id = user.getUserId();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.role = user.getRole();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
