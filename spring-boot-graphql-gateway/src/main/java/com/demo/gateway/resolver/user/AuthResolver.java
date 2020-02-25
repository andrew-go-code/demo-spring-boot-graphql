package com.demo.gateway.resolver.user;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.demo.gateway.model.graph.user.Creds;
import com.demo.gateway.model.graph.user.UserInfo;
import com.demo.gateway.security.PermitAll;
import com.demo.gateway.security.UsernamePasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.security.auth.message.AuthException;

@Component
@SuppressWarnings("unused")
@RequiredArgsConstructor
public class AuthResolver implements GraphQLMutationResolver {
    private final UsernamePasswordValidator usernamePasswordValidator;

    @PermitAll
    public UserInfo auth(Creds creds) throws AuthException {
        return usernamePasswordValidator.validate(creds);
    }
}
