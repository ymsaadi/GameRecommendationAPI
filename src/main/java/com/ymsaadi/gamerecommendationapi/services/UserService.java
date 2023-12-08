package com.ymsaadi.gamerecommendationapi.services;

import com.ymsaadi.gamerecommendationapi.models.User;
import com.ymsaadi.gamerecommendationapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final EmailService emailService;
    private final UserRepository userRepository;

    public ResponseEntity<HttpStatus> sendFriendRequest(Integer destinationUserId, User sender) {
        try {
            User destinationUser = userRepository.findById(destinationUserId).orElseThrow();
            emailService.sendEmail(destinationUser.getEmail(),
                    "New Friend Request!!", sender.getUsername() +
                            " Wants to be your friend, make sure to accept his friend request.");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
