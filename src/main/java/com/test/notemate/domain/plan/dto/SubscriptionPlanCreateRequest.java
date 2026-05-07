package com.test.notemate.domain.plan.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SubscriptionPlanCreateRequest(

		@NotBlank(message = "플랜명은 필수입니다.")
		@Size(max = 50, message = "플랜명은 50자 이하여야 합니다.")
		String name,
		
		@NotNull(message = "가격은 필수입니다.")
		@Min(value = 0, message = "가격은 0원 이상이어야 합니다.")
		Integer price,
		
		@NotNull(message = "구독 기간은 필수입니다.")
		@Min(value = 1, message = "구독 기간은 1일 이상이어야 합니다.")
		Integer durationDays,
		
		@Size(max = 255, message = "설명은 255자 이하여야 합니다.")
		String description,
		
		boolean active
) {

}
