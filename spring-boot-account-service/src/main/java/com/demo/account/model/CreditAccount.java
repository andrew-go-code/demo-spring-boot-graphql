package com.demo.account.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "credit_accounts")
@Data
public class CreditAccount extends Account {
    @Column(name = "limit_amount")
    private Integer limitAmount;
}
