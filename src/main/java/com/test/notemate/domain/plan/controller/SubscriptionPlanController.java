package com.test.notemate.domain.plan.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.test.notemate.domain.plan.dto.SubscriptionPlanResponse;
import com.test.notemate.domain.plan.service.SubscriptionPlanService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SubscriptionPlanController {
	
	private final SubscriptionPlanService subscriptionPlanService;
	
	@GetMapping("/plans")
	public String plans(Model model) {
		List<SubscriptionPlanResponse> plans = subscriptionPlanService.getActivePlans();
		model.addAttribute("plans", plans);
		
		return "plan/list";
	}

}
