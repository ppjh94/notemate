package com.test.notemate.domain.plan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.test.notemate.domain.plan.dto.SubscriptionPlanCreateRequest;
import com.test.notemate.domain.plan.service.SubscriptionPlanService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminSubscriptionPlanController {

	private final SubscriptionPlanService subscriptionPlanService;
	
	@GetMapping("/admin/plans/new")
	public String createForm(@ModelAttribute("planCreateRequest") SubscriptionPlanCreateRequest request) {
		return "admin/plan-form";
	}
	
	@PostMapping("/admin/plan")
	public String createPlan(@Valid @ModelAttribute("planCreateRequest") SubscriptionPlanCreateRequest request, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "admin/plan-form";
		}
		
		subscriptionPlanService.createPlan(request);
		
		return "redirect:/plans";
	}
	
}


