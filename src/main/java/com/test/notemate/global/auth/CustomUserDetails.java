package com.test.notemate.global.auth;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
	
	/**
	 * Security가 사용할 로그인 사용자 정보 객체
	 * @param user
	 */
	public CustomUserDetails(User user) {
		this.id = user.getUserId();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.role = user.getRole();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
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



