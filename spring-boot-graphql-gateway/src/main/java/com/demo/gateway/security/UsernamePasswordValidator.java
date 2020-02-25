package com.demo.gateway.security;

import com.demo.gateway.model.graph.user.Creds;
import com.demo.gateway.model.graph.user.UserInfo;
import org.springframework.stereotype.Component;

import javax.security.auth.message.AuthException;

import static com.demo.gateway.repository.TokenRepository.FAKE_USER;
import static com.demo.gateway.repository.TokenRepository.FAKE_VALID_ACCESS_TOKEN;

@Component
public class UsernamePasswordValidator {
    private static final String FAKE_USERNAME = "user";
    private static final String FAKE_PASSWORD = "pass";

    private static final UserInfo FAKE_USER_INFO = new UserInfo().setUser(FAKE_USER).setToken(FAKE_VALID_ACCESS_TOKEN);

    public UserInfo validate(Creds creds) throws AuthException {
        if (creds.getLogin().equals(FAKE_USERNAME) && creds.getPassword().equals(FAKE_PASSWORD)){
            return FAKE_USER_INFO;
        }
        throw new AuthException("Invalid username or password");
    }
}
