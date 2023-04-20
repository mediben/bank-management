package dk.bank.transactions.clients;

import org.springframework.stereotype.Component;

import dk.bank.transactions.domain.Account;

@Component
public interface AccountServiceClient {
    public Account getAccountById(Integer accountId);

    public Account update(Account account);
}
