# Newsletter coding challenge

The application is an API for creating subscriptions.

It consists of three microservices:

- Public API: Exposed to frontend. Entry point for subscriptions. It validates also the received data.

- Private API: Persists the subscriptions. It Does some aditional logic to schedule an email to the user.

- Email Service: It sends scheduled emails to the users

Subscriptions and pending emails are stored in a mysql database.

## Build Process

### For public API

Build springboot standalone JAR
```
cd newsletter-public-api
mvn clean package
```

Docker Image Build
```
docker build -t miravete92/newsletter-public-api .
```

### For private API

Build springboot standalone JAR
```
cd newsletter-private-api
mvn clean package
``` 

Docker Image Build
```
docker build -t miravete92/newsletter-private-api .
```

### For email service

Build springboot standalone JAR
```
cd send-mail-service
mvn clean package
``` 

Docker Image Build
```
docker build -t miravete92/send-mail-service .
```

### Database initialization
The application is configured to access *db_newsletter* schema. We must create that in our local mysql instance or change it in the *application.properties* file of private API and email service.

Docker Image Build (Initializes mysql database with newsletter table and a newsletter registry with id = 1)
```
cd newsletter-database
docker build -t miravete92/newsletter-database .
```

### Docker compose

```
cd docker
docker-compose up -d
```

Due to initialization times, private and email service could throw an error when they try to connect to database in it's not already alive.
Check if these service are alive
```
docker-compose ps
```
If not, reinitialize them typing again
```
docker-compose up -d
```

## How services work

### Public API

Receives a subscription request from client, validates the data format and sends it to the private API.
If private API is down, stores failed subscription requests into a queue and retries in a few seconds.
Public api never waits for private API reponse to send a response to the client. If data is valid, sends OK to the client.

### Private API

Receives a subscription request from public api, it also validates the data format, stores this subscription into database, schedules a mail (adding a registry into pendingMails table in database) and returns subscription new ID.
If subscription newsletterId and email entered already match with a subscription registry, it updates the aditional data (name, gender, consent...) and does not send a new email.



