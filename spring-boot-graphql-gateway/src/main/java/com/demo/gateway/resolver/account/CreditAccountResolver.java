package com.demo.gateway.resolver.account;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.demo.gateway.model.graph.card.CardPage;
import com.demo.gateway.model.graph.account.CreditAccount;
import com.demo.gateway.resolver.card.CardPageResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @see AccountResolver - parent
 * @see CardPageResolver - child
 */

@Component
@SuppressWarnings("unused")
@RequiredArgsConstructor
public class CreditAccountResolver implements GraphQLResolver<CreditAccount> {
    public CardPage cards(CreditAccount accountItem){
        return new CardPage(accountItem);
    }
}
