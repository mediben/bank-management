package dk.bank.accounts.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import dk.bank.accounts.domain.Account;

@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindByName() {
        // create some accounts
        Account account1 = new Account("checking account", null, new BigDecimal("1000"), "checking", 1);
        Account account2 = new Account("saving account", null, new BigDecimal("2000"), "savings", 1);
        entityManager.persist(account1);
        entityManager.persist(account2);
        entityManager.flush();

        // search for accounts by name
        List<Account> accounts = accountRepository.findByName("account");

        // check results
        assertNotNull(accounts);
        assertEquals(2, accounts.size());
        assertEquals("checking account", accounts.get(0).getName());
        assertEquals("saving account", accounts.get(1).getName());
    }
}
