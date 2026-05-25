package com.test.notemate.domain.plan.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.notemate.domain.plan.dto.SubscriptionPlanCreateRequest;
import com.test.notemate.domain.plan.dto.SubscriptionPlanResponse;
import com.test.notemate.domain.plan.entity.SubscriptionPlan;
import com.test.notemate.domain.plan.repository.SubscriptionPlanRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionPlanService {
	
	private final SubscriptionPlanRepository subscriptionPlanRepository;
	
	@Transactional
	public Long createPlan(SubscriptionPlanCreateRequest request) {
		SubscriptionPlan plan = SubscriptionPlan.builder()
				.planName(request.name())
				.price(request.price())
				.durationDays(request.durationDays())
				.description(request.description())
				.active(request.active())
				.build();
		
		return subscriptionPlanRepository.save(plan).getPlanId();
	}
	
	public List<SubscriptionPlanResponse> getActivePlans() {
		return subscriptionPlanRepository.findByActiveTrueOrderByPlanIdAsc()
				.stream()
				.map(SubscriptionPlanResponse::from)
				.toList();
	}

}
