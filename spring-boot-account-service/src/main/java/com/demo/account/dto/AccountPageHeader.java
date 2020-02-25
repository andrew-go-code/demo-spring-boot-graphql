package com.demo.account.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AccountPageHeader {
    private Integer total;
    private Integer page;
    private Integer onPage;
}
