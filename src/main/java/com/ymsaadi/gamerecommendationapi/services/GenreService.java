package com.ymsaadi.gamerecommendationapi.services;

import com.ymsaadi.gamerecommendationapi.models.GameGenre;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final IGDBService igdbService;
    public ResponseEntity<List<GameGenre>> getGenres(String name) {
        String stringBody = "fields id,name,slug; where name ~ *\"" + name + "\"*; limit 500;";
        return igdbService.getListData("https://api.igdb.com/v4/genres", stringBody, new ParameterizedTypeReference<>() {
        });
    }
}
