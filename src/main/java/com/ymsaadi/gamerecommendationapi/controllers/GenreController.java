package com.ymsaadi.gamerecommendationapi.controllers;

import com.ymsaadi.gamerecommendationapi.models.GameGenre;
import com.ymsaadi.gamerecommendationapi.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {
    private final GenreService genreService;

    @GetMapping
    public List<GameGenre> getGenres(@RequestParam String name) {
        return genreService.getGenres(name).getBody();
    }
}
