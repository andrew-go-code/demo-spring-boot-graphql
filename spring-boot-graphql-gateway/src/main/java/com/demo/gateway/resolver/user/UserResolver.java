package com.demo.gateway.resolver.user;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.demo.gateway.model.graph.user.User;
import com.demo.gateway.security.UsernamePasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.demo.gateway.repository.TokenRepository.FAKE_USER;

@Component
@SuppressWarnings("unused")
@RequiredArgsConstructor
public class UserResolver implements GraphQLQueryResolver {
    private final UsernamePasswordValidator usernamePasswordValidator;

    public User user() {
        return FAKE_USER;
    }
}
