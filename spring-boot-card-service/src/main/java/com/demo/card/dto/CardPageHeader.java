package com.demo.card.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CardPageHeader {
    private Integer total;
    private Integer page;
    private Integer onPage;
}
