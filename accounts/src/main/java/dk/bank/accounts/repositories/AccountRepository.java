package dk.bank.accounts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dk.bank.accounts.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByName(String name);
}