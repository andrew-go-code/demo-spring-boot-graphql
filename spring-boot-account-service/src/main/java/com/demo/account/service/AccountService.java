package com.demo.account.service;

import com.demo.account.dto.AccountPageHeader;
import com.demo.account.dto.AccountResponse;
import com.demo.account.model.Account;
import com.demo.account.model.CreditAccount;
import com.demo.account.model.CurrentAccount;
import com.demo.account.repository.CreditAccountRepository;
import com.demo.account.repository.CurrentAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final CreditAccountRepository creditAccountRepository;
    private final CurrentAccountRepository currentAccountRepository;

    public AccountResponse getAllAccounts(Integer page, Integer onPage) {
        List<CreditAccount> creditAccounts = creditAccountRepository.findAll();
        List<CurrentAccount> currentAccounts = currentAccountRepository.findAll();
        List<Account> accounts = new ArrayList<>();
        accounts.addAll(creditAccounts);
        accounts.addAll(currentAccounts);

        return new AccountResponse()
                .setItems(accounts.subList(page *  onPage,onPage))
                .setHeader(new AccountPageHeader().setOnPage(onPage).setPage(page).setTotal(accounts.size()));
    }

    public Account getAccountById(Integer id){
        Optional<CreditAccount> creditAccount = creditAccountRepository.findById(id);
        if (creditAccount.isPresent()) return creditAccount.get();
        return currentAccountRepository.findById(id).orElse(null);
    }

    public Account spendMoney(Integer id, Integer amount) {
        Optional<CreditAccount> creditAccountBox = creditAccountRepository.findById(id);
        if (creditAccountBox.isPresent()){
            CreditAccount creditAccount = creditAccountBox.get();
            checkAmount(creditAccount, amount);
            creditAccount.setAmount(creditAccount.getAmount() - amount);
            return creditAccountRepository.save(creditAccount);
        }

        Optional<CurrentAccount> currentAccountBox = currentAccountRepository.findById(id);
        if (currentAccountBox.isPresent()){
            CurrentAccount currentAccount = currentAccountBox.get();
            checkAmount(currentAccount, amount);
            currentAccount.setAmount(currentAccount.getAmount() - amount);
            return currentAccountRepository.save(currentAccount);
        }
        throw new RuntimeException(String.format("Account id %s not found.", id));
    }

    private void checkAmount(Account account, Integer amount){
        if (account.getAmount() < amount){
            throw new RuntimeException("Not enough money.");
        }
    }
}
