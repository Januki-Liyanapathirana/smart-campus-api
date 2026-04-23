# Smart Campus REST API

## Overview

This project implements a RESTful API for managing a smart campus system. It supports room management, sensor management, and sensor readings with validation, filtering, nested resources, and proper error handling.

The API is versioned using `/api/v1` and follows RESTful design principles.

---

## Technologies Used

* Java (JAX-RS - Jersey)
* Maven
* Apache Tomcat
* JSON (Jackson)

---

## How to Run

1. Open the project in NetBeans
2. Clean and Build the project
3. Run the project on Apache Tomcat
4. Access the API at:

```
http://localhost:8080/smart-campus-api/api/v1
```

---

## API Endpoints

### Discovery

* GET `/api/v1`

### Rooms

* GET `/api/v1/rooms`
* POST `/api/v1/rooms`
* GET `/api/v1/rooms/{id}`
* DELETE `/api/v1/rooms/{id}`

### Sensors

* GET `/api/v1/sensors`
* GET `/api/v1/sensors?type=Temperature`
* POST `/api/v1/sensors`
* GET `/api/v1/sensors/{id}`

### Readings (Sub-resource)

* GET `/api/v1/sensors/{id}/readings`
* POST `/api/v1/sensors/{id}/readings`

---

## Sample curl Commands

### Create Room

```
curl -X POST http://localhost:8080/smart-campus-api/api/v1/rooms \
-H "Content-Type: application/json" \
-d '{"id":1,"name":"Lab 1","capacity":40}'
```

### Create Sensor

```
curl -X POST http://localhost:8080/smart-campus-api/api/v1/sensors \
-H "Content-Type: application/json" \
-d '{"id":1,"type":"Temperature","roomId":1}'
```

### Add Reading

```
curl -X POST http://localhost:8080/smart-campus-api/api/v1/sensors/1/readings \
-H "Content-Type: application/json" \
-d '{"id":1,"value":26.5}'
```

### Get Sensors (Filtered)

```
curl http://localhost:8080/smart-campus-api/api/v1/sensors?type=Temperature
```

### Get Readings

```
curl http://localhost:8080/smart-campus-api/api/v1/sensors/1/readings
```

---

## Error Handling

* 404 Not Found – Resource does not exist
* 409 Conflict – Room contains active sensors
* 400 / 422 – Invalid request or linked resource
* 500 Internal Server Error – Unexpected failure

---

# Part 1: Service Architecture & Setup

## 1. Project & Application Configuration
   Answer:
   By default, JAX-RS resource classes are per request instantiated. It means that a new instance will be created for each HTTP request instead of keeping a singleton instance.
   Each request is independent, improving thread safety of the app. However, shared in- memory data structures such as maps must still be handled carefully to avoid race conditions. Centralized data storage is used in this project for data integrity and consistency.
## 2. The ”Discovery” Endpoint
   Answer:
   HATEOAS enables clients to navigate an API dynamically through links provided in responses. This reduces dependency on static documentation and allows clients to discover available operations at runtime. This approach enhances usability, flexibility and maintainability for client developers.

# Part 2: Room Management

## 1. Room Resource Implementation
   Answer:
   Returning only IDs improves performance and reduces payload size but that means additional requests are necessary. Returning full objects provides more information in a single response but increases data size. The choice depends on the use case and efficiency requirements of the system.
## 2. Room Deletion & Safety Logic
   Answer:
   DELETE is idempotent because multiple identical requests result in the same outcome. Once a resource is deleted, repeating the request does not change the system state further.
   
# Part 3: Sensor Operations & Linking

## 4. Sensor Resource & Integrity
   Answer:
   The API uses @Consumes(MediaType.APPLICATION_JSON) to ensure that only JSON input is accepted. Requests with unsupported formats result in a 415 error.
## 5. Filtered Retrieval & Search
   Answer:
   Query parameters are used for filtering because they are optional and flexible. Path parameters represent specific resources, while query parameters refine collections.

# Part 4: Deep Nesting with Sub – Resources

## 1. The Sub-Resource Locator Pattern
   Answer:
   The sub-resource locator pattern improves modularity by delegating nested resource handling to dedicated classes. This results in cleaner and more maintainable code.

# Part 5: Advanced Error Handling, Exception Mapping & Logging
## 2. Dependency Validation
Answer:
HTTP 422 is used when the request is valid but contains invalid data (e.g., non-existent related resource). HTTP 404 indicates that the requested endpoint does not exist.
## 4. The Global Safety Net (500)
Answer:
Exposing stack traces reveals internal implementation details, which can be exploited. A global exception handler is used to return safe, generic error messages.
## 5. API Request & Response Logging Filters
Answer:
Logging filters centralize request and response logging, reducing duplication and improving maintainability.


## Author

Januki Liyanapathirana

