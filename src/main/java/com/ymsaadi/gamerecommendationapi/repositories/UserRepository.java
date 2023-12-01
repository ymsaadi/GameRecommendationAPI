package com.ymsaadi.gamerecommendationapi.repositories;

import com.ymsaadi.gamerecommendationapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
