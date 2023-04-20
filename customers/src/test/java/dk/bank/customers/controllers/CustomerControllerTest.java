package dk.bank.customers.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import dk.bank.customers.domain.Customer;
import dk.bank.customers.services.CustomerService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Test
    void testSaveCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setName("Mehdi Taarit");
        customer.setEmail("mehdi.taarit@email.com");
        customer.setAddress("Copenhagen, Denmark");

        when(customerService.addCustomer(any(Customer.class))).thenReturn(customer);

        ResponseEntity<Customer> response = customerController.saveCustomer(customer);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    public void testGetCustomerById() {
        Integer id = 1;
        Customer customer = new Customer(id, "Mehdi Taarit", "mehdi.taarit@email.com", "Copenhagen, Denmark");
        customer.setId(id);

        Mockito.when(customerService.findById(id)).thenReturn(Optional.of(customer));

        ResponseEntity<Customer> response = customerController.getCustomerById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    public void testGetCustomerById_whenCustomerDoesNotExist() {
        Integer id = 2;

        Mockito.when(customerService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Customer> response = customerController.getCustomerById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}