package com.ymsaadi.gamerecommendationapi.models;

import java.util.List;

public record PaginationResponse<T>(PaginationDetail paginationDetail, List<T> items) {
}
