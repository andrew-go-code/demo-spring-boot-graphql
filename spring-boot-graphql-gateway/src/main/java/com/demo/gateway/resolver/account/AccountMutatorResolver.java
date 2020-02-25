package com.demo.gateway.resolver.account;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.demo.gateway.model.graph.account.AccountItem;
import com.demo.gateway.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class AccountMutatorResolver implements GraphQLMutationResolver {
    private final AccountService accountService;

    public AccountItem spendMoney(Integer accountId, Integer amount) {
        return accountService.spendMoney(accountId, amount);
    }
}
