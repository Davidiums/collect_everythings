# E-Commerce Microservices Application
This application is a microservices-based e-commerce platform consisting of the following functional services:

- **Gateway Service**
- **Authentication Service (Auth)**
- **Item Service**
- **Basket Service**

# Setup 

Before launching the services, run the following command to start the required infrastructure:

```
docker-compose up -d
```

This will set up :
- RabbitMQ
- MariaDB
- PhpMyAdmin

## Start the Services

Launch each of the services (Gateway, AuthService, ItemService, BasketService) individually using your preferred method (e.g., via your IDE or command line).

# API Endpoints Authentication
## Obtain JWT Token

### Endpoint:

``` POST http://localhost:8080/auth/login ```

Available Users
You can use the following users to authenticate and interact with the services:

| User     | Password                  |
|----------|---------------------------|
| user1    | 123456                    |
| admin1   | 123456                    |
| cacatoe1 | 123456                    |

### Parameters:

| Parameter | Description                  |
|-----------|------------------------------|
| username  | Your username (e.g., user1)  |
| password  | Your password (e.g., 123456) |

### Description:

Send a POST request with username and password to receive a JWT token, which is required for authenticated endpoints.

### Response:

```
{
"success": true,
"message": "Authentication réussie",
"token": "<your_jwt_token>"
} 
```

# Item Service
## Get Available Items

### Endpoint:

``` GET http://localhost:8080/item/all ```

### Description:

Retrieve a list of all available items in the catalog.

### Response:

```
[
    {
        "id": 1,
        "name": "Smartphone",
        "description": "Un smartphone de dernière génération",
        "category": {
        "id": 1,
        "name": "Électronique",
        "description": "Appareils électroniques et gadgets"
        },
        "price": 699.99,
        "stock": 50
    }, // ...other items
]
```

# Basket Service

For the Basket Service, you should include the JWT token in the Authorization header for authenticated endpoints.
``` Authorization: Bearer <your_jwt_token> ```

## Get Your Basket

Endpoint:

``` GET http://localhost:8080/basket/basket ```

### Description:

Retrieve the contents of your basket.


## Add Item to Your Basket
Endpoint:
``` POST http://localhost:8080/basket/items/addItem ```

### Parameters:
- ```itemId ``` The ID of the item to add.

### Description:

Add an item to your basket. Requires authentication.

## Remove Item from Your Basket
Endpoint: ```DELETE http://localhost:8080/basket/items/removeItem ```

### Parameters:

- ```itemId ``` The ID of the item to remove.

### Description:

# Architecture Overview
The application follows a microservices architecture, with each service handling a specific domain:

- Gateway Service: Acts as a single entry point to the system, routing requests to the appropriate services.
- Authentication Service: Manages user authentication and JWT token generation.
- Item Service: Handles item catalog operations, such as retrieving available items.
- Basket Service: Manages user baskets, allowing items to be added or removed.

![Schéma de fonctionnement](schema.png)

# RabbitMQ Usage
RabbitMQ is used as a message broker for asynchronous communication between services:

- Retrieving User ID: 
  - When adding an item to the basket, the Basket Service uses RabbitMQ to communicate with the Authentication Service to retrieve the user's ID from the provided JWT token.

- Item Verification:
  - The Basket Service also communicates with the Item Service via RabbitMQ to verify if the item exists before adding it to the basket.

# Non-Functional Services
The following services are planned but not yet developed:

- Payment Service
- Subscription Service
- 
Note: These services are placeholders and currently do not function.

# Notes
## Authentication Service Anomaly:

The Authentication Service currently has an unusual implementation where the user authentication process unnecessarily goes through RabbitMQ. This was initially implemented as a Proof of Concept (POC) and will be corrected in future iterations to streamline the authentication flow.

## Testing Tips:

Ensure that you include the Authorization header with the Bearer token when accessing secured endpoints.
Use the provided usernames and password for testing authentication and authorization.
Verify that the docker-compose services are running correctly before starting the application services.