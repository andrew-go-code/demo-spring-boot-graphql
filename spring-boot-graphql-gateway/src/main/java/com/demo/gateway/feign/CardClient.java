package com.demo.gateway.feign;

import com.demo.gateway.dto.ItemResponse;
import com.demo.gateway.model.graph.card.CardItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "card-api",
        url = "http://127.0.0.1:7771"
)
@RequestMapping("/cards")
public interface CardClient {
    @GetMapping(params = {"accountIdIn", "page", "onPage"})
    ItemResponse<CardItem> getCardsByAccountIdIn(@RequestParam List<Integer> accountIdIn, @RequestParam int page, @RequestParam int onPage );

    @GetMapping(params = "accountIdIn")
    List<CardItem> getCardsByAccountIdIn(@RequestParam List<Integer> accountIdIn );

    @GetMapping
    List<CardItem> getCardsByAccountId(@RequestParam Integer accountId);

    @PostMapping
    CardItem createCard(@RequestBody Integer accountId);
}
