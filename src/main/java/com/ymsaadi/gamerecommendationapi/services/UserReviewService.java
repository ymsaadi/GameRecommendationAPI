package com.ymsaadi.gamerecommendationapi.services;

import com.ymsaadi.gamerecommendationapi.models.GetReviewsRequest;
import com.ymsaadi.gamerecommendationapi.models.User;
import com.ymsaadi.gamerecommendationapi.models.UserReview;
import com.ymsaadi.gamerecommendationapi.repositories.UserReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserReviewService {
    private final UserReviewRepository userReviewRepository;

    public Page<UserReview> getAllReviews(GetReviewsRequest getReviewsRequest) {
        if (getReviewsRequest.getUserId() != null && getReviewsRequest.getGameId() != null) {
            return userReviewRepository.findByUserIdAndGameId(getReviewsRequest.getUserId(), getReviewsRequest.getGameId(), PageRequest.of(0, 5));
        }
        if (getReviewsRequest.getUserId() != null) {
            return userReviewRepository.findByUserId(getReviewsRequest.getUserId(), PageRequest.of(0, 5));
        }
        if (getReviewsRequest.getGameId() != null) {
            return userReviewRepository.findByGameId(getReviewsRequest.getGameId(), PageRequest.of(0, 5));
        }
        return userReviewRepository.findAll(PageRequest.of(0, 5));
    }

    public ResponseEntity<UserReview> createReview(UserReview userReview, User user) {
        userReview.setUser(user);
        UserReview existingReview = userReviewRepository.findByUserIdAndGameId(user.getId(), userReview.getGameId());
//        System.out.println(existingReview);
        UserReview _userReview = userReviewRepository.save(userReview);
        return new ResponseEntity<>(_userReview, HttpStatus.CREATED);
    }
}
