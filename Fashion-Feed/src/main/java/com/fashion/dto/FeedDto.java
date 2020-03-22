package com.fashion.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fashion.domain.FeedComment;
import com.fashion.domain.FeedLike;
import com.fashion.domain.FeedShared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedDto {
	private Long id;
	private LocalDateTime date;
	private String mdPhoto;
	private String	 mdName;
	private String content;
	private int countOfComment;
	private int countOfLike;
	private int countOfShared;
	
	private List<FeedComment> comments = new ArrayList<>();
	private List<FeedLike> likes = new ArrayList<>();
	private List<FeedShared> shares = new ArrayList<>();
}
