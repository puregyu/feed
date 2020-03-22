package com.fashion.dto;

import com.fashion.domain.Feed;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeedCommentDto {
	private Long id;
	private Long userId;
	private String content;
	private Feed feed;
}
