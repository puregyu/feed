package com.fashion.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class FeedLike {
	
	@Id @GeneratedValue
	private Long id;
	private Long userId;
	private LocalDateTime date;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "feed")
	private Feed feed;
	
	public void setFeed(Feed feed) {
		this.feed = feed;
		feed.getLikes().add(this);
	}
	
	public static FeedLike createFeedLike(Feed feed, Long userId) {
		FeedLike feedLike = new FeedLike();
		feedLike.setFeed(feed);
		feedLike.setUserId(userId);
		feedLike.setDate(LocalDateTime.now());
		return feedLike;
	}

}
