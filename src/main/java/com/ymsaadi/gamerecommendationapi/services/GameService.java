package com.ymsaadi.gamerecommendationapi.services;

import com.ymsaadi.gamerecommendationapi.models.Game;
import com.ymsaadi.gamerecommendationapi.models.GetGamesRequest;
import com.ymsaadi.gamerecommendationapi.models.PaginationDetail;
import com.ymsaadi.gamerecommendationapi.models.PaginationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    private final IGDBService igdbService;

    public PaginationResponse<Game> getGames(GetGamesRequest getGamesRequest) {
        Integer offset = getGamesRequest.getPerPage() * getGamesRequest.getPageNumber() - getGamesRequest.getPerPage();
        String stringBody = String
                .format("fields id, name, cover.*, first_release_date, genres.*, slug, summary, url; where category = 0 & first_release_date != null %s %s %s %s; limit %d; offset %d; %s",
                        getGamesRequest.getName() == null ? "" : "& name ~ *\"" + getGamesRequest.getName() + "\"*",
                        getGamesRequest.getGenre() == null ? "" : "& genres.slug = \"" + getGamesRequest.getGenre() + "\"*",
                        getGamesRequest.getGteFirstReleaseDate() == null ? "" : "& first_release_date > " + getGamesRequest.getGteFirstReleaseDate().toEpochSecond(LocalTime.parse("00:00:01"), ZoneOffset.of("Z")),
                        getGamesRequest.getLteFirstReleaseDate() == null ? "" : "& first_release_date < " + getGamesRequest.getLteFirstReleaseDate().toEpochSecond(LocalTime.parse("00:00:01"), ZoneOffset.of("Z")),
                        getGamesRequest.getPerPage(),
                        offset,
                        getGamesRequest.getSortBy().isEmpty() ? "" : "sort " + getGamesRequest.getSortBy() + " " + getGamesRequest.getSortDir() + ";");
        PaginationDetail paginationDetail = getGamesCount(stringBody).getBody();
        paginationDetail.setPageNumber(getGamesRequest.getPageNumber());
        paginationDetail.setPerPage(getGamesRequest.getPerPage());
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
        return igdbService.getListData("https://api.igdb.com/v4/games", stringBody, new ParameterizedTypeReference<>() {
        });
    }

    public ResponseEntity<PaginationDetail> getGamesCount(String stringBody) {
        return igdbService.getData("https://api.igdb.com/v4/games/count", stringBody, new ParameterizedTypeReference<>() {
        });
    }
}
