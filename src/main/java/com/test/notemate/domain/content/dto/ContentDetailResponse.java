package com.test.notemate.domain.content.dto;

import java.time.LocalDateTime;

import com.test.notemate.domain.content.entity.Content;

/* 콘텐츠 상세 화면 응답 DTO */
public record ContentDetailResponse(
		Long id,
		String title,
		String body,
		boolean premium,
		String authorName,
		LocalDateTime createdAt
) {
	
	/**
	 * Content Entity의 모든 값을 가져오는 메서드
	 * @param content
	 * @return
	 */
	public static ContentDetailResponse from(Content content) {
		return new ContentDetailResponse(
				content.getContentId(),
				content.getTitle(),
				content.getBody(),
				content.isPremium(),
				content.getAuthor().getName(),
				content.getCreatedAt()
		);
	}

}
