package com.ymsaadi.gamerecommendationapi.services;

import com.ymsaadi.gamerecommendationapi.models.Game;
import com.ymsaadi.gamerecommendationapi.models.PaginationDetail;
import com.ymsaadi.gamerecommendationapi.models.PaginationResponse;
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

    public PaginationResponse<Game> getGames(Integer pageNumber, Integer perPage, String sortBy, String sortDir) {
        Integer offset = perPage * pageNumber - perPage;
        String stringBody = String
                .format("fields id, cover.*, first_release_date, genres.*, name, slug, summary, url; where category=0 & first_release_date != null; limit %d; offset %d; %s",
                        perPage,
                        offset,
                        sortBy.isEmpty() ? "" : "sort " + sortBy + " " + sortDir + ";");
        PaginationDetail paginationDetail = getGamesCount(stringBody).getBody();
        paginationDetail.setPageNumber(pageNumber);
        paginationDetail.setPerPage(perPage);
        return new PaginationResponse<>(paginationDetail, getGamesByString(stringBody).getBody());
    }

    public ResponseEntity<Game> getGameById(String id) {
        String bodyString = "fields id, cover.*, first_release_date, genres.*, name, slug, summary, url; where category=0 & id=" + id + ";";
        ResponseEntity<List<Game>> game = getGamesByString(bodyString);
        if (game.getBody() == null) {
            return ResponseEntity.internalServerError().build();
        }
        if (game.getBody().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game.getBody().get(0));
    }

    public ResponseEntity<List<Game>> getGamesByString(String stringBody) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Client-ID", "eihuex4eqir18dy84r9b1axqrnwevm"); // todo hide client-id and token
        httpHeaders.add("Authorization", "Bearer fwesoqdsr4ey7zofcm8lwcsw4bvfsy");
        return restTemplate.exchange(
                "https://api.igdb.com/v4/games",
                HttpMethod.POST,
                new HttpEntity<>(stringBody, httpHeaders),
                new ParameterizedTypeReference<>() {
                });
    }

    public ResponseEntity<PaginationDetail> getGamesCount(String stringBody) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Client-ID", "eihuex4eqir18dy84r9b1axqrnwevm"); // todo hide client-id and token
        httpHeaders.add("Authorization", "Bearer fwesoqdsr4ey7zofcm8lwcsw4bvfsy");
        return restTemplate.exchange(
                "https://api.igdb.com/v4/games/count",
                HttpMethod.POST,
                new HttpEntity<>(stringBody, httpHeaders),
                new ParameterizedTypeReference<>() {
                });
    }
}
