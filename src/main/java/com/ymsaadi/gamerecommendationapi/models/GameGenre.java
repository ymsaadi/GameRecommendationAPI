package com.ymsaadi.gamerecommendationapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GameGenre(Integer id, String name, String slug) {
}
