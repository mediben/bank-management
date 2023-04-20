package dk.bank.accounts.services;

import dk.bank.accounts.clients.CustomerServiceClient;
import dk.bank.accounts.domain.Account;
import dk.bank.accounts.domain.Customer;
import dk.bank.accounts.repositories.AccountRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerServiceClient customerServiceClient;

    /**
     * Account Service constructor
     * 
     * @param accountRepository
     * @param customerServiceClient
     */
    public AccountService(AccountRepository accountRepository, CustomerServiceClient customerServiceClient) {
        this.accountRepository = accountRepository;
        this.customerServiceClient = customerServiceClient;
    }

    /**
     * Create Account
     * 
     * @param Account account
     * @return Account
     */
    public Account createAccount(Account account) {
        Customer customerFetched = customerServiceClient.getCustomer(account.getCustomer());
        if (customerFetched == null) {
            throw new EntityNotFoundException("Customer not found");
        }
        // Generate a UUID for account number
        account.setNumber(UUID.randomUUID().toString().replace("-", "").substring(0, 12));

        Account accountSavedToDB = accountRepository.save(account);
        return accountSavedToDB;
    }

    /**
     * List all account found
     * 
     * @return [Account] list of all accounts
     */
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    /**
     * Fetch account by id
     * 
     * @param id
     * @return Account
     */
    public Optional<Account> getAccountById(Integer id) {
        return accountRepository.findById(id);
    }

    /**
     * Update account values
     * 
     * @param id      account id to search with
     * @param Account accountToUpdate value with
     * @return
     */
    public Account updateAccount(Integer id, Account accountToUpdate) {
        Optional<Account> existingAccount = getAccountById(id);
        if (existingAccount.isPresent()) {
            Account account = existingAccount.get();
            account.setBalance(accountToUpdate.getBalance());

            account = accountRepository.save(account);
            return account;
        } else {
            throw new EntityNotFoundException("Account not found");
        }
    }

}
