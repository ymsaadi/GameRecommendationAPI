package com.ymsaadi.gamerecommendationapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetReviewsRequest {
    private Integer gameId;
    private Integer userId;
}
