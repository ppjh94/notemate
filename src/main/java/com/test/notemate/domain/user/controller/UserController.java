package com.test.notemate.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.test.notemate.domain.user.dto.SignupRequest;
import com.test.notemate.domain.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/signup")
	public String signupForm(@ModelAttribute("signupRequest") SignupRequest signupRequest) {
		return "user/signup";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid @ModelAttribute("signupRequest") SignupRequest signupRequest,
						BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "user/signup";
		}
		
		try {
			userService.signup(signupRequest);
		} catch (IllegalArgumentException e) {
			bindingResult.rejectValue("email", "duplicate", e.getMessage());
		}
		
		return "redirect:/login";
	}

}
