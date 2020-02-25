package com.demo.account.controller;

import com.demo.account.dto.AccountResponse;
import com.demo.account.model.Account;
import com.demo.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public AccountResponse getAccounts(
            @RequestParam("page") Integer page,
            @RequestParam("onPage") Integer onPage){
        return accountService.getAllAccounts(page, onPage);
    }

    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable("accountId") Integer id){
        return accountService.getAccountById(id);
    }

    @PostMapping("/spend/{accountId}/{amount})")
    public Account spendMoney(@PathVariable("accountId") Integer id, @PathVariable("amount") Integer amount) {
        return accountService.spendMoney(id, amount);
    }
}
