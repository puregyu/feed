package com.fashion.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeedSharedDto {

	private Long id;
	private LocalDateTime date;
	private Long userid;
	private Long feedId;
	private int targetNo;
}
