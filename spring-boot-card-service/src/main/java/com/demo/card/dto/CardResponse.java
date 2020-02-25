package com.demo.card.dto;

import com.demo.card.model.Card;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CardResponse {
    private List<Card> items;
    private CardPageHeader header;
}
