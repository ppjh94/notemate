package com.test.notemate.domain.content.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.test.notemate.domain.content.entity.Content;

public interface ContentRepository extends JpaRepository<Content, Long> {

	/* 전체 콘텐츠 조회 */
	List<Content> findAllByOrderByContentIdDesc();
	
	/* 무료 콘텐츠만 조회 */
	List<Content> findByPremiumFalseOrderByContentIdDesc();
	
	/* 프리미엄 콘텐츠만 조회 */
	List<Content> findByPremiumTrueOrderByContentIdDesc();
	
}
