package com.ymsaadi.gamerecommendationapi.repositories;

import com.ymsaadi.gamerecommendationapi.models.UserReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReviewRepository extends JpaRepository<UserReview, Integer> {
    Page<UserReview> findByGameId(Integer gameId, PageRequest of);

    Page<UserReview> findByUserId(Integer userId, PageRequest of);

    Page<UserReview> findByUserIdAndGameId(Integer userId, Integer gameId, PageRequest of);
    UserReview findByUserIdAndGameId(Integer userId, Integer gameId);
}
