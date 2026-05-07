package com.test.notemate.domain.plan.dto;

import com.test.notemate.domain.plan.entity.SubscriptionPlan;

public record SubscriptionPlanResponse(
		Long id,
		String name,
		Integer price,
		Integer durationDays,
		String description
) {
	
	public static SubscriptionPlanResponse from(SubscriptionPlan plan) {
		return new SubscriptionPlanResponse(
				plan.getPlanId(),
				plan.getPlanName(),
				plan.getPrice(),
				plan.getDurationDays(),
				plan.getDescription()
		);
	}

}
