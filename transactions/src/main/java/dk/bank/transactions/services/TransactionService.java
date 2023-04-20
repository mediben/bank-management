package dk.bank.transactions.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.bank.transactions.clients.AccountServiceClient;
import dk.bank.transactions.domain.Account;
import dk.bank.transactions.domain.Transaction;
import dk.bank.transactions.repositories.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountServiceClient accountServiceClient;

    public TransactionService(TransactionRepository transactionRepository, AccountServiceClient accountServiceClient) {
        this.transactionRepository = transactionRepository;
        this.accountServiceClient = accountServiceClient;
    }

    public Transaction createDepositTransaction(Transaction transaction) {
        Account accountFetched = accountServiceClient.getAccountById(transaction.getAccount());
        if (accountFetched == null) {
            throw new EntityNotFoundException("Account not found");
        }

        if (transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        Transaction transactionSaved = transactionRepository.save(transaction);
        // update balance
        BigDecimal newBalance = accountFetched.getBalance().add(transaction.getAmount());
        accountFetched.setBalance(newBalance);
        accountServiceClient.update(accountFetched);
        transactionSaved.setStatus("done");
        transactionRepository.save(transactionSaved);
        return transactionSaved;
    }

    public Transaction createWithdrawTransaction(Transaction transaction) {
        Account accountFetched = accountServiceClient.getAccountById(transaction.getAccount());
        if (accountFetched == null) {
            throw new EntityNotFoundException("Account not found");
        }

        if (transaction.getAmount().compareTo(accountFetched.getBalance()) > 0) {
            throw new IllegalArgumentException("Low balance for this transaction");
        }

        Transaction transactionSaved = transactionRepository.save(transaction);
        // update balance
        BigDecimal newBalance = accountFetched.getBalance().subtract(transaction.getAmount());
        accountFetched.setBalance(newBalance);
        accountServiceClient.update(accountFetched);
        transactionSaved.setStatus("done");
        transactionRepository.save(transactionSaved);
        return transactionSaved;
    }

    public List<Transaction> getLatestTransactionsByAccount(Integer accountId, Integer numberOfTransactions) {
        System.out.println("fetching the latest transactions");
        Account accountFetched = accountServiceClient.getAccountById(accountId);
        if (accountFetched == null) {
            throw new EntityNotFoundException("Account not found");
        }

        List<Transaction> transactions = transactionRepository.findByAccountOrderByIdDesc(accountId);
        return transactions.stream().limit(numberOfTransactions).collect(Collectors.toList());
    }

}
