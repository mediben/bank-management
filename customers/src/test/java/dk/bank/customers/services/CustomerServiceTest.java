package dk.bank.customers.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import dk.bank.customers.domain.Customer;
import dk.bank.customers.repositories.CustomerRepository;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setName("John");

        when(customerRepository.save(customer)).thenReturn(customer);

        Customer customerSavedToDB = customerService.addCustomer(customer);

        assertEquals(customer.getName(), customerSavedToDB.getName());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Customer customer = new Customer(id, "Mehdi Taarit", "mehdi.taarit@gmail.com", "Copenhagen, Denmark");
        Optional<Customer> expectedCustomer = Optional.of(customer);

        Mockito.when(customerRepository.findById(id)).thenReturn(expectedCustomer);

        Optional<Customer> actualCustomer = customerService.findById(id);

        Assertions.assertEquals(expectedCustomer, actualCustomer);
        Mockito.verify(customerRepository, Mockito.times(1)).findById(id);
    }
}