package com.fashion.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.fashion.dto.FeedsDto;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Feed {

	@Id @GeneratedValue
	private Long id;
	private LocalDateTime date;
	private String mdPhoto;
	private String	 mdName;
	@Lob
	private String content;
	private int countOfComment;
	private int countOfLike;
	private int countOfShared;
	
	@OneToMany(mappedBy = "feed")
	private List<FeedComment> comments = new ArrayList<>();
	
	@OneToMany(mappedBy = "feed")
	private List<FeedLike> likes = new ArrayList<>();
	
	@OneToMany(mappedBy = "feed")
	private List<FeedShared> shares = new ArrayList<>();
	
	public static Feed createFeed(FeedsDto feedDto) {
		Feed feed = new Feed();
		return feed;
	}
	
	public void addCountOfCommnet() {
		countOfComment = countOfComment+1;
	}
	
	public void removeCountOfCommnet() {
		countOfComment = countOfComment-1;
	}
	
	public void addCountOfLike() {
		countOfLike = countOfLike+1;
	}
	
	public void removeCountOfLike() {
		countOfLike = countOfLike-1;
	}

	public void addCountOfShared() {
		countOfShared = countOfShared+1;
	}
}
