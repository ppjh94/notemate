package com.test.notemate.domain.payment.dto;

public record PaymentReadyResponse(
		Long paymentId,
		Long statusId,
		String orderId,
		Integer amount,
		String planName
) {

}
