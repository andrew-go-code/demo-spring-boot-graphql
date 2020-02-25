package com.demo.gateway.resolver.loader;

import com.demo.gateway.feign.CardClient;
import com.demo.gateway.model.graph.card.CardItem;
import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.servlet.context.DefaultGraphQLServletContext;
import graphql.servlet.context.DefaultGraphQLWebSocketContext;
import graphql.servlet.context.GraphQLServletContextBuilder;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GraphQLContextBuilder implements GraphQLServletContextBuilder {
    public static final String DL_NAME = "cardDataLoader";

    private final CardClient cardClient;


    @Override
    public GraphQLContext build(HttpServletRequest req, HttpServletResponse response) {
        return DefaultGraphQLServletContext.createServletContext(buildDataLoaderRegistry(), null).with(req).with(response)
                .build();
    }

    @Override
    public GraphQLContext build() {
        return new DefaultGraphQLContext(buildDataLoaderRegistry(), null);
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest request) {
        return DefaultGraphQLWebSocketContext.createWebSocketContext(buildDataLoaderRegistry(), null).with(session)
                .with(request).build();
    }

    private DataLoaderRegistry buildDataLoaderRegistry() {
        DataLoaderRegistry dataLoaderRegistry = new DataLoaderRegistry();
        dataLoaderRegistry.register(DL_NAME,
                new DataLoader<Integer, List<CardItem>>(accountIdIn ->
                        CompletableFuture.supplyAsync(() -> {
                                    List<CardItem> cardItems = cardClient.getCardsByAccountIdIn(accountIdIn);
                                    return accountIdIn.stream().map(k-> getCardItems(k, cardItems)).collect(Collectors.toList());
                                }
                        )));

        return dataLoaderRegistry;
    }

    private List<CardItem> getCardItems(int accountId, List<CardItem> detailItems) {
        return detailItems.stream()
                .filter(d -> d.getAccountId().equals(accountId))
                .collect(Collectors.toList());
    }
}
