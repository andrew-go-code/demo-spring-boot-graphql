package com.demo.account.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "current_accounts")
@Data
public class CurrentAccount extends Account {
    private Integer overdraft;
}
