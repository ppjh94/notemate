package com.test.notemate.domain.user.entity;

import com.test.notemate.global.common.entity.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "email", nullable = false, unique = true, length = 100)
	private String email;

	@Column(name = "password", nullable = false, length = 255)
	private String password;
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false, length = 20)
	private UserRole role;
	
	@Builder
	public User(String email, String password, String name, UserRole role) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.role = role;
	}
	
	public void changePassword(String password) {
		this.password = password;
	}
	
	public void changeName(String name) {
		this.name = name;
	}

	public void changeRole(UserRole role) {
		this.role = role;
	}
}



