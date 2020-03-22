package com.fashion.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeedsDto {
	private Long id;
	private LocalDateTime date;
	private String mdPhoto;
	private String	 mdName;
	private String content;
	private int countOfComment;
	private int countOfLike;
	private int countOfShared;
}
