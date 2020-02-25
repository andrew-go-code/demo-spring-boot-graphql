package com.demo.card.controller;

import com.demo.card.dto.CardResponse;
import com.demo.card.model.Card;
import com.demo.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping(params = {"accountIdIn", "page", "onPage"})
    public CardResponse getCardsByAccountIdIn(@RequestParam List<Integer> accountIdIn, @RequestParam Integer page, @RequestParam Integer onPage){
        return cardService.getCardsByAccountIdIn(accountIdIn, page, onPage);
    }

    @GetMapping(params = "accountIdIn")
    public List<Card> getCardsByAccountIdIn(@RequestParam List<Integer> accountIdIn){
        return cardService.getCardsByAccountIdIn(accountIdIn);
    }

    @GetMapping
    public List<Card> getCardsByAccountId(@RequestParam Integer accountId){
        return cardService.getAllCardsByAccountId(accountId);
    }

    @PostMapping
    public Card createCard(@RequestBody Integer accountId){
        return cardService.createCard(accountId);
    }
}

