# Geolocations platform

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Installation](#installation)
* [Testing](#testing)
* [Usage](#api-usage)
* [License](#license)

## General info
The Java aplication - Geolocations platform - allows to find the nearest localization from those which has been added previously.

## Technologies

* Java 8
* MySQL 8.0.15
* Spring Boot 2.14
* Flyway
* Gradle
* Docker

## Installation

To run this project you need to install:
* [docker](https://www.docker.com/get-started)
* [docker-compose](https://docs.docker.com/compose/gettingstarted/)
* [gradle](https://docs.gradle.org/current/userguide/getting_started.html) or use gradle-wrapper from project dir ([gradlew](https://github.com/adamkrupa96/geolocations-platform/blob/master/backend/gradlew))

1. Go to project directory and run command line:

```bash
cd backend
gradle build      
./gradlew build (if you did not install gradle locally)

cd ../environment
cat >> .env <<EOF
> DB_USER=your_user
> DB_PASSWORD=your_db_password
> DB_NAME=your_db_name
> EOF

docker-compose -f docker-compose-prod.yml up -d

```
Application will be started at port 8080.

## Testing
To run unit tests, go the command line:
```bash
cd backend
gradle clean test --info
```

## API Usage

You can test this API with cURL.

To save locations in DB:
```cURL
curl -X POST \
  http://localhost:8080/api/locations \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
    "locations": [
        {
            "name": "McDonald'\''s Lwowska",
            "latitude": 50.037854,
            "longitude": 22.022061
        },
        {
            "name": "Hospital",
            "latitude": 50.036569,
            "longitude": 22.035318
        },
        {
            "name": "Airport Jasionka",
            "latitude": 50.115627,
            "longitude": 22.024593
        },
        {
        	"name": "Madrid",
        	"latitude": 40.416961,
        	"longitude": -3.703782
        }
    ]
}'
```

To find the nearest position, provide ``latitude`` and ``longitude`` query params, in example:
```curl
curl -X GET \
  'http://localhost:8080/api/locations/search/nearestPoint?latitude={latitude}&longitude={longitude}' \
  -H 'cache-control: no-cache'
```

## License
[MIT](https://github.com/adamkrupa96/geolocations-platform/blob/master/LICENSE)