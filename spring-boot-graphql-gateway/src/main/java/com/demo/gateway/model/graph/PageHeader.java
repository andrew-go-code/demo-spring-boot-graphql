package com.demo.gateway.model.graph;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PageHeader {
    private Integer total;
    private Integer page;
    private Integer onPage;
}
