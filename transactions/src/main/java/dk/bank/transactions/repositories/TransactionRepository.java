package dk.bank.transactions.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dk.bank.transactions.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByAccountOrderByIdDesc(Integer accountId);

}