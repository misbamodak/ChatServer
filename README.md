# Chat Server Application

## Overview
This project is a simple chat server application built with Spring Boot and Hibernate. It allows users to log in, send messages, view chat history, delete message.

## Setup Instructions
### Prerequisites
- Java Development Kit (JDK) 17 or later
- Maven

### Installation
1. Clone the repository: `git clone https://github.com/yourusername/chat-server.git`
2. Navigate to the project directory: `cd ChatServer`

### Running the Application
1. Build the project: `mvn clean package`
2. Run the application: `java -jar target/chatServer-0.0.1-SNAPSHOT`

## Usage
### Login
Endpoint: `POST /chat/login`
- Request Body: `{ "username": "admin", "password": "admin123" }`
- Response: `Login successful!` or `Invalid credentials!`

### Send Message
Endpoint: `POST /chat/send`
- Request Body: `{ "username": "admin", "message": "Hello, world!" }`
- Response: `Message sent successfully!`

### Get Chat History
Endpoint: `GET /chat/history`
- Response: List of chat messages in JSON format

### Delete Message
- **Endpoint:** `DELETE /chat/delete`
- **Request Body:** `{ "messageId": 1 }`
- **Response:** `Message deleted successfully!`


## Testing
To run tests, execute: `mvn test`
