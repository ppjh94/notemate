package com.test.notemate.global.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.test.notemate.global.auth.CustomUserDetails;

@Controller
public class HomeController {

	/**
	 * 로그인한 User의 이메일과 권한 값을 검증 후 페이지를 반환
	 * @param userDetails
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String home(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		
		if (userDetails != null) {
			model.addAttribute("email", userDetails.getEmail());
			model.addAttribute("role", userDetails.getRole().name());
		}
		
		return "home";
	}
	
}
