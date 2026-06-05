package com.test.notemate.domain.plan.entity;

import com.test.notemate.global.common.entity.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "subscription_plan")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubscriptionPlan extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "plan_id")
	private Long planId;
	
	@Column(name = "plan_name", nullable = false, length = 50)
	private String planName;
	
	@Column(name = "price", nullable = false)
	private Integer price;

	@Column(name = "duration_days", nullable = false)
	private Integer durationDays;
	
	@Column(name = "description", length = 255)
	private String description;
	
	@Column(name = "is_active", nullable = false)
	private boolean active;
	
	@Builder
	public SubscriptionPlan(String planName, Integer price, Integer durationDays, String description, boolean active) {
		this.planName = planName;
		this.price = price;
		this.durationDays = durationDays;
		this.description = description;
		this.active = active;
	}
	
	public void update(String planName, Integer price, Integer durationDays, String description, boolean active) {
		this.planName = planName;
		this.price = price;
		this.durationDays = durationDays;
		this.description = description;
		this.active = active;
	}
	
}


