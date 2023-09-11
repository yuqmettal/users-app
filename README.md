# Users Service
Springboot and Java project to create users .

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Tests](#tests)
- [Project Structure](#project-structure)

## Installation

**Prerequisites**: Ensure you have Java 17+ and Gradle 8+ installed.

```bash
# Clone this repository
git clone git@github.com:yuqmettal/users-app.git

# Navigate to the project directory
cd users-app

# Build the project
gradle build
```

## Usage

This is an API REST application.

### API Endpoints (if applicable)

- `POST /users`: Endpoint implemented to create users

Example Request:

```bash
curl -X POST http://yourapi.com/users -d '{
    "name": "Juan Rodriguez",
    "email": "aaaaaaa@dominio.cl",
    "password": "X10slnp@pass",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}'
```

Example Response:
```json
{
  "id": "4ceff08e-52ff-4499-981b-972acf139fce",
  "created": "2023-09-11T16:13:29.329+00:00",
  "modified": "2023-09-11T16:13:29.329+00:00",
  "last_login": "2023-09-11T16:13:29.329+00:00",
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYWFhYWFhQGRvbWluaW8uY2wifQ.bUtVCClkiWatdTFd84IszwLNJ_6d3LwHF2-l0GMGI70",
  "isactive": "true"
}
```

## Tests

To run unit and integration tests:
```bash
gradle test
```

## Project Structure

- `src/main/java`: Application source code.
- `src/test/java`: Test cases for the application.
- `src/main/resources`: Configuration and other static files.


## Application diagram

![](/Users/yuqui/nisum/users/users-app.png)


