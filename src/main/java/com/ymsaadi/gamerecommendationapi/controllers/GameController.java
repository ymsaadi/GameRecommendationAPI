package com.ymsaadi.gamerecommendationapi.controllers;

import com.ymsaadi.gamerecommendationapi.models.Game;
import com.ymsaadi.gamerecommendationapi.services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/games")
public class GameController {
    private final GameService gameService;

    @GetMapping("")
    public ResponseEntity<List<Game>> getAllGames() {
        return gameService.getGames();
    }
}
