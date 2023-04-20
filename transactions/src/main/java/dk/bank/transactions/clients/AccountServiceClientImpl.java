package dk.bank.transactions.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import dk.bank.transactions.domain.Account;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AccountServiceClientImpl implements AccountServiceClient {

    @Value("${ACCOUNT_SERVICE_URL}")
    private String accountServiceUrl;

    private RestTemplate restTemplate;

    public AccountServiceClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Account getAccountById(Integer accountId) {
        try {
            ResponseEntity<Account> responseEntity = restTemplate.exchange(
                    accountServiceUrl + "/api/accounts/" + accountId,
                    HttpMethod.GET, null, Account.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody();
            } else {
                return null;
            }
        } catch (RestClientException e) {
            return null;
        }
    }

    @Override
    public Account update(Account account) {
        String url = accountServiceUrl + "/api/accounts/" + account.getId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Account> requestEntity = new HttpEntity<>(account, headers);

        try {
            ResponseEntity<Account> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity,
                    Account.class); // , account.getId()

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody();
            } else {
                System.out.println("failed !!!");
                return null;
            }
        } catch (HttpClientErrorException.NotFound e) {
            throw new EntityNotFoundException("Account not found");
        }
    }

}
