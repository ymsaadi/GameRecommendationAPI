package com.ymsaadi.gamerecommendationapi.controllers;

import com.ymsaadi.gamerecommendationapi.models.GetReviewsRequest;
import com.ymsaadi.gamerecommendationapi.models.User;
import com.ymsaadi.gamerecommendationapi.models.UserReview;
import com.ymsaadi.gamerecommendationapi.services.UserReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class UserReviewController {
    private final UserReviewService userReviewService;

    @GetMapping
    public ResponseEntity<Page<UserReview>> getAllReviews(@Valid GetReviewsRequest getReviewsRequest) {
        return userReviewService.getAllReviews(getReviewsRequest);
    }

    @PostMapping
    public ResponseEntity<UserReview> createReview(@RequestBody UserReview userReview, @AuthenticationPrincipal User user) {
        return userReviewService.createReview(userReview, user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserReview> updateReview(@PathVariable Integer id, @RequestBody UserReview userReview, @AuthenticationPrincipal User user) {
        return userReviewService.updateReview(id, userReview, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteReview(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        return userReviewService.deleteReview(id, user);
    }
}
