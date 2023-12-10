package com.ymsaadi.gamerecommendationapi.services;

import com.ymsaadi.gamerecommendationapi.mappers.UserReviewMapper;
import com.ymsaadi.gamerecommendationapi.models.Game;
import com.ymsaadi.gamerecommendationapi.models.GetReviewsRequest;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserReviewServiceTests {
    @Mock
    private UserReviewRepository userReviewRepository;
    @Mock
    private GameService gameService;
    @Mock
    private UserReviewMapper userReviewMapper;
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

    @Test
    public void UserReviewService_GetAllReviews_ReturnsResponseEntityPage() {
        Page<UserReview> userReviews = Mockito.mock(Page.class);

        GetReviewsRequest getReviewsRequest = GetReviewsRequest.builder().userId(1).gameId(1).build();

        when(userReviewRepository.findByUserIdAndGameId(Mockito.any(Integer.class), Mockito.any(Integer.class), Mockito.any(PageRequest.class))).thenReturn(userReviews);
//        when(userReviewRepository.findByUserId(Mockito.any(Integer.class), Mockito.any(PageRequest.class))).thenReturn(userReviews);
//        when(userReviewRepository.findByGameId(Mockito.any(Integer.class), Mockito.any(PageRequest.class))).thenReturn(userReviews);
//        when(userReviewRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(userReviews);

        ResponseEntity<Page<UserReview>> responseUserReviews = userReviewService.getAllReviews(getReviewsRequest);

        Assertions.assertNotNull(responseUserReviews);
    }

    @Test
    public void UserReviewService_UpdateReview_ReturnsUpdatedUserReview() {
        User user = User
                .builder()
                .id(1)
                .email("ymsaadi@gmail.com")
                .username("ymsaadi")
                .firstname("Youssef")
                .lastname("Msaadi")
                .password("123456")
                .build();
        UserReview userReview = UserReview.builder().reviewScore(4).gameId(1).user(user).build();
        UserReview userReviewDto = UserReview.builder().reviewScore(4).gameId(1).user(user).build();

        when(userReviewRepository.findByIdAndUserId(Mockito.any(Integer.class), Mockito.any(Integer.class))).thenReturn(userReview);
        when(userReviewRepository.save(Mockito.any(UserReview.class))).thenReturn(userReviewDto);

        ResponseEntity<UserReview> savedUserReview = userReviewService.updateReview(1, userReviewDto, user);

        Assertions.assertNotNull(savedUserReview);
    }

    @Test
    public void UserReviewService_DeleteReview_DeletesUserReview() {
        User user = User
                .builder()
                .id(1)
                .email("ymsaadi@gmail.com")
                .username("ymsaadi")
                .firstname("Youssef")
                .lastname("Msaadi")
                .password("123456")
                .build();
        UserReview userReview = UserReview.builder().reviewScore(4).id(1).gameId(1).user(user).build();
        when(userReviewRepository.findByIdAndUserId(Mockito.any(Integer.class), Mockito.any(Integer.class))).thenReturn(userReview);

        ResponseEntity<HttpStatus> deleteResponse = userReviewService.deleteReview(userReview.getId(), user);

        Assertions.assertNotNull(deleteResponse);
    }
}
