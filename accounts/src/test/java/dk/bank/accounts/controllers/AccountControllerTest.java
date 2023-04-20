package dk.bank.accounts.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dk.bank.accounts.domain.Account;
import dk.bank.accounts.services.AccountService;
import jakarta.persistence.EntityNotFoundException;

class AccountControllerTest {

    @Mock
    AccountService accountService;

    @InjectMocks
    AccountController accountController;

    private Account testAccount;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testAccount = new Account();
        testAccount.setId(1);
        testAccount.setNumber("123456");
        testAccount.setBalance(new BigDecimal(1000));
        testAccount.setCustomer(1);
    }

    @Test
    @DisplayName("Create account should return saved account with 201 CREATED status")
    void createAccount() {
        when(accountService.createAccount(testAccount)).thenReturn(testAccount);

        ResponseEntity<Account> response = accountController.createAccount(testAccount);

        assertThat(response).isNotNull();
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testAccount, response.getBody());
    }

    @Test
    @DisplayName("Get all accounts should return a list of accounts")
    void getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(testAccount);

        when(accountService.getAllAccounts()).thenReturn(accounts);

        ResponseEntity<List<Account>> response = accountController.getAllAccounts();

        assertThat(response).isNotNull();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accounts, response.getBody());
    }

    @Test
    @DisplayName("Get account by id should return account if it exists")
    void getAccountById() throws EntityNotFoundException {
        when(accountService.getAccountById(1)).thenReturn(Optional.of(testAccount));

        ResponseEntity<Account> response = accountController.getAccountById(1);

        assertThat(response).isNotNull();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testAccount, response.getBody());
    }

    @Test
    @DisplayName("Get account by id should return 404 NOT FOUND if account does not exist")
    void getAccountById_notFound() throws EntityNotFoundException {
        when(accountService.getAccountById(1)).thenReturn(Optional.empty());

        ResponseEntity<Account> response = accountController.getAccountById(1);

        assertThat(response).isNotNull();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Update account should return updated account if it exists")
    void updateAccount() throws EntityNotFoundException {
        Account updatedAccount = new Account();
        updatedAccount.setId(1);
        updatedAccount.setNumber("123456");
        updatedAccount.setBalance(new BigDecimal(2000));
        updatedAccount.setCustomer(1);

        when(accountService.updateAccount(1, updatedAccount)).thenReturn(updatedAccount);

        ResponseEntity<Account> response = accountController.updateAccount(1, updatedAccount);

        assertThat(response).isNotNull();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedAccount, response.getBody());
    }
}
