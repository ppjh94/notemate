package com.test.notemate.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.notemate.domain.user.dto.SignupRequest;
import com.test.notemate.domain.user.dto.SignupResponse;
import com.test.notemate.domain.user.service.UserService;
import com.test.notemate.global.common.dto.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserApiController {
	
	private final UserService userService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<SignupResponse>> signup(@Valid @RequestBody SignupRequest request) {
		Long userId = userService.signup(request);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(ApiResponse.success("회원가입이 완료되었습니다.", new SignupResponse(userId)));
		
	}

}
