package com.test.notemate.domain.subscription.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.test.notemate.domain.subscription.service.SubscriptionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminSubscriptionController {
	
	private final SubscriptionService subscriptionService;
	
	@PostMapping("/admin/subscription/expire")
	public String expireSubscription(Model model) {
		int expiredCount = subscriptionService.expireSubscriptions();
		
		model.addAttribute("message", expiredCount + "건의 구독을 만료처리했습니다.");
		
		return "subscription/result";
	}

}
