package com.ymsaadi.gamerecommendationapi.controllers;

import com.ymsaadi.gamerecommendationapi.models.Game;
import com.ymsaadi.gamerecommendationapi.models.PaginationResponse;
import com.ymsaadi.gamerecommendationapi.services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/games")
public class GameController {
    private final GameService gameService;

    @GetMapping("")
    public ResponseEntity<PaginationResponse<Game>> getGames(@RequestParam(defaultValue = "1") Integer pageNumber,
                                                             @RequestParam(defaultValue = "20") Integer perPage,
                                                             @RequestParam(defaultValue = "") String sortBy,
                                                             @RequestParam(defaultValue = "asc") String sortDir) {
        return ResponseEntity.ok(gameService.getGames(pageNumber, perPage, sortBy, sortDir));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable String id) {
        return gameService.getGameById(id);
    }

    @GetMapping("/raw")
    public ResponseEntity<List<Game>> getGamesByString(@RequestParam String s) {
        ResponseEntity<List<Game>> g = gameService.getGamesByString(s);
        System.out.println("controller");
        System.out.println(g);
        return g;
    }
}
