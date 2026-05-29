package com.test.notemate.domain.subscription.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.notemate.domain.plan.entity.SubscriptionPlan;
import com.test.notemate.domain.plan.repository.SubscriptionPlanRepository;
import com.test.notemate.domain.subscription.dto.SubscriptionResponse;
import com.test.notemate.domain.subscription.entity.Subscription;
import com.test.notemate.domain.subscription.entity.SubscriptionStatus;
import com.test.notemate.domain.subscription.repository.SubscriptionRepository;
import com.test.notemate.domain.user.entity.User;
import com.test.notemate.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionService {
	
	private final SubscriptionRepository subscriptionRepository;
	private final SubscriptionPlanRepository subscriptionPlanRepository;
	private final UserRepository userRepository;
	
	@Transactional
	public Long subscribe(Long userId, Long planId) {
		User user = findUser(userId);
		SubscriptionPlan plan = findActivePlan(planId);
		
		vailddateNotAlreadySubscribed(user);
		
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime endAt = now.plusDays(plan.getDurationDays());
		
		Subscription subscription = Subscription.builder()
				.user(user)
				.plan(plan)
				.status(SubscriptionStatus.ACTIVE)
				.startAt(now)
				.endAt(endAt)
				.build();
				
		return subscriptionRepository.save(subscription).getStatusId();
	}
	
	public Optional<SubscriptionResponse> getMyActiveSubscription(Long userId) {
		User user = findUser(userId);
		
		return subscriptionRepository
				.findTopByUserAndStatusOrderByStatusIdDesc(user, SubscriptionStatus.ACTIVE)
				.map(SubscriptionResponse::from);
	}
	
	private User findUser(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
	}
	
	private SubscriptionPlan findActivePlan(Long planId) {
		SubscriptionPlan plan = subscriptionPlanRepository.findById(planId)
				.orElseThrow(() -> new IllegalArgumentException("구독 플랜을 찾을 수 없습니다."));
		if (!plan.isActive()) {
			throw new IllegalStateException("현재 판매 중인 구독 플랜이 아닙니다.");
		}
		
		return plan;
	}

	private void vailddateNotAlreadySubscribed(User user) {
		boolean alreadySubscribed = subscriptionRepository.existsByUserAndStatus(user, SubscriptionStatus.ACTIVE);
		if (alreadySubscribed) {
			throw new IllegalStateException("이미 활성화된 구독이 있습니다.");
		}
		
	}



	
	
}
