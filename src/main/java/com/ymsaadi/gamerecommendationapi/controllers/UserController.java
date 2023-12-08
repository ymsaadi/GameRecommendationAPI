package com.ymsaadi.gamerecommendationapi.controllers;

import com.ymsaadi.gamerecommendationapi.models.User;
import com.ymsaadi.gamerecommendationapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/friendRequest")
    public ResponseEntity<HttpStatus> sendFriendRequest(@RequestParam Integer id, @AuthenticationPrincipal User user) {
        return userService.sendFriendRequest(id, user);
    }
}
