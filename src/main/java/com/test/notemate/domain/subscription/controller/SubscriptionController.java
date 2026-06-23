package com.test.notemate.domain.subscription.controller;

import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.test.notemate.domain.subscription.dto.SubscriptionResponse;
import com.test.notemate.domain.subscription.service.SubscriptionService;
import com.test.notemate.global.auth.CustomUserDetails;
import com.test.notemate.global.auth.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SubscriptionController {
	
	private final SubscriptionService subscriptionService;
	
	@PostMapping("/subscriptions/subscribe/{planId}")
	public String subscribe(@PathVariable("planId") Long planId,
							@AuthenticationPrincipal CustomUserDetails userDetails,
							Model model) {
		
		try {
			subscriptionService.subscribe(userDetails.getId(), planId);
			return "redirect:/subscriptions/me";
		} catch (IllegalStateException | IllegalArgumentException e) {
			model.addAttribute("message", e.getMessage());
			return "subscription/error";
		}
	}
	
	@GetMapping("/subscriptions/me")
	public String mySubscription(@AuthenticationPrincipal CustomUserDetails userDetails,
								Model model) {
		
		Optional<SubscriptionResponse> subscription =
				subscriptionService.getMyActiveSubscription(userDetails.getId());
		
		model.addAttribute("subscription", subscription.orElse(null));
		
		return "subscription/my";
	}
	
	/**
	 * 구독 취소 메서드
	 */
	@PostMapping("/subscriptions/cancel")
	public String cancelSubscription(@AuthenticationPrincipal CustomUserDetails userDetails,
									Model model) {
		
		try {
			subscriptionService.cancelMySubscription(userDetails.getId());
			return "redirect:/subscription/me";
		} catch (IllegalStateException | IllegalArgumentException e) {
			model.addAttribute("message", e.getMessage());
			return "subscription/error";
		}
	
	}

}
