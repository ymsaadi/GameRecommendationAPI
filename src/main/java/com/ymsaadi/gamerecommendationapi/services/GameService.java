package com.ymsaadi.gamerecommendationapi.services;

import com.ymsaadi.gamerecommendationapi.models.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    public ResponseEntity<List<Game>> getGames() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Client-ID", "eihuex4eqir18dy84r9b1axqrnwevm"); // todo hide client-id and token
        httpHeaders.add("Authorization", "Bearer fwesoqdsr4ey7zofcm8lwcsw4bvfsy");
        return restTemplate.exchange(
                "https://api.igdb.com/v4/games",
                HttpMethod.POST,
                new HttpEntity<>("fields id, cover.*, first_release_date, genres.*, name, slug, summary, url; where category=0;", httpHeaders),
                new ParameterizedTypeReference<>() {
                });
    }
}
