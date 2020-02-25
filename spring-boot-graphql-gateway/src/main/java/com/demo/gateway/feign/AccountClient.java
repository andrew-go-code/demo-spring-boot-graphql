package com.demo.gateway.feign;

import com.demo.gateway.dto.ItemResponse;
import com.demo.gateway.model.graph.account.AccountItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(
        name = "account-api",
        url = "http://127.0.0.1:7770"
)
@RequestMapping("/accounts")
public interface AccountClient {
    @GetMapping
    ItemResponse<AccountItem> getAccounts(@RequestParam("page") Integer page,
                                          @RequestParam("onPage") Integer perPage );

    @GetMapping("/{accountId}")
    Optional<AccountItem> getAccount(@PathVariable("accountId") Integer id);

    @PostMapping("/spend/{accountId}/{amount})")
    AccountItem spendMoney(@PathVariable("accountId") Integer accountId, @PathVariable("amount") Integer amount);
}
