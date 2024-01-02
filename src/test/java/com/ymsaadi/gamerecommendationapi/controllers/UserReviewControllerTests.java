package com.ymsaadi.gamerecommendationapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ymsaadi.gamerecommendationapi.models.User;
import com.ymsaadi.gamerecommendationapi.models.UserReview;
import com.ymsaadi.gamerecommendationapi.services.UserReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = UserReviewController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserReviewControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserReviewService userReviewService;

    @Autowired
    private ObjectMapper objectMapper;
    private User user;
    private UserReview userReview;

    @BeforeEach
    public void init() {
        user = User
                .builder()
                .id(1)
                .email("ymsaadi@gmail.com")
                .username("ymsaadi")
                .firstname("Youssef")
                .lastname("Msaadi")
                .password("123456")
                .build();
        userReview = UserReview.builder().reviewScore(4).gameId(1).user(user).build();
    }

    @Test
    public void UserReviewController_CreateUserReview_ReturnCreated() throws Exception {
        given(userReviewService.createReview(ArgumentMatchers.any(), ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/api/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userReview)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
