package com.demo.gateway.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter implements Filter {
    private static final String TOKEN_HEADER = "X-Auth-Token";
    private final TokenAuthenticationProvider tokenAuthenticationProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Optional<String> accessToken = Optional.ofNullable(((HttpServletRequest) request).getHeader(TOKEN_HEADER));

        TokenAuthentication tokenAuthentication = new TokenAuthentication(accessToken.orElse(null));
        tokenAuthenticationProvider.authenticate(tokenAuthentication);

        SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
        chain.doFilter(request, response);
    }
}
