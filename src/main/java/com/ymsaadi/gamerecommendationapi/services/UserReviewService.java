package com.ymsaadi.gamerecommendationapi.services;

import com.ymsaadi.gamerecommendationapi.mappers.UserReviewMapper;
import com.ymsaadi.gamerecommendationapi.models.GetReviewsRequest;
import com.ymsaadi.gamerecommendationapi.models.User;
import com.ymsaadi.gamerecommendationapi.models.UserReview;
import com.ymsaadi.gamerecommendationapi.repositories.UserReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserReviewService {
    @Autowired
    UserReviewRepository userReviewRepository;

    @Autowired
    UserReviewMapper userReviewMapper;

    public ResponseEntity<Page<UserReview>> getAllReviews(GetReviewsRequest getReviewsRequest) {
        if (getReviewsRequest.getUserId() != null && getReviewsRequest.getGameId() != null) {
            return new ResponseEntity<>(userReviewRepository.findByUserIdAndGameId(getReviewsRequest.getUserId(), getReviewsRequest.getGameId(), PageRequest.of(0, 5)), HttpStatus.OK);
        }
        if (getReviewsRequest.getUserId() != null) {
            return new ResponseEntity<>(userReviewRepository.findByUserId(getReviewsRequest.getUserId(), PageRequest.of(0, 5)), HttpStatus.OK);
        }
        if (getReviewsRequest.getGameId() != null) {
            return new ResponseEntity<>(userReviewRepository.findByGameId(getReviewsRequest.getGameId(), PageRequest.of(0, 5)), HttpStatus.OK);
        }
        return new ResponseEntity<>(userReviewRepository.findAll(PageRequest.of(0, 5)), HttpStatus.OK);
    }

    public ResponseEntity<UserReview> createReview(UserReview userReview, User user) {
        UserReview existingReview = userReviewRepository.findByUserIdAndGameId(user.getId(), userReview.getGameId());
        if (existingReview != null) {
            return updateReview(existingReview.getId(), userReview, user); // If the user already reviewed that game, update the review instead.
        }
        userReview.setUser(user);
        UserReview _userReview = userReviewRepository.save(userReview);
        return new ResponseEntity<>(_userReview, HttpStatus.CREATED);
    }

    public ResponseEntity<UserReview> updateReview(Integer id, UserReview userReview, User user) {
        UserReview _userReview = userReviewRepository.findByIdAndUserId(id, user.getId());
        if (_userReview == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        userReviewMapper.updateUserReviewFromDto(userReview, _userReview);
        userReviewRepository.save(_userReview);
        return new ResponseEntity<>(_userReview, HttpStatus.OK);
    }
}
