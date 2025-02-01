# Receipt Processor

# Overview

This project is a REST API built using Spring Boot to store receipts and calculates reward points based on predefined rules as per the APIs structure defined in api.yml file. The data is stored in-memory using a ConcurrentHashMap, meaning it does not persist after the application is restarted.

# Technologies Used:
Java 21
Spring Boot 3.2.1
Spring Web (for REST API development)
ConcurrentHashMap (for thread-safe in-memory storage)
Postman (for testing API requests)
Docker (for containerized deployment)

# API Endpoints

# 1. Process Receipt (POST /receipts/process)
This takes in JSON input of the reciept details and returns a unique id as JSON response.

# 2. Get Points for a Receipt (GET /receipts/{id}/points)
Based on the receipt id provided, it returns the total points based on the rules specified.

# Explanation of Code Files:

# ReceiptProcessorApplication.java
The main class that bootstraps the Spring Boot application.

# ReceiptController.java
Defines the API endpoints (/receipts/process and /receipts/{id}/points).
Delegates the processing logic to ReceiptService.

# ReceiptService.java
Contains business logic for processing receipts and calculating points.
Uses a ConcurrentHashMap<String, Receipt> to store receipts in memory.

# Receipt.java & Item.java
Receipt.java: Represents the structure of a receipt.
Item.java: Represents individual items in the receipt.

# Instructions to run the application:

# Using Maven:
mvn spring-boot:run

# Using Docker:
Docker file is already created. 



# Build the docker image using: 
docker build -t receipt-processor .

# Run the Docker container:
docker run -p 8080:8080 receipt-processor
