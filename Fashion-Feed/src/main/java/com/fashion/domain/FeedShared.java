package com.fashion.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fashion.dto.FeedSharedDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class FeedShared {

	@Id @GeneratedValue
	private Long id;
	private LocalDateTime date;
	private Long userid;
	@Enumerated(EnumType.STRING)
	private SharedTarget target;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "feed")
	private Feed feed;
	
	public void setFeed(Feed feed) {
		this.feed = feed;
		feed.getShares().add(this);
	}
	
	public static FeedShared createFeedShare(Feed feed, SharedTarget targetSns, FeedSharedDto feedSharedDto) {
		
		FeedShared feedShared = new FeedShared();
		feedShared.setFeed(feed);
		feedShared.setUserid(feedSharedDto.getUserid());
		feedShared.setTarget(targetSns);
		feedShared.setDate(LocalDateTime.now());
		return feedShared;
	}
}
