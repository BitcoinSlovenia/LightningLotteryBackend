
# URL
- http://localhost:8080/lightningLottery/swagger-ui.html


test new ticket
```
{
  "callback_url": "",
  "customer_email": "kristjan.grm1@gmail.com",
  "customer_name": "kristjan",
  "description": "gtrm",
  "numbers": [
    "1","2",
  ],
  "success_url": ""
}
```

en delujoč lightning payment
```
{
  "ticket_id": null,
  "openNodeId": "695184b8-4c5b-416d-a78c-eb2af1848e2e",
  "customer_name": "kristjan",
  "customer_email": "kristjan.grm1@gmail.com",
  "numbers": [
    "1",
    "2"
  ],
  "status": "UNPAID",
  "amount": 200,
  "fiat_value": 0.01568144,
  "ticketRequest": null,
  "lightningInvoice": {
    "settledAt": 0,
    "payreq": "LNBC2U1P0ZWHCJPP526LTUYP0F44XXYJPQYZHW5RTL922M8VVAXSGFHV2JGX3PVLR7CYQDQ8VA68YMGCQZPGZ0ECEFY5LCWEEAEE9NE0SN8CH45QQZ6QV5ZASGDDXWE5RSWMKZLSV3VVY874GSR4E30EUVJH9T8VV0TF9V56W7MK9UL9DTL7RK7YXFQPPTJZDF"
  }
}
```

## URL
http://localhost:8080/swagger-ui.html#


### Flyway  
Database is versioned using flyway.  
**Useful links:**  
- https://rieckpil.de/howto-best-practices-for-flyway-and-hibernate-with-spring-boot/ 
- https://www.callicoder.com/spring-boot-flyway-database-migration-example/
#### Settings
This will validate jpa models with database that flyway creates
`spring.jpa.hibernate.ddl-auto=validate`



## BUILDING AND DOCKER IMAGE
we unpack the fat jar and copy it into docker
https://spring.io/guides/gs/spring-boot-docker/ (scroll to text: Also, to take advantage of the clean separation between dependencies and application resources in a Spring Boot fat jar file, we will use a slightly different implementation of the Dockerfile:)

`mvn clean package`
`mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)`

### maven docker
TODO Build a Docker Image with Maven https://spring.io/guides/gs/spring-boot-docker/ 

docker build -t bitcoinslovenia/lightningloterry:tagname .
