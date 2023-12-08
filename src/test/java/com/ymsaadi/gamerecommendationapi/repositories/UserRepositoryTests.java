package com.ymsaadi.gamerecommendationapi.repositories;

import com.ymsaadi.gamerecommendationapi.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void userRepository_Save_ReturnSavedUser() {
        User user = User
                .builder()
                .email("ymsaadi@gmail.com")
                .username("ymsaadi")
                .firstname("Youssef")
                .lastname("Msaadi")
                .password("123456")
                .build();

        User savedUser = userRepository.save(user);

        Assertions.assertNotNull(savedUser);
        Assertions.assertTrue(savedUser.getId() > 0);
    }

    @Test
    public void userRepository_FindAll_ReturnMoreThanOneUser() {
        User user1 = User
                .builder()
                .email("ymsaadi1@gmail.com")
                .username("ymsaadi1")
                .firstname("Youssef")
                .lastname("Msaadi")
                .password("123456")
                .build();
        User user2 = User
                .builder()
                .email("ymsaadi2@gmail.com")
                .username("ymsaadi2")
                .firstname("Youssef")
                .lastname("Msaadi")
                .password("123456")
                .build();

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> userList = userRepository.findAll();

        Assertions.assertNotNull(userList);
        Assertions.assertTrue(userList.size() >= 2);
    }

    @Test
    public void userRepository_FindById_ReturnUser() {
        User user = User
                .builder()
                .email("ymsaadi@gmail.com")
                .username("ymsaadi")
                .firstname("Youssef")
                .lastname("Msaadi")
                .password("123456")
                .build();

        userRepository.save(user);

        User foundUser = userRepository.findById(user.getId()).get();

        Assertions.assertNotNull(foundUser);
    }

    @Test
    public void userRepository_FindByUsername_ReturnUser() {
        User user = User
                .builder()
                .email("ymsaadi@gmail.com")
                .username("ymsaadi")
                .firstname("Youssef")
                .lastname("Msaadi")
                .password("123456")
                .build();

        userRepository.save(user);

        User foundUser = userRepository.findByUsername(user.getUsername()).get();

        Assertions.assertNotNull(foundUser);
    }
}
