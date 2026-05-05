package com.test.notemate.domain.content.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.test.notemate.domain.content.dto.ContentCreateRequest;
import com.test.notemate.domain.content.service.ContentService;
import com.test.notemate.global.auth.CustomUserDetails;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminContentController {
	
	private final ContentService contentService;
	
	@GetMapping("/admin/contents/new")
	public String createForm(@ModelAttribute("contentCreateRequest") ContentCreateRequest request) {
		return "admin/content-form";
	}
	
	@PostMapping("/admin/contents")
	public String createContent(@Valid @ModelAttribute("contentCreateRequest") ContentCreateRequest request,
			BindingResult bindingResult,
			@AuthenticationPrincipal CustomUserDetails userDetails) {
	
		if (bindingResult.hasErrors()) {
			return "admin/content-form";
		}
		
		contentService.createContent(request, userDetails.getId());
		
		return "redirect:/contents";
	}

}
