package com.demo.gateway.dto;

import com.demo.gateway.model.graph.PageHeader;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ItemResponse<T> {
    private List<T> items;
    private PageHeader header;

}
