package com.demo.gateway.resolver.account;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.demo.gateway.model.graph.account.CurrentAccount;
import com.demo.gateway.model.graph.card.CardPage;
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
public class CurrentAccountResolver implements GraphQLResolver<CurrentAccount> {
    public CardPage cards(CurrentAccount currentAccount){
        return new CardPage(currentAccount);
    }
}
