package com.test.notemate.domain.payment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.notemate.domain.payment.entity.Payment;
import com.test.notemate.domain.user.entity.User;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	/* orderId로 결제 조회 */
	Optional<Payment> findByOrderId(String orderId);
	
	/* 사용자별 결제 내역 조회 */
	List<Payment> findByUserOrderByPaymentIdDesc(User user);

}
