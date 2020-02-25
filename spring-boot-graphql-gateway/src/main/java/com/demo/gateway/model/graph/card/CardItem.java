package com.demo.gateway.model.graph.card;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CardItem {
    private Integer id;
    private String cardHolder;
    private String cardNumber;
    private Integer accountId;
    private Status status;
}
