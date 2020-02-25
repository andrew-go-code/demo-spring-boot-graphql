package com.demo.gateway.resolver.card;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.demo.gateway.model.graph.card.CardItem;
import com.demo.gateway.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class CardMutatorResolver implements GraphQLMutationResolver {
    private final CardService cardService;

    public CardItem newCard(Integer accountId) {
        return cardService.createCard(accountId);
    }
}
