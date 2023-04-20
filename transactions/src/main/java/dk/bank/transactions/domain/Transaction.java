package dk.bank.transactions.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions", schema = "transactions_db")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number; // should be renamed to transactionNumber and auto generated
    private Integer account; // should be renamed to accountIdReference
    private Integer customer; // should be renamed to customerIdReference
    private String type; // should be renamed to transactionType, enum and mapped to a table
    private BigDecimal amount;
    private String status; // should be enum and mapped to a table
    private Boolean archived;
}
