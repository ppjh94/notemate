package com.test.notemate.domain.content.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.notemate.domain.content.dto.ContentCreateRequest;
import com.test.notemate.domain.content.dto.ContentDetailResponse;
import com.test.notemate.domain.content.dto.ContentListResponse;
import com.test.notemate.domain.content.entity.Content;
import com.test.notemate.domain.content.repository.ContentRepository;
import com.test.notemate.domain.user.entity.User;
import com.test.notemate.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentService {
	
	private final ContentRepository contentRepository;
	private final UserRepository userRepository;
	
	public List<ContentListResponse> getContents() {
		return contentRepository.findAllByOrderByContentIdDesc()
				.stream()
				.map(ContentListResponse::from)
				.toList();
	}
	
	public ContentDetailResponse getContent(Long contentId, boolean loggedIn) {
		Content content = contentRepository.findById(contentId)
				.orElseThrow(() -> new IllegalArgumentException("콘텐츠를 찾을 수 없습니다."));
				
		if (content.isPremium() && !loggedIn) {
			throw new IllegalStateException("프리미업 콘텐츠는 로그인 후 이용할 수 있습니다.");
		}
		
		return ContentDetailResponse.from(content);
	}
	
	@Transactional
	public Long createContent(ContentCreateRequest request, Long authorId) {
		User author = userRepository.findById(authorId)
				.orElseThrow(() -> new IllegalArgumentException("작성자를 찾을 수 없습니다."));
		
		Content content = Content.builder()
			.title(request.title())
			.body(request.body())
			.premium(request.premium())
			.author(author)
			.build();
		
		return contentRepository.save(content).getContentId();
	}
	
}


