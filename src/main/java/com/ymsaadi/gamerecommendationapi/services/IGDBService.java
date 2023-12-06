package com.ymsaadi.gamerecommendationapi.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class IGDBService {
    public <T> ResponseEntity<List<T>> getListData(String url, String stringBody, ParameterizedTypeReference<List<T>> type) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = getHttpHeaders();
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(stringBody, httpHeaders),
                type);
    }

    public <T> ResponseEntity<T> getData(String url, String stringBody, ParameterizedTypeReference<T> type) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = getHttpHeaders();
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(stringBody, httpHeaders),
                new ParameterizedTypeReference<>() {
                });
    }

    public HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Client-ID", "eihuex4eqir18dy84r9b1axqrnwevm"); // todo hide client-id and token
        httpHeaders.add("Authorization", "Bearer fwesoqdsr4ey7zofcm8lwcsw4bvfsy");
        return httpHeaders;
    }
}
