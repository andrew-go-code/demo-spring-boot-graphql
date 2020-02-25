package com.demo.gateway.resolver.account;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.demo.gateway.dto.ItemResponse;
import com.demo.gateway.model.graph.PageHeader;
import com.demo.gateway.model.graph.Pagination;
import com.demo.gateway.model.graph.account.AccountItem;
import com.demo.gateway.model.graph.account.AccountPage;
import com.demo.gateway.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @see CreditAccountResolver - child
 * @see CurrentAccountResolver - child
 */

@Component
@SuppressWarnings("unused")
@RequiredArgsConstructor
public class AccountResolver implements GraphQLQueryResolver {
    private final AccountService accountService;

    public AccountPage accounts(Pagination pagination, Integer filter) {
        if (filter != null){
            AccountItem accountById = accountService.getAccountById(filter);
            return new AccountPage()
                    .setHeader(new PageHeader())
                    .setList(List.of(accountById));
        }
        ItemResponse<AccountItem> items = accountService.getAccounts(pagination);
        return new AccountPage()
                .setHeader(items.getHeader())
                .setList(items.getItems());
    }
}
