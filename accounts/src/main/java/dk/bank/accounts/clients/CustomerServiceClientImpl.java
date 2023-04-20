package dk.bank.accounts.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import dk.bank.accounts.domain.Customer;

@Service
public class CustomerServiceClientImpl implements CustomerServiceClient {

    @Value("${CUSTOMER_SERVICE_URL}")
    private String customerServiceUrl;

    private RestTemplate restTemplate;

    public CustomerServiceClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Customer getCustomer(Integer customerId) {
        try {
            ResponseEntity<Customer> responseEntity = restTemplate.exchange(
                    customerServiceUrl + "/api/customers/" + customerId,
                    HttpMethod.GET, null, Customer.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody();
            } else {
                return null;
            }
        } catch (RestClientException e) {
            return null;
        }
    }

}
