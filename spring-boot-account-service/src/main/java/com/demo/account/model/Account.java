package com.demo.account.model;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public class Account implements AccountItem {
    @Id
    @SequenceGenerator(name = "seq")
    @GeneratedValue(generator = "seq")
    protected Integer id;

    @Enumerated(EnumType.STRING)
    protected Currency currency;
    protected Integer amount;
}
