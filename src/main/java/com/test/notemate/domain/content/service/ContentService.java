package com.test.notemate.domain.content.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.notemate.domain.content.dto.ContentCreateRequest;
import com.test.notemate.domain.content.dto.ContentDetailResponse;
import com.test.notemate.domain.content.dto.ContentListResponse;
import com.test.notemate.domain.content.entity.Content;
import com.test.notemate.domain.content.repository.ContentRepository;
import com.test.notemate.domain.subscription.service.SubscriptionService;
import com.test.notemate.domain.user.entity.User;
import com.test.notemate.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentService {
	
	private final ContentRepository contentRepository;
	private final UserRepository userRepository;
	private final SubscriptionService subscriptionService;
	
	public List<ContentListResponse> getContents() {
		return contentRepository.findAllByOrderByContentIdDesc()
				.stream()
				.map(ContentListResponse::from)
				.toList();
	}
	
	public ContentDetailResponse getContent(Long contentId, Long userId) {
		Content content = contentRepository.findById(contentId)
				.orElseThrow(() -> new IllegalArgumentException("콘텐츠를 찾을 수 없습니다."));
				
		validateContentAccess(content, userId);
		
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
	
	private void validateContentAccess(Content content, Long userId) {
		if (!content.isPremium()) {
			return;
		}
		
		if (userId == null) {
			throw new IllegalStateException("프리미엄 콘텐츠는 로그인 후 구독해야 이용할 수 있습니다.");
		}
		
		boolean hasActiveSubscription = subscriptionService.hasActiveSubscription(userId);
		
		if (!hasActiveSubscription) {
			throw new IllegalStateException("프리미엄 콘텐츠는 구독 후 이용할 수 있습니다.");
		}
	}
	
}


