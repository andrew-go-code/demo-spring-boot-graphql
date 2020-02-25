package com.demo.gateway.model.graph.account;

import com.demo.gateway.model.graph.card.CardPage;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CurrentAccount implements AccountItem {
    private Integer id;
    private Currency currency;
    private CardPage cards;
    private Integer amount;
    private Integer overdraft;
}