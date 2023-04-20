package dk.bank.customers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dk.bank.customers.domain.Customer;
import dk.bank.customers.repositories.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer addCustomer(Customer customer) throws Exception {
        Customer customerSavedToDB = this.customerRepository.save(customer);

        return customerSavedToDB;
    }

    @Transactional
    public Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id);
    }

    @Transactional
    public boolean customerExists(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.isPresent();
    }

    @Transactional
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

}
