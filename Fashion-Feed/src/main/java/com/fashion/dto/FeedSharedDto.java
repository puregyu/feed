package com.fashion.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedSharedDto {
	private Long id;
	private LocalDateTime date;
	private Long userId;
	private int targetNo;
}
