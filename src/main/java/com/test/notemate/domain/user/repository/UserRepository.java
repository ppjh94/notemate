package com.test.notemate.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.notemate.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	/* 회원가입 시 이메일 중복 확인 */
	boolean existsByEmail(String email);
	
	/* 로그인 시 사용자 조회 */
	Optional<User> findByEmail(String email);
	
}
