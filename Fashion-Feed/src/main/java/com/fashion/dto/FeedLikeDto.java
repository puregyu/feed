package com.fashion.dto;

import java.time.LocalDateTime;

import com.fashion.domain.Feed;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeedLikeDto {
	private Long id;
	private Long userId;
	private LocalDateTime date;
	private Feed feed;
}
