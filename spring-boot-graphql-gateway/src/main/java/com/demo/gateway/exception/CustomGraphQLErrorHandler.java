package com.demo.gateway.exception;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.DefaultGraphQLErrorHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomGraphQLErrorHandler extends DefaultGraphQLErrorHandler {
    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        List<GraphQLError> clientErrors = errors.stream()
                .filter(this::isClientError)
                .collect(Collectors.toList());

        List<GraphQLError> serverErrors = errors.stream()
                .filter(e -> !isClientError(e))
                .map(e -> {
                    if (e instanceof ExceptionWhileDataFetching){
                        ExceptionWhileDataFetching ex = (ExceptionWhileDataFetching) e;
                        return new GraphQLErrorAdaptor(e, ex.getException().getMessage());
                    }
                    return new GraphQLErrorAdaptor(e, e.getMessage());
                })
                .collect(Collectors.toList());

        List<GraphQLError> e = new ArrayList<>();
        e.addAll(clientErrors);
        e.addAll(serverErrors);
        return e;
    }
}


