package com.ymsaadi.gamerecommendationapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Game Recommendation API", version = "1.0"))
public class GameRecommendationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameRecommendationApiApplication.class, args);
	}

}
