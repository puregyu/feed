package com.fashion.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fashion.dto.FeedCommentDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class FeedComment {

	@Id @GeneratedValue
	private Long id;
	private Long userId;
	private LocalDateTime date;
	private String	 content;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "feed")
	private Feed feed;
	
	public void setFeed(Feed feed) {
		this.feed = feed;
		feed.getComments().add(this);
	}
	
	public static FeedComment createFeedComment(Feed feed, FeedCommentDto feedCommentDto) {
		FeedComment feedComment = new FeedComment();
		feedComment.setFeed(feed);
		
		feedComment.setUserId(feedCommentDto.getUserId());
		feedComment.setDate(LocalDateTime.now());
		feedComment.setContent(feedCommentDto.getContent());
		return feedComment;
	}

	public void updateComment(FeedCommentDto feedCommentDto) {
		this.content = feedCommentDto.getContent();
	}
}
