package com.fashion.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Max;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@Validated
public class FeedController {

	private final FeedService feedService;

	// == Feed ==
	@GetMapping("/feeds")
	public ResponseEntity<List<FeedsDto>> FeedList(
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") @Max(value = 100) int pageSize) {
		
		return new ResponseEntity<List<FeedsDto>>(feedService.findAllFeeds(pageNo, pageSize), HttpStatus.OK); 
	}
	
	@GetMapping("/feed/{feedId}")
	public ResponseEntity<FeedDto> Feed(@PathVariable Long feedId) {
		
		return new ResponseEntity<FeedDto>(feedService.findOneFeed(feedId), HttpStatus.OK); 
	}
	
	// == FeedLike ==
	@PutMapping("/feed/like/{feedId}")
	public ResponseEntity<FeedLikeDto> savelike(@PathVariable Long feedId, @RequestParam Long userId) {
		
		return new ResponseEntity<FeedLikeDto>(feedService.saveLike(feedId, userId), HttpStatus.OK);
	}
	
	@DeleteMapping("/feed/like/{feedId}")
	public ResponseEntity<Object> deleteLike(@PathVariable Long feedId, @RequestParam Long userId) {
		
		feedService.deleteLike(feedId, userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// == FeedComment ==
	@PostMapping("/feed/comment/{feedId}")
	public ResponseEntity<FeedCommentDto> saveComment(@PathVariable Long feedId, @RequestBody FeedCommentDto feedCommentDto) {
		
		return new ResponseEntity<FeedCommentDto>(feedService.saveComment(feedId, feedCommentDto), HttpStatus.OK);
	}
	
	@GetMapping("/feed/comment/{commentId}")
	public ResponseEntity<FeedCommentDto> findComment(@PathVariable Long commentId) {
		
		return new ResponseEntity<FeedCommentDto>(feedService.findOneComment(commentId), HttpStatus.OK);
	}
	
	@PutMapping("/feed/comment/{commentId}")
	public ResponseEntity<FeedCommentDto> updateComment(@PathVariable Long commentId, @RequestBody FeedCommentDto feedCommentDto) {
		
		return new ResponseEntity<FeedCommentDto>(feedService.updateComment(commentId, feedCommentDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/feed/comment/{commentId}")
	public ResponseEntity<Object> deleteComment(@PathVariable Long commentId,
											@RequestParam Long feedId) {
		
		feedService.deleteComment(commentId, feedId);
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	// == FeedShared ==
	@PutMapping("/feed/shared/{feedId}")
	public ResponseEntity<FeedSharedDto> saveShared(
			@PathVariable Long feedId,
			@RequestBody FeedSharedDto feedSharedDto,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		return new ResponseEntity<FeedSharedDto>(feedService.saveShared(feedId, feedSharedDto, request, response), HttpStatus.OK);
	}
}
