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

## Author

Januki Liyanapathirana

