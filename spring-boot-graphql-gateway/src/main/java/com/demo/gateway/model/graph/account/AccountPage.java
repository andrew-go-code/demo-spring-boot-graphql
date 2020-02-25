package com.demo.gateway.model.graph.account;

import com.demo.gateway.model.graph.PageHeader;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class AccountPage {
    private PageHeader header;
    private List<AccountItem> list;
}
