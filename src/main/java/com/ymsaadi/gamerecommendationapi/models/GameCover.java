package com.ymsaadi.gamerecommendationapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GameCover(Integer height, Integer width, String url) {
}
