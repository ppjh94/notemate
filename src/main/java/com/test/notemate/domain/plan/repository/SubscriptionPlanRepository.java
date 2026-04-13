package com.test.notemate.domain.plan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.notemate.domain.plan.entity.SubscriptionPlan;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {

	/* 현재 판매 중인 구독 플랜만 조회 */
	List<SubscriptionPlan> findByActiveTrueOrderByPlanIdAsc();
	
}
