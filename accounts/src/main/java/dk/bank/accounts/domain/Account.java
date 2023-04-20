package dk.bank.accounts.domain;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts", schema = "accounts_db")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private String number;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "type")
    private String type;

    @Column(name = "customer")
    private Integer customer;

    public Account(String name, String number, BigDecimal balance, String type, Integer customer) {
        this.name = name;
        this.balance = balance;
        this.number = number;
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        this.type = type;
        this.customer = customer;

    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

}
