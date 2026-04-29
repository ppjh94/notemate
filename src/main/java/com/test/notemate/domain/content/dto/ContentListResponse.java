package com.test.notemate.domain.content.dto;

import com.test.notemate.domain.content.entity.Content;

public record ContentListResponse(
		Long id,
		String title,
		boolean premium,
		String authorName
) {
	
	public static ContentListResponse from(Content content) {
		return new ContentListResponse(
				content.getContentId(),
				content.getTitle(),
				content.isPremium(),
				content.getAuthor().getName()
		);
	}

}
