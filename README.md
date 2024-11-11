# weatherMap
Weather Information API
This project provides a REST API to retrieve weather information based on a given pincode and date. The API fetches latitude and longitude based on the pincode using OpenWeather's Geocoding API and retrieves weather data using OpenWeather's Weather API. The retrieved data is cached in a database for optimized performance on subsequent requests.

Features
Fetches weather information by pincode and date.
Caches latitude, longitude, and weather data in a relational database.
Optimized API calls by using cached data if available.
REST API is testable with Postman or Swagger.
Structured code with services and repository layers for modularity.
Error handling and logging for easy debugging.

#Tech Stack
Java 8
Spring Boot
Spring Data JPA
H2 (in-memory database for development/testing)
RestTemplate (for making external API calls)
OpenWeather API (for geocoding and weather information)
Maven (for dependency management)


#API Documentation
Endpoint: Get Weather by Pincode
#URL: http://localhost:8080/api/weather

Method: GET

Parameters:
pincode (String): The pincode for which to fetch weather information (e.g., 411014).
forDate (String): The date for which to fetch weather data, in YYYY-MM-DD format (e.g., 2020-10-15).

Example Request:
http://localhost:8080/api/weather?pincode=411014&forDate=2020-10-15

Response: JSON object containing weather information for the specified pincode and date. If data is already cached in the database, it returns the cached data; otherwise, it fetches data from the API, caches it, and returns it.
