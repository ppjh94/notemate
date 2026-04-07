package com.test.notemate.domain.payment.entity;

import java.time.LocalDateTime;

import com.test.notemate.domain.subscription.entity.Subscription;
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
@Table(name = "payment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Long paymentId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	private Subscription subscription;
	
	@Column(name = "order_id", nullable = false, unique = true, length = 100)
	private String orderId;
	
	@Column(name = "payment_key", nullable = false, length = 200)
	private String paymentKey;
	
	@Column(name = "amount", nullable = false)
	private Integer amount;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 20)
	private PaymentStatus status;

	@Column(name = "requested_at")
	private LocalDateTime requestedAt;
	
	@Column(name = "approved_at")
	private LocalDateTime approvedAt;
	
	@Builder
	public Payment(User user, Subscription subscription, String orderId, String paymentKey, Integer amount, PaymentStatus status, LocalDateTime requestedAt, LocalDateTime approvedAt) {
		this.user = user;
		this.subscription = subscription;
		this.orderId = orderId;
		this.paymentKey = paymentKey;
		this.amount = amount;
		this.status = status;
		this.requestedAt = requestedAt;
		this.approvedAt = approvedAt;
	}
	
	public void markReady(LocalDateTime requestedAt) {
		this.status = PaymentStatus.READY;
		this.requestedAt = requestedAt;
	}
	
	public void markSuccess(String paymentKey, LocalDateTime approvedAt) {
        this.status = PaymentStatus.SUCCESS;
        this.paymentKey = paymentKey;
        this.approvedAt = approvedAt;
    }

    public void markFailed() {
        this.status = PaymentStatus.FAILED;
    }

    public void cancel() {
        this.status = PaymentStatus.CANCELED;
    }

    public void connectSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
	
}


