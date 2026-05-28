package com.test.notemate.domain.subscription.dto;

import java.time.LocalDateTime;

import com.test.notemate.domain.subscription.entity.Subscription;

public record SubscriptionResponse(
		Long id,
		String planName,
		Integer price,
		Integer durationDays,
		String status,
		LocalDateTime startAt,
		LocalDateTime endAt
) {
	
	public static SubscriptionResponse from(Subscription subscription) {
		return new SubscriptionResponse(
				subscription.getStatusId(),
				subscription.getPlan().getPlanName(),
				subscription.getPlan().getPrice(),
				subscription.getPlan().getDurationDays(),
				subscription.getStatus().name(),
				subscription.getStartAt(),
				subscription.getEndAt()
		);
	}

}


