package com.demo.gateway.security;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class SecurityGraphQLAspect {

    @Value("${spring.profiles.active:Unknown}")
    private String activeProfile;

    @Profile("!test")
    @Before("isGraphqlResolvers() && !isUnsecured()")
    public void doSecurityCheck() {
        if (!activeProfile.equals("test")) {
            if (
                    SecurityContextHolder.getContext() == null ||
                            SecurityContextHolder.getContext().getAuthentication() == null ||
                            !SecurityContextHolder.getContext().getAuthentication().isAuthenticated() ||
                            AnonymousAuthenticationToken.class.isAssignableFrom(SecurityContextHolder.getContext().getAuthentication().getClass())) {
                throw new AccessDeniedException("User not authenticated");
            }
        }
    }

    @Pointcut("target(com.coxautodev.graphql.tools.GraphQLResolver)")
    private void isGraphqlResolvers() {
    }

    @Pointcut("@annotation(com.demo.gateway.security.PermitAll)")
    private void isUnsecured() {
    }
}
