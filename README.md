# Lime Pay - Technical Assessment

## Table of Contents
1. [Objective](#objective)
2. [The Directors API](#the-directors-api)
3. [Running the application locally](#running-the-application-locally)
4. [Error Codes](#error-codes)


## Objective
Create a maintainable and extensible [Spring](https://spring.io/projects/spring-boot) REST API that would return the directors with the most movies directed. The API should be able to return the list of directors with movie count strictly greater than the given threshold. The list of names must be returned in alphabetical order.

## The Directors API
For this project, I only created one API to accomplish the objective. The API has the following endpoint `/api/directors/by-movie-count?threshold=<threshold>`. 

From the instruction, I should be getting our movies from this API: `https://wiremock.dev.eroninternational.com/api/movies/search?page=<pageNumber>`. Since the API was unavailable, I created a mock API using WireMock to simulate the expected response. I used a local instance of WireMock and the stub json should be available in this project's root named `movies_stub.json`.

### Logic
To achieve the goal, I utilized a HashMap to store the directors and the number of movies they have directed. While doing this, I also checked if a director has exceeded the given threshold and stored them in a HashSet.

To retrieve the movie data, I implemented a paginated API calls in a loop until all pages were processed. For each page, I extracted the director’s name from the movie data and updated the count in the HashMap using the compute method, which efficiently increments the count or initializes it if the director is encountered for the first time.

Once all pages were processed, I returned the directors who exceeded the threshold, ensuring the list was sorted for consistency before sending the response.

### Extra Features
Part of the evaluation criteria is following the best practices of Spring when it comes to developing an API. Furthermore, extra effort would also be considered. Below is a list of features that I think would greatly improve the API and the project as a whole: 

1. Caching - I took advantage of Spring caching to cache the response of the API. This would greatly improve the performance of the API as it would not need to make the same API calls to get the movie data.
2. Documentation - I have added a SpringDoc configuration to the APIs contract/interface so that the API documentation can be generated automatically. This would help other developers understand how to use the API.
3. Uniform API response - I have implemented a uniform way that the API response would follow common fields like message, code, and body. This would make it easier for developers and consumers to understand the response of the API. 
4. Error handling - I have added a global exception handler to handle exceptions thrown by the API. This would ensure that the API does not crash when an exception is thrown. In addition, I have added custom exceptions to handle specific errors that might occur (e.g. Invalid threshold provided).
5. Testing - I have added integration tests to fully test the API. This would ensure that the API works as expected and would help prevent bugs from being introduced when new features are added. 

 ### Left Out Feature
I thoroughly considered whether to add basic authentication to this API using Spring security. However, I decided against due to the following reasons: 
1. I felt like the API was intended for public use and adding basic authentication would make it harder for users to use the API.
2. I assumed that this would be tested by other engineers and adding some security features would make it harder for them to test the API instead of being straight forward with it. Worst case scenario, the testers would completely not be able to test the API. 

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.example.limepay.LimePayApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:
```shell
mvn clean install
```
```shell
mvn spring-boot:run
```
Ensure that Maven is installed on your machine and that you are running Java 17, as the project is compiled with Java 17.

## Sample Request & Response

###### Sample Request
```shell
curl --location --request GET 'localhost:8080/api/directors/by-movie-count?threshold=1'
```
###### Sample Success Response
```json
{
  "message": "success",
  "code": "0",
  "body": {
    "directors": [
      "Christopher Nolan",
      "David Fincher"
    ]
  }
}
```
###### Sample Error Response
```json
{
  "message": "error",
  "code": "999",
  "errorCode": {
    "code": "002",
    "message": "No directors directed more than the requested threshold"
  }
}
```

## Error Codes

| HTTP Status Code |   Status  |   Code        |   Description                                                                    
|------------------|   ------  |   --------    |   -----------------------------------------                                      
| 500              |   error   |   999         |   Internal Server Error                                                                 
| 400              |   error   |   001         |   Invalid requested threshold                                                         
| 404              |   error   |   002         |   No directors directed more than the requested threshold