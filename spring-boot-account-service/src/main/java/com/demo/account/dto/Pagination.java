package com.demo.account.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Pagination {
    private Integer page;
    private Integer onPage;
}
