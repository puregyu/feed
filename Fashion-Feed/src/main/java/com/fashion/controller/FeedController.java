package com.fashion.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fashion.dto.FeedCommentDto;
import com.fashion.dto.FeedDto;
import com.fashion.dto.FeedLikeDto;
import com.fashion.dto.FeedSharedDto;
import com.fashion.dto.FeedsDto;
import com.fashion.service.FeedService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FeedController {

	private final FeedService feedService;

	// == Feed ==
	@GetMapping("/feeds")
	public List<FeedsDto> FeedList(	@RequestParam(defaultValue = "0") int pageNo,
													@RequestParam(defaultValue = "10") int pageSize) {
		return feedService.findAllFeeds(pageNo, pageSize);
	}
	
	@GetMapping("/feed/{feedId}")
	public FeedDto Feed(@PathVariable Long feedId) {
		return feedService.findOneFeed(feedId); 
	}
	
	// == FeedLike ==
	@PutMapping("/feed/like/{feedId}")
	public FeedLikeDto savelike(	@PathVariable Long feedId,
												@RequestParam Long userId) {
		return feedService.saveLike(feedId, userId);
	}
	
	@DeleteMapping("/feed/like/{feedId}")
	public void deleteLike(	@PathVariable Long feedId,
										@RequestParam Long userId) {
		feedService.deleteLike(feedId, userId);
	}
	
	// == FeedComment ==
	@PostMapping("/feed/comment/{feedId}")
	public FeedCommentDto saveComment(	@PathVariable Long feedId,
																@RequestBody FeedCommentDto feedCommentDto) {
		
		return feedService.saveComment(feedId, feedCommentDto);
	}
	
	@GetMapping("/feed/comment/{commentId}")
	public FeedCommentDto findComment(@PathVariable Long commentId) {
		
		return feedService.findOneComment(commentId);
	}
	
	@PutMapping("/feed/comment/{commentId}")
	public FeedCommentDto updateComment(	@PathVariable Long commentId,
																	@RequestBody FeedCommentDto feedCommentDto) {
		
		return feedService.updateComment(commentId, feedCommentDto);
	}
	
	@DeleteMapping("/feed/comment/{commentId}")
	public void deleteComment(@PathVariable Long commentId,
											@RequestParam Long feedId) {
		feedService.deleteComment(commentId, feedId);
	}
	
	// == FeedShared ==
	@PutMapping("/feed/shared/{feedId}")
	public FeedSharedDto saveShared(@PathVariable Long feedId,
													@RequestBody FeedSharedDto feedSharedDto,
													HttpServletRequest request,
													HttpServletResponse response) throws IOException {
		
		return feedService.saveShared(feedId, feedSharedDto, request, response); 
	}
}
