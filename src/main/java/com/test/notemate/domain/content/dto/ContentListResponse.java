package com.test.notemate.domain.content.dto;

import com.test.notemate.domain.content.entity.Content;

/* 콘텐츠 목록 응답 DTO */
public record ContentListResponse(
		Long id,
		String title,
		boolean premium,
		String authorName
) {
	
	/**
	 * Content Entity에서 목록 화면에 띄울 값을 반환하는 객체
	 * @param content
	 * @return
	 */
	public static ContentListResponse from(Content content) {
		return new ContentListResponse(
				content.getContentId(),
				content.getTitle(),
				content.isPremium(),
				content.getAuthor().getName()
		);
	}

}
