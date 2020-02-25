package com.demo.gateway.model.graph.account;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, property = "className")
public interface AccountItem {
    Integer getId();
}
