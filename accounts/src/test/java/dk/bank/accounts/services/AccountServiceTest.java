package dk.bank.accounts.services;

import dk.bank.accounts.domain.Account;
import dk.bank.accounts.repositories.AccountRepository;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    private AccountService accountService;

    @Test
    void testGetAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("account 1", null, new BigDecimal("100"), "checking", 1));
        accounts.add(new Account("account 2", null, new BigDecimal("200"), "savings", 1));

        when(accountRepository.findAll()).thenReturn(accounts);

        List<Account> retrievedAccounts = accountService.getAllAccounts();
        assertEquals(accounts, retrievedAccounts);

        verify(accountRepository, times(1)).findAll();
    }

    @Test
    void testGetAccountById() {
        Account account = new Account("test account", null, new BigDecimal("1000"), "checking", 1);
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));

        Optional<Account> retrievedAccount = accountService.getAccountById(account.getId());
        assertTrue(retrievedAccount.isPresent());
        assertEquals(account, retrievedAccount.get());

        verify(accountRepository, times(1)).findById(account.getId());
    }
}
