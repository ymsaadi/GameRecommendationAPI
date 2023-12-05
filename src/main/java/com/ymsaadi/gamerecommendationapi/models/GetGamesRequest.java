package com.ymsaadi.gamerecommendationapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetGamesRequest {
    private Integer pageNumber = 1;
    private Integer perPage = 20;
    private String sortBy;
    private String sortDir;
    private String name;
    private LocalDate gteFirstReleaseDate;
    private LocalDate lteFirstReleaseDate;
    private String genre;
}
