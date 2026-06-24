package com.test.notemate.domain.subscription.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.notemate.domain.subscription.entity.Subscription;
import com.test.notemate.domain.subscription.entity.SubscriptionStatus;
import com.test.notemate.domain.user.entity.User;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

	/* 특정 사용자의 구독 이력 조회 */
	List<Subscription> findByUserOrderByStatusIdDesc(User user);
	
	/* 특정 사용자의 가장 최근 ACTIVE 구독 조회 */
	Optional<Subscription> findTopByUserAndStatusOrderByStatusIdDesc(User user, SubscriptionStatus status);
	
	/* 사용자의 활성 구독 여부 확인 (중복 구독 방지 역할) */
	boolean existsByUserAndStatus(User user, SubscriptionStatus status);
	
	/* 만료 대상 조회 메서드 추가 */
	List<Subscription> findByStatusAndEndAtBefore(SubscriptionStatus status, LocalDateTime now);
	
}
