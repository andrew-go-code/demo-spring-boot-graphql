package com.demo.gateway.model.graph.user;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Creds {
    private String login;
    private String password;
}
