package com.demo.gateway.repository;

import com.demo.gateway.model.graph.user.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class TokenRepository {
    public static final String FAKE_VALID_ACCESS_TOKEN = "qwe";
    public static final User FAKE_USER = new User().setRoles(List.of("USER")).setName("Kris").setLogin("user");

    public Optional<User> getUserInfoByAccessToken(String accessToken){
        if (FAKE_VALID_ACCESS_TOKEN.equals(accessToken)){
            return Optional.of(FAKE_USER);
        }

        return Optional.empty();
    }
}
