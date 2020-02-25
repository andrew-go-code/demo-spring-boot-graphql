package com.demo.gateway.model.graph.card;

import com.demo.gateway.model.graph.account.AccountItem;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CardPage {
    private List<CardItem> list;

    public CardPage(AccountItem accountItem) {
        this.accountItem = accountItem;
    }

    private AccountItem accountItem;
}
