package dk.bank.customers.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import jakarta.persistence.EntityManager;

public class CustomerTest {

    @Mock
    private EntityManager entityManager;

    @Test
    public void testConstructor() {
        Customer customer = new Customer(1, "John Doe", "john.doe@gmail.com", "123 Main St");
        Assertions.assertEquals(1, customer.getId());
        Assertions.assertEquals("John Doe", customer.getName());
        Assertions.assertEquals("john.doe@gmail.com", customer.getEmail());
        Assertions.assertEquals("123 Main St", customer.getAddress());
    }

    @Test
    public void testSetters() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("John Doe");
        customer.setEmail("john.doe@gmail.com");
        customer.setAddress("123 Main St");
        Assertions.assertEquals(1, customer.getId());
        Assertions.assertEquals("John Doe", customer.getName());
        Assertions.assertEquals("john.doe@gmail.com", customer.getEmail());
        Assertions.assertEquals("123 Main St", customer.getAddress());
    }

    @Test
    public void testToString() {
        Customer customer = new Customer(1, "John Doe", "john.doe@gmail.com", "123 Main St");
        Assertions.assertEquals("Customer(id=1, name=John Doe, email=john.doe@gmail.com, address=123 Main St)",
                customer.toString());
    }

}
