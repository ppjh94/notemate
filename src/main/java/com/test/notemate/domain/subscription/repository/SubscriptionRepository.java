package com.test.notemate.domain.subscription.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.notemate.domain.subscription.entity.Subscription;
import com.test.notemate.domain.subscription.entity.SubscriptionStatus;
import com.test.notemate.domain.user.entity.User;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

	/* 특정 사용자의 구독 이력 조회 */
	List<Subscription> findByUserOrderByIdDesc(User user);
	
	/* 특정 사용자의 가장 최근 ACTIVE 구독 조회 */
	Optional<Subscription> findTopByUserAndStatusOrderByIdDesc(User user, SubscriptionStatus status);
	
}
