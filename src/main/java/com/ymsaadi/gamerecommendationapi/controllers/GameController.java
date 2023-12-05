package com.ymsaadi.gamerecommendationapi.controllers;

import com.ymsaadi.gamerecommendationapi.models.Game;
import com.ymsaadi.gamerecommendationapi.models.GetGamesRequest;
import com.ymsaadi.gamerecommendationapi.models.PaginationResponse;
import com.ymsaadi.gamerecommendationapi.services.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/games")
public class GameController {
    private final GameService gameService;

    @GetMapping
    public ResponseEntity<PaginationResponse<Game>> getGames(@Valid GetGamesRequest getGamesRequest) {
        return ResponseEntity.ok(gameService.getGames(getGamesRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable String id) {
        return gameService.getGameById(id);
    }

    @GetMapping("/raw")
    public ResponseEntity<List<Game>> getGamesByString(@RequestParam String s) {
        return gameService.getGamesByString(s);
    }
}
