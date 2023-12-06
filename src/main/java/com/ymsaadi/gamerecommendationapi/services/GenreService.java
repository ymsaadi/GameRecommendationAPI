package com.ymsaadi.gamerecommendationapi.services;

import com.ymsaadi.gamerecommendationapi.models.GameGenre;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GenreService {
    public ResponseEntity<List<GameGenre>> getGenres(String name) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Client-ID", "eihuex4eqir18dy84r9b1axqrnwevm"); // todo hide client-id and token
        httpHeaders.add("Authorization", "Bearer fwesoqdsr4ey7zofcm8lwcsw4bvfsy");
        return restTemplate.exchange(
                "https://api.igdb.com/v4/genres",
                HttpMethod.POST,
                new HttpEntity<>("fields id,name,slug;" + "where name ~ *\"" + name + "\"*;" + " limit 500;", httpHeaders),
                new ParameterizedTypeReference<>() {
                });
    }
}
