package dk.bank.transactions.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import dk.bank.transactions.domain.Transaction;
import dk.bank.transactions.services.TransactionService;

@RestController
@RequestMapping("api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/latest/{accountId}")
    public ResponseEntity<List<Transaction>> getLatestTransactionsByAccount(@PathVariable Integer accountId) {
        List<Transaction> transaction = transactionService.getLatestTransactionsByAccount(accountId, 10);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("/deposit")
    public ResponseEntity<Transaction> createDepositTransaction(@RequestBody Transaction transaction) {
        Transaction transactionSaved = transactionService.createDepositTransaction(transaction);
        return new ResponseEntity<Transaction>(transactionSaved, HttpStatus.CREATED);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> createWithdrawTransaction(@RequestBody Transaction transaction) {
        Transaction transactionSaved = transactionService.createWithdrawTransaction(transaction);
        return new ResponseEntity<Transaction>(transactionSaved, HttpStatus.CREATED);
    }

    @GetMapping("/health")
    @ResponseBody
    public String get() {
        return "Transactions service is up and running !";
    }

}
