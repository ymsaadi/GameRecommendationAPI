package com.ymsaadi.gamerecommendationapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Game(
        Integer id,
        String name,
        String slug,
        @JsonProperty("first_release_date")
        Instant firstReleaseDate,
        String summary,
        String url,
        GameCover cover,
        GameGenre[] genres
) {
}
