package com.test.notemate.domain.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.notemate.domain.user.dto.SignupRequest;
import com.test.notemate.domain.user.entity.User;
import com.test.notemate.domain.user.entity.UserRole;
import com.test.notemate.domain.user.repository.UserRepository;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	public Long signup(SignupRequest request) {
		validateDuplicateEmail(request.email());
		
		User user = User.builder()
				.email(request.email())
				.password(passwordEncoder.encode(request.password()))
				.name(request.name())
				.role(UserRole.USER)
				.build();
		
		return userRepository.save(user).getUserId();
	}

	private void validateDuplicateEmail(String email) {
		if (userRepository.existsByEmail(email)) {
			throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
		}
	}

}
