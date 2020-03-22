package com.fashion.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fashion.domain.Feed;
import com.fashion.domain.FeedComment;
import com.fashion.domain.FeedLike;
import com.fashion.domain.FeedShared;
import com.fashion.domain.SharedTarget;
import com.fashion.dto.FeedCommentDto;
import com.fashion.dto.FeedDto;
import com.fashion.dto.FeedLikeDto;
import com.fashion.dto.FeedSharedDto;
import com.fashion.dto.FeedsDto;
import com.fashion.repository.FeedRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FeedService {
	
	private final FeedRepository feedRepository;
	
	// == Feed ==
	public List<FeedsDto> findAllFeeds(int pageNo, int pageSize) {
		
		int startIndex = pageNo * 10;
		List<Feed> feeds = feedRepository.findAllFeeds(startIndex, pageSize);
		
		List<FeedsDto> feedsDto = new ArrayList<FeedsDto>();
		for(int i=0; i<feeds.size(); i++) {
			FeedsDto feed = new FeedsDto(
					feeds.get(i).getId(),
					feeds.get(i).getDate(),
					feeds.get(i).getMdPhoto(),
					feeds.get(i).getMdName(),
					feeds.get(i).getContent(),
					feeds.get(i).getCountOfComment(),
					feeds.get(i).getCountOfLike(),
					feeds.get(i).getCountOfShared());
			
			feedsDto.add(feed);
		}
		return feedsDto;
	}

	public FeedDto findOneFeed(Long feedId) {
		
		Feed feed = feedRepository.findOneFeed(feedId);
		FeedDto feedDto = new FeedDto(
				feed.getId(),
				feed.getDate(), 
				feed.getMdPhoto(), 
				feed.getMdName(), 
				feed.getContent(), 
				feed.getCountOfComment(), 
				feed.getCountOfLike(), 
				feed.getCountOfShared(), 
				feed.getComments(), 
				feed.getLikes(), 
				feed.getShares());
		
		return feedDto;
	}

	// == FeedLike ==
	@Transactional
	public FeedLikeDto saveLike(Long feedId, Long userId) {
		
		Feed feed = feedRepository.findOneFeed(feedId);
		feed.addCountOfLike();
		
		FeedLike feedLike = FeedLike.createFeedLike(feed, userId);
		feedRepository.saveLike(feedLike);
		
		FeedLikeDto feedLikeDto = new FeedLikeDto(
				feedLike.getId(),
				feedLike.getUserId(), 
				feedLike.getDate(), 
				feedLike.getFeed());
		
		return feedLikeDto;
	}

	@Transactional
	public void deleteLike(Long feedId, Long userId) {
		
		Feed feed = feedRepository.findOneFeed(feedId);
		feed.removeCountOfLike();
		
		FeedLike feedLike = feedRepository.findOneLike(feedId, userId);
		feedRepository.deleteLike(feedLike);
	}

	// == FeedComment ==
	@Transactional
	public FeedCommentDto saveComment(Long feedId, FeedCommentDto feedCommentDto) {
		
		Feed feed = feedRepository.findOneFeed(feedId);
		feed.addCountOfCommnet();
		
		FeedComment feedComment = FeedComment.createFeedComment(feed, feedCommentDto);
		feedRepository.saveComment(feedComment);
		
		return feedCommentDto;
	}

	@Transactional
	public FeedCommentDto updateComment(Long commentId, FeedCommentDto feedCommentDto) {
		
		FeedComment feedComment = feedRepository.findComment(commentId);
		feedComment.updateComment(feedCommentDto);
		
		return feedCommentDto;
	}

	@Transactional
	public void deleteComment(Long commentId, Long feedId) {
		
		Feed feed = feedRepository.findOneFeed(feedId);
		feed.removeCountOfCommnet();
		
		FeedComment feedComment = feedRepository.findComment(commentId);
		feedRepository.deleteComment(feedComment);
	}

	public FeedCommentDto findOneComment(Long commentId) {
		
		FeedComment feedComment = feedRepository.findComment(commentId);
		FeedCommentDto feedCommentDto = new FeedCommentDto(
				feedComment.getId(),
				feedComment.getUserId(),
				feedComment.getContent());
		
		return feedCommentDto;
	}

	// == FeedShared ==
	@Transactional
	public FeedSharedDto saveShared(Long feedId, FeedSharedDto feedSharedDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String sharedUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/feed/"+feedId;
		SharedTarget targetSns=null;
		
		switch (feedSharedDto.getTargetNo()) {
		case 1:
			targetSns = SharedTarget.Facebook;
			response.sendRedirect("http://www.facebook.com/sharer/sharer.php?u=" + sharedUrl);
			break;

		case 2:
			targetSns = SharedTarget.Twitter;
			response.sendRedirect("https://twitter.com/intent/tweet?text=TEXT&url=" + sharedUrl);
			break;
			
		}
		
		Feed feed = feedRepository.findOneFeed(feedId);
		feed.addCountOfShared();
		
		FeedShared feedShared = FeedShared.createFeedShare(feed, targetSns, feedSharedDto);
		feedRepository.saveShared(feedShared);
		
		return feedSharedDto;
	}
}
