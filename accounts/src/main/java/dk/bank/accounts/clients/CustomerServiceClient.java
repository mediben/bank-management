package dk.bank.accounts.clients;

import org.springframework.stereotype.Component;

import dk.bank.accounts.domain.Customer;

@Component
public interface CustomerServiceClient {
    public Customer getCustomer(Integer customerId);

}
