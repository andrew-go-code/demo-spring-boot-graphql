package com.demo.card.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cards")
@Accessors(chain = true)
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "card_holder")
    private String cardHolder;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "account_id")
    private Integer accountId;
}
