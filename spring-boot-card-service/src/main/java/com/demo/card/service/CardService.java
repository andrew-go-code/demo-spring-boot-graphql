package com.demo.card.service;

import com.demo.card.dto.CardPageHeader;
import com.demo.card.dto.CardResponse;
import com.demo.card.model.Card;
import com.demo.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public CardResponse getCardsByAccountIdIn(List<Integer> accountIdIn, Integer page, Integer onPage){
        Page<Card> cardPage = cardRepository.findAllByAccountIdIn(accountIdIn, PageRequest.of(page, onPage));
        return new CardResponse()
                .setItems(cardPage.getContent())
                .setHeader(new CardPageHeader()
                        .setOnPage(onPage)
                        .setPage(page)
                        .setTotal((int)cardPage.getTotalElements()));
    }

    public List<Card> getCardsByAccountIdIn(List<Integer> accountIdIn){
        return cardRepository.findAllByAccountIdIn(accountIdIn);
    }

    public List<Card> getAllCardsByAccountId(Integer accountId){
        return cardRepository.findAllByAccountId(accountId);
    }

    public Card createCard(Integer accountId){
        Card newCard = new Card()
                .setCardHolder("Christoph Waltz")
                .setAccountId(accountId)
                .setCardNumber("999988887777" + ThreadLocalRandom.current().nextInt(1000, 9999));

        return cardRepository.save(newCard);
    }
}
