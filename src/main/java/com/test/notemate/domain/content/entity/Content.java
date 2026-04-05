package com.test.notemate.domain.content.entity;

import com.test.notemate.domain.user.entity.User;
import com.test.notemate.global.common.entity.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "contents")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "content_id")
	private Long contentId;
	
	@Column(name = "title", nullable = false, length = 200)
	private String title;
	
	@Column(name = "body", nullable = false, columnDefinition = "TEXT")
	private String body;
	
	@Column(name = "is_premium", nullable = false)
	private boolean premium;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User author;
	
	@Builder
	public Content(String title, String body, boolean premium, User author) {
		this.title = title;
		this.body = body;
		this.premium = premium;
		this.author = author;
	}
	
	public void update(String title, String body, boolean premium) {
		this.title = title;
		this.body = body;
		this.premium = premium;
	}
	
}


