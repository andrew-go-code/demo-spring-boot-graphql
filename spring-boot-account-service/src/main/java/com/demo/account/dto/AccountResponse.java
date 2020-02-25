package com.demo.account.dto;

import com.demo.account.model.Account;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class AccountResponse {
    private List<Account> items;
    private AccountPageHeader header;
}
