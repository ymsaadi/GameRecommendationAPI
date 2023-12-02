package com.ymsaadi.gamerecommendationapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Game(
        Integer id,
        String name,
        String slug,
        Date first_release_date,
        String summary,
        String url,
        GameCover cover
) {
}
