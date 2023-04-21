# Bank-Management-System

### <em style="color:Green"> Architecture </em>

For better scalability and flexibility of the solution. We will opt for a microservice architecture and break away from the monolith system that is in place. Each microservice is responsible for a specific domain.
This will allows us independent deployment and testing of each microservice.

### <em style="color:Green"> Domain-Driven Design </em>

The microservices will follow Domain-Driven Design (DDD) principles, which means that each service will have its own bounded context, entities, and value objects.

### <em style="color:Green"> Communication between microservices </em>

To ensure communication we will use a RESTful API. Each microservice will expose its endpoints to other microservices and external systems.

### <em style="color:Green"> Database </em>

Each microservice will have its own database, which will ensure data isolation and scalability. However for this iteration we decided to go for the same database but lay them across different schemas.

### <em style="color:Green"> Onion architecture </em>

For a better visibility and maintainability, the Onion Architecture separates the application into distinct layers based on their responsibilities.

**_The Accounts microservice will communicate with the Customers microservice to check if a customer exists, and the Transactions microservice will communicate with the Accounts microservice to check if an account exists. This communication will be done via HTTP requests and responses._** <br><br>

> > > ## Accounts Microservice

This service will be responsible for creating, updating, and deleting accounts. When a new account is created, it will first check if the customer exists by making a request to the Customers microservice. If the customer exists, then the account will be created, and the account details will be stored in a database. The Accounts microservice will also handle the retrieval of account information, such as balance.<br>

> > > ## Customers Microservice

This service will be responsible for creating, updating, and deleting customer information. When a new customer is created, it will receive input from a REST API and store the customer details in a database. The Customers microservice will also handle the retrieval of customer information.<br>

> > > ## Transactions Microservice

This service will be responsible for handling deposit and withdrawal transactions. When a transaction is initiated, it will first check if the account exists by making a request to the Accounts microservice. If the account exists, then the transaction will proceed by checking the current balance and updating it accordingly. The Transactions microservice will also handle the listing of the last performed transactions.<br>

### _How to run 🎉_

1. Open project location and run `docker compose up`
2. Check all the services are up and running:
3. customers-service: `http://localhost:8081/health`
4. accounts-service: `http://localhost:8082/health`
5. transactions-service: `http://localhost:8083/health`
6. Use postman to preform the following:

Add a new customer:

- endpoint: http://localhost:8081/api/customers
- ```json
  {
    "name": "first last name",
    "email": "your@email.com",
    "address": "Country, street 1"
  }
  ```

Add a new account:

- endpoint: http://localhost:8082/api/accounts
- ```json
  {
    "name": "account name",
    "balance": 200.0,
    "type": "CHECKING",
    "customer": 1
  }
  ```

Add a deposit:

- endpoint: http://localhost:8083/api/transactions/deposit

- ```json
  {
    "account": 1,
    "customer": 1,
    "type": "deposit",
    "status": "pending",
    "archived": "false",
    "amount": 100
  }
  ```

Make a withdraw:

- endpoint http://localhost:8083/api/transactions/withdraw

- ```json
  {
    "account": 1,
    "customer": 1,
    "type": "withdraw",
    "status": "pending",
    "archived": "false",
    "amount": 100
  }
  ```

List last transactions:

```
- http://localhost:8083/api/transactions/latest/1
```

List balance:

```
- http://localhost:8082/api/accounts/1
```

---

## <em>Improvement</em>

<br>

#### **Security**

To ensure better security, we will have to implement industry-standard security measures such as SSL/TLS encryption, token-based authentication.xw<br>

#### **Queue-based communication**

Instead of direct HTTP requests between microservices, which can be slow. We can use a message broker to enable asynchronous communication between services. _ore asynchronous solution_ <br>
e.g: The Accounts and Transactions microservices can publish messages to a queue when they need to perform an operation that requires input from another microservice. For example, when creating an account, the Accounts microservice can publish a message to a queue that the Customers microservice is listening to. Once the Customers microservice receives the message, it can process the request and publish a response message to the queue that the Accounts microservice is listening to.

#### **Dependency injection**

Address better this like we did in the client layer where we separate the logic and interface
