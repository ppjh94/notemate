package com.test.notemate.domain.subscription.entity;

import java.time.LocalDateTime;

import com.test.notemate.domain.plan.entity.SubscriptionPlan;
import com.test.notemate.domain.user.entity.User;
import com.test.notemate.global.common.entity.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "subscription")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subscription extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_id")
	private Long statusId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plan_id")
	private SubscriptionPlan plan;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 20)
	private SubscriptionStatus status;
	
	@Column(name = "start_at")
	private LocalDateTime startAt;
	
	@Column(name = "end_at")
	private LocalDateTime endAt;
	
	@Builder
	public Subscription(User user, SubscriptionPlan plan, SubscriptionStatus status, LocalDateTime startAt, LocalDateTime endAt) {
		this.user = user;
		this.plan = plan;
		this.status = status;
		this.startAt = startAt;
		this.endAt = endAt;
	}
	
	public void activate(LocalDateTime startAt, LocalDateTime endAt) {
		this.status = SubscriptionStatus.ACTIVE;
		this.startAt = startAt;
		this.endAt = endAt;
	}
	
	public void cancel() {
		this.status = SubscriptionStatus.CANCELED;
	}
	
	public void exprire() {
		this.status = SubscriptionStatus.EXPIRED;
	}
	
}
