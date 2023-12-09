package com.ymsaadi.gamerecommendationapi.services;

import com.ymsaadi.gamerecommendationapi.models.Game;
import com.ymsaadi.gamerecommendationapi.models.User;
import com.ymsaadi.gamerecommendationapi.models.UserReview;
import com.ymsaadi.gamerecommendationapi.repositories.UserReviewRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserReviewServiceTests {
    @Mock
    private UserReviewRepository userReviewRepository;
    @Mock
    private GameService gameService;
    @InjectMocks
    private UserReviewService userReviewService;

    @Test
    public void UserReviewService_CreateReview_ReturnsUserReview() {
        User user = User
                .builder()
                .id(1)
                .email("ymsaadi@gmail.com")
                .username("ymsaadi")
                .firstname("Youssef")
                .lastname("Msaadi")
                .password("123456")
                .build();
        Game game = Game.builder().name("The Witcher 3").slug("the-witcher-3").build();
        UserReview userReview = UserReview.builder().reviewScore(4).gameId(1).user(user).build();
        UserReview userReviewDto = UserReview.builder().reviewScore(4).gameId(1).user(user).build();

        when(userReviewRepository.save(Mockito.any(UserReview.class))).thenReturn(userReview);
        when(userReviewRepository.findByUserIdAndGameId(Mockito.any(Integer.class), Mockito.any(Integer.class))).thenReturn(null);
        when(gameService.getGameById(Mockito.any(Integer.class))).thenReturn(ResponseEntity.ok(game));

        ResponseEntity<UserReview> savedUserReview = userReviewService.createReview(userReviewDto, user);

        Assertions.assertNotNull(savedUserReview);
    }
}
