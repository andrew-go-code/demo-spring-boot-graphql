package com.demo.gateway.model.graph;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Pagination {
    private Integer page = 0;
    private Integer onPage = 3;
}
