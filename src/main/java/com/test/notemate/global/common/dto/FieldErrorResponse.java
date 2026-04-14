package com.test.notemate.global.common.dto;

public record FieldErrorResponse(
		String field,
		String message
) {
}
