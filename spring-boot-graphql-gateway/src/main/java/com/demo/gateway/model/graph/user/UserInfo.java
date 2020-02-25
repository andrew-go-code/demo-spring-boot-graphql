package com.demo.gateway.model.graph.user;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserInfo {
    private User user;
    private String token;
}
