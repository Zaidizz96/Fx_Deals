# Fx Deals

## Description
This project is part of a scrum team developing a data warehouse for Bloomberg to analyze FX deals. It accepts deal details and persists them into a database.

## Table of Contents
- [Features](#features)
- [Technologies](#Technologies)
- [Getting Started](#getting-started)
- [Testing](#testing)
- [Documentation](#documentation)


## Features
- Accepts the deal information from the customer and save them into mysql database.
- Validates row structure, handling missing fields and type formats.
- Prevents importing the same request twice.
- No rollback allowed; every imported row is saved in the database.

## Prerequisites
- Java (for building the project)
- Docker
- Maven 
- Spring data jpa
- Spring data test

## Getting Started
1. Clone the repository:
   
   git clone https://github.com/<your_github_username>/<your_project_name>.git
   cd <your_project_name>

2. Run the project:
   1- mvn clean install -DskipTests
   2- docker image build . -t fxdeals:v1
   3- docker-compose up

## Documentation 
### DealController

#### `POST /api/deals`
Endpoint to add a new FX deal.

##### Request
- Method: `POST`
- URL: `/api/deals`
- Body: JSON representing the FX deal details.
```json
{
  "dealUniqueId": 123456789,
  "fromCurrencyISOCode": "USD",
  "toCurrencyISOCode": "EUR",
  "dealTimeStamp": "2024-02-16T12:34:56",
  "dealAmount": 5000.00
}
```
#### `GET /api/deals/{dealId}`
Endpoint to retrieve an FX deal by its unique ID.

##### Request
- Method: `GET`
- URL: `/api/deals/{dealId}`
Example  dealId : 123456789
##### Response
``` json
{
  "id": 1,
  "dealUniqueId": 123456789,
  "fromCurrencyISOCode": "USD",
  "toCurrencyISOCode": "EUR",
  "dealTimeStamp": "2024-02-16T12:34:56",
  "dealAmount": 5000.00
}
```

## Testing
Run unit Tests 
``` bash
# For Maven
mvn test

# For Gradle
./gradlew test
```



