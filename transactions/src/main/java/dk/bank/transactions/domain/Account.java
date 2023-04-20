package dk.bank.transactions.domain;

import java.math.BigDecimal;

public class Account {

    private Integer id;
    private String name;
    private BigDecimal balance;
    private String type;
    private Integer customer;

    public Account() {
    }

    public Account(String name, BigDecimal balance, String type, Integer customer) {
        this.name = name;
        this.balance = balance;
        this.type = type;
        this.customer = customer;
    }

    // getters and setters

    public void setID(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

}
