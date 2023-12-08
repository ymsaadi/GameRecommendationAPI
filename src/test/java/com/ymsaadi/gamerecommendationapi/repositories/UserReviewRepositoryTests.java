package com.ymsaadi.gamerecommendationapi.repositories;

import com.ymsaadi.gamerecommendationapi.models.User;
import com.ymsaadi.gamerecommendationapi.models.UserReview;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserReviewRepositoryTests {
    @Autowired
    private UserReviewRepository userReviewRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void userReviewRepository_Save_ReturnSavedUserReview() {
        User user = User
                .builder()
                .email("ymsaadi@gmail.com")
                .username("ymsaadi")
                .firstname("Youssef")
                .lastname("Msaadi")
                .password("123456")
                .build();
        userRepository.save(user);

        UserReview userReview = UserReview.builder().reviewScore(4).gameId(1).user(user).build();

        UserReview savedUserReview = userReviewRepository.save(userReview);

        Assertions.assertNotNull(savedUserReview);
        Assertions.assertTrue(savedUserReview.getId() > 0);
    }

    @Test
    public void userReviewRepository_FindAll_ReturnMoreThanOneUserReview() {
        User user = User
                .builder()
                .email("ymsaadi@gmail.com")
                .username("ymsaadi")
                .firstname("Youssef")
                .lastname("Msaadi")
                .password("123456")
                .build();
        userRepository.save(user);

        UserReview userReview1 = UserReview.builder().reviewScore(4).gameId(1).user(user).build();
        UserReview userReview2 = UserReview.builder().reviewScore(5).gameId(2).user(user).build();

        userReviewRepository.save(userReview1);
        userReviewRepository.save(userReview2);

        List<UserReview> userReviews = userReviewRepository.findAll();

        Assertions.assertNotNull(userReviews);
        Assertions.assertEquals(2, userReviews.size());
    }

    @Test
    public void userReviewRepository_FindById_ReturnUserReview() {
        User user = User
                .builder()
                .email("ymsaadi@gmail.com")
                .username("ymsaadi")
                .firstname("Youssef")
                .lastname("Msaadi")
                .password("123456")
                .build();
        userRepository.save(user);

        UserReview userReview = UserReview.builder().reviewScore(4).gameId(1).user(user).build();
        userReviewRepository.save(userReview);

        UserReview userReviewFound = userReviewRepository.findById(userReview.getId()).get();

        Assertions.assertNotNull(userReviewFound);
    }

    @Test
    public void userReviewRepository_FindByGameId_ReturnUserReviews() {
        User user = User
                .builder()
                .email("ymsaadi@gmail.com")
                .username("ymsaadi")
                .firstname("Youssef")
                .lastname("Msaadi")
                .password("123456")
                .build();
        userRepository.save(user);

        UserReview userReview1 = UserReview.builder().reviewScore(4).gameId(1).user(user).build();
        UserReview userReview2 = UserReview.builder().reviewScore(5).gameId(1).user(user).build();

        userReviewRepository.save(userReview1);
        userReviewRepository.save(userReview2);

        Page<UserReview> userReviews = userReviewRepository.findByGameId(1, PageRequest.of(0, 5));
        Assertions.assertNotNull(userReviews);
        Assertions.assertEquals(userReviews.getTotalElements(), 2);
    }

    @Test
    public void userReviewRepository_FindByUserId_ReturnUserReviews() {
        User user = User
                .builder()
                .email("ymsaadi@gmail.com")
                .username("ymsaadi")
                .firstname("Youssef")
                .lastname("Msaadi")
                .password("123456")
                .build();
        userRepository.save(user);

        UserReview userReview1 = UserReview.builder().reviewScore(4).gameId(1).user(user).build();
        UserReview userReview2 = UserReview.builder().reviewScore(5).gameId(2).user(user).build();

        userReviewRepository.save(userReview1);
        userReviewRepository.save(userReview2);

        Page<UserReview> userReviews = userReviewRepository.findByUserId(user.getId(), PageRequest.of(0, 5));
        Assertions.assertNotNull(userReviews);
        Assertions.assertEquals(userReviews.getTotalElements(), 2);
    }

    @Test
    public void userReviewRepository_FindByUserIdAndGameId_ReturnUserReviews() {
        User user = User
                .builder()
                .email("ymsaadi@gmail.com")
                .username("ymsaadi")
                .firstname("Youssef")
                .lastname("Msaadi")
                .password("123456")
                .build();
        userRepository.save(user);

        UserReview userReview1 = UserReview.builder().reviewScore(4).gameId(1).user(user).build();
        UserReview userReview2 = UserReview.builder().reviewScore(5).gameId(2).user(user).build();

        userReviewRepository.save(userReview1);
        userReviewRepository.save(userReview2);

        Page<UserReview> userReviews = userReviewRepository.findByUserIdAndGameId(user.getId(), 1, PageRequest.of(0, 5));
        Assertions.assertNotNull(userReviews);
        Assertions.assertEquals(userReviews.getTotalElements(), 1);
    }

    @Test
    public void userReviewRepository_UpdateUserReview_ReturnUpdatedUserReview() {
        User user = User
                .builder()
                .email("ymsaadi@gmail.com")
                .username("ymsaadi")
                .firstname("Youssef")
                .lastname("Msaadi")
                .password("123456")
                .build();
        userRepository.save(user);

        UserReview userReview = UserReview.builder().reviewScore(4).gameId(1).user(user).build();
        userReviewRepository.save(userReview);

        UserReview userReviewUpdate = userReviewRepository.findById(userReview.getId()).get();
        userReviewUpdate.setReviewScore(3);
        userReviewRepository.save(userReviewUpdate);

        Assertions.assertNotNull(userReviewUpdate);
        Assertions.assertEquals(userReviewUpdate.getReviewScore(), 3);
    }

    @Test
    public void userReviewRepository_DeleteUserReview_ReturnUserReviewIsEmpty() {
        User user = User
                .builder()
                .email("ymsaadi@gmail.com")
                .username("ymsaadi")
                .firstname("Youssef")
                .lastname("Msaadi")
                .password("123456")
                .build();
        userRepository.save(user);

        UserReview userReview = UserReview.builder().reviewScore(4).gameId(1).user(user).build();
        userReviewRepository.save(userReview);
        userReviewRepository.deleteById(userReview.getId());

        Optional<UserReview> emptyUserReview = userReviewRepository.findById(userReview.getId());

        Assertions.assertTrue(emptyUserReview.isEmpty());
    }
}
