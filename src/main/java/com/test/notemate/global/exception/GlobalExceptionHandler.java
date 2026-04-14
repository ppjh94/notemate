package com.test.notemate.global.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.test.notemate.global.common.dto.ApiResponse;
import com.test.notemate.global.common.dto.FieldErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<List<FieldErrorResponse>>> HandleValidationException(MethodArgumentNotValidException e) {
		
		List<FieldErrorResponse> errors = e.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(this::toFieldErrorResponse)
				.toList();
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(ApiResponse.fail("입력값이 올바르지 않습니다.", errors));
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponse<Void>> handleIllegalArgumnetException(IllegalArgumentException e) {
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(ApiResponse.fail(e.getMessage(), null));
	}
	
	private FieldErrorResponse toFieldErrorResponse(FieldError fieldError) {
		return new FieldErrorResponse(fieldError.getField(), fieldError.getDefaultMessage());
	}
	

}
