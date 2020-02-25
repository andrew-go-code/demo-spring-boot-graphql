package com.demo.gateway.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.RequiredArgsConstructor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class GraphQLErrorAdaptor implements GraphQLError {
    private final GraphQLError graphQLError;
    private final String message;

    @Override
    public List<SourceLocation> getLocations() {
        return graphQLError.getLocations();
    }

    @Override
    public ErrorClassification getErrorType() {
        return graphQLError.getErrorType();
    }

    @Override
    public String getMessage() {
        return graphQLError.getMessage();
    }

    @Override
    public Map<String, Object> getExtensions() {
        Map<String, Object> extensions = new LinkedHashMap<>();
        extensions.put("code", "000");
        extensions.put("message", message);
        return extensions;
    }
}
