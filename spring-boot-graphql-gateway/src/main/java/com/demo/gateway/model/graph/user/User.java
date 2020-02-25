package com.demo.gateway.model.graph.user;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class User {
    private String login;
    private String name;
    private List<String> roles;
}
