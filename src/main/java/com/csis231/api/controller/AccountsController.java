package com.csis231.api.controller;

import com.csis231.api.model.Accounts;
import com.csis231.api.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountsController {

    private final AccountsService accountsService;

    @Autowired
    public AccountsController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @GetMapping
    public List<Accounts> getAllAccounts() {
        return accountsService.getAllAccounts();
    }

    @PostMapping
    public ResponseEntity<Accounts> createAccount(@Valid @RequestBody Accounts account) {
        Accounts savedAccount = accountsService.createAccounts(account);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accounts> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountsService.getAccountsById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAccount(@PathVariable Long id, @Valid @RequestBody Accounts accountDetails, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid account data");
        }
        Accounts updatedAccount = accountsService.updateAccounts(id, accountDetails);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountsService.deleteAccounts(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Accounts> getAccountByUsername(@PathVariable String username) {
        Accounts account = accountsService.getAccountByUsername(username);
        return ResponseEntity.ok(account);
    }
}
