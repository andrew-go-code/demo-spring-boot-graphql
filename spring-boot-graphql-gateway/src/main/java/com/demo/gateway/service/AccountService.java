package com.demo.gateway.service;

import com.demo.gateway.dto.ItemResponse;
import com.demo.gateway.feign.AccountClient;
import com.demo.gateway.model.graph.Pagination;
import com.demo.gateway.model.graph.account.AccountItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountClient accountClient;

    public ItemResponse<AccountItem> getAccounts(Pagination pagination){
        pagination = pagination == null ? new Pagination() : pagination;
        return accountClient.getAccounts(pagination.getPage(), pagination.getOnPage());
    }

    public AccountItem getAccountById(Integer accountId){
        Optional<AccountItem> account = accountClient.getAccount(accountId);
        if (account.isEmpty()){
            throw new RuntimeException("Not found account with id " + accountId);
        }
        return account.get();
    }

    public AccountItem spendMoney(Integer accountId, Integer amount){
        return accountClient.spendMoney(accountId, amount);
    }
}
