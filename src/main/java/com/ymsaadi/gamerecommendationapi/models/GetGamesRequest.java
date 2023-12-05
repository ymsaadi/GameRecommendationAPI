package com.ymsaadi.gamerecommendationapi.models;

public record GetGamesRequest(Integer pageNumber, Integer perPage, String sortBy, String sortDir) {
}
