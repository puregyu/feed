package com.fashion.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.fashion.domain.Feed;
import com.fashion.domain.FeedComment;
import com.fashion.domain.FeedLike;
import com.fashion.domain.FeedShared;

@Repository
public class FeedRepository {

	@PersistenceContext
	private EntityManager em;
	
	// == Feed ==
	public List<Feed> findAllFeeds(int startIndex, int pageSize) {
		return em.createQuery("select f from Feed f order by f.date desc", Feed.class)
				.setFirstResult(startIndex)
				.setMaxResults(pageSize)
				.getResultList();
	}

	public Feed findOneFeed(Long feedId) {
		return em.find(Feed.class, feedId);
	}

	// == FeedLike ==
	public void saveLike(FeedLike feedLike) {
		em.persist(feedLike);
	}

	public FeedLike findOneLike(Long feedId, Long userId) {
		return (FeedLike) em.createQuery("select fl from FeedLike fl where fl.feed.id=:feedId and fl.userId =:userId")
				.setParameter("feedId", feedId)
				.setParameter("userId", userId)
				.getSingleResult();
	}
	
	public void deleteLike(FeedLike feedLike) {
		em.remove(feedLike);
	}

	// == FeedComment ==
	public void saveComment(FeedComment feedComment) {
		em.persist(feedComment);
	}

	public FeedComment findComment(Long commentId) {
		return em.find(FeedComment.class, commentId);
	}

	public void deleteComment(FeedComment feedComment) {
		em.remove(feedComment);
	}

	// == FeedShared ==
	public void saveShared(FeedShared feedShared) {
		em.persist(feedShared);
	}
}
