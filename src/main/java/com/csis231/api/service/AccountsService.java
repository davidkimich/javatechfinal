package com.csis231.api.service;

import com.csis231.api.exception.ResourceNotFoundException;
import com.csis231.api.model.Accounts;
import com.csis231.api.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountsService {

    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountsService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    public List<Accounts> getAllAccounts() {
        return accountsRepository.findAll();
    }

    public Accounts getAccountsById(Long id) {
        return accountsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Accounts not found"));
    }

    public Accounts createAccounts(Accounts accounts) {
        return accountsRepository.save(accounts);
    }

    public Accounts updateAccounts(Long id, Accounts accountsDetails) {
        Accounts accounts = accountsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accounts does not exist with id : " + id));

        // Update accounts fields with details
        // Example:
        // accounts.setUsername(accountsDetails.getUsername());
        // accounts.setPassword(accountsDetails.getPassword());
        // accounts.setFirstName(accountsDetails.getFirstName());
        // accounts.setLastName(accountsDetails.getLastName());

        return accountsRepository.save(accounts);
    }

    public Accounts getAccountByUsername(String username) {
        Optional<Accounts> account = accountsRepository.findByUsername(username);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new ResourceNotFoundException("Account not found with username: " + username);
        }
    }

    public Map<String, Boolean> deleteAccounts(Long id) {
        Accounts accounts = accountsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accounts not found."));

        accountsRepository.delete(accounts);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
