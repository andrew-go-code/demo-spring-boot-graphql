package com.demo.gateway.service;

import com.demo.gateway.dto.ItemResponse;
import com.demo.gateway.feign.CardClient;
import com.demo.gateway.model.graph.Pagination;
import com.demo.gateway.model.graph.card.CardItem;
import com.demo.gateway.model.graph.card.Status;
import com.demo.gateway.publisher.NotificationPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class CardService {
    private final CardClient cardClient;

    public ItemResponse<CardItem> getCards(Pagination pagination, List<Integer> accountIdIn){
        pagination = pagination == null ? new Pagination() : pagination;
        return cardClient.getCardsByAccountIdIn(accountIdIn, pagination.getPage(), pagination.getOnPage());
    }

    public List<CardItem> getCards(List<Integer> accountIdIn){
        return cardClient.getCardsByAccountIdIn(accountIdIn);
    }

    public List<CardItem> getCards(Integer accountId){
        return cardClient.getCardsByAccountId(accountId);
    }

    public CardItem createCard(Integer accountId){
        CardItem newCard = cardClient.createCard(accountId);
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            NotificationPublisher.NOTE_QUEUE.add(newCard.setStatus(Status.CREATED));
        }).start();

        return newCard.setStatus(Status.ORDERED);
    }
}
