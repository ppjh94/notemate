package com.test.notemate.domain.content.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.test.notemate.domain.content.dto.ContentDetailResponse;
import com.test.notemate.domain.content.dto.ContentListResponse;
import com.test.notemate.domain.content.service.ContentService;
import com.test.notemate.global.auth.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ContentController {
	
	private final ContentService contentService;
	
	/**
	 * 콘텐츠 목록 페이지
	 * @param model
	 * @return
	 */
	@GetMapping("/contents")
	public String contents(Model model) {
		List<ContentListResponse> contents = contentService.getContents();
		model.addAttribute("contents", contents);
		
		return "content/list";
	}
	
	/**
	 * 콘텐츠 상세 페이지
	 * @param contentId
	 * @param userDetails
	 * @param model
	 * @return
	 */
	@GetMapping("/contents/{contentId}")
	public String contentDetail(@PathVariable Long contentId,
								@AuthenticationPrincipal CustomUserDetails userDetails,
								Model model) {
		
		boolean loggedIn = userDetails != null;
		
		try {
			ContentDetailResponse content = contentService.getContent(contentId, loggedIn);
			model.addAttribute("content", content);
			return "content/detail";
		} catch (IllegalStateException e) {
			model.addAttribute("message", e.getMessage());
			return "content/access-denied";
		}
	}

}


