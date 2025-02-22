# 📚 Library Management System

> A MongoDB-based library management system with RESTful APIs.

## 🚀 Overview

This project is a **Library Management System** that enables CRUD operations on authors, books, categories, readers, and loan records using **Spring Boot** and **MongoDB**. The system is designed to handle various operations such as creating, reading, updating, and deleting (CRUD) data related to books, authors, members, and other entities. It supports various relational models, including one-to-one (1-1), one-to-many (1-m), and many-to-many (m-m) relationships. Additionally, the backend is connected to a cloud database cluster to ensure scalability and reliability.

## 🛠️ Features

✅ **Manage Authors, Books, Categories, and Readers**  
✅ **CRUD Operations via RESTful API**  
✅ **MongoDB for NoSQL Storage**  
✅ **Postman Collection for API Testing**  

## 📜 Table of Contents

- [Architecture](#architecture)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Technologies Used](#technologies-used)
  
## 🏗 Architecture

The system follows a **RESTful microservice architecture**, consisting of:

- **Author Controller**: Manages authors.
- **Book Controller**: Handles book-related operations.
- **Category Controller**: Manages book categories.
- **Reader Controller**: Handles reader records.
- **Loan Controller**: Manages book loans.
- **Database**: MongoDB.

## 💻 Installation

### Prerequisites
- Java 17+
- Maven
- MongoDB

## 🎮 Usage

### Postman Collection

Import the provided `MongoDB.postman_collection.json` into Postman to test API endpoints.

## 🌍 API Endpoints

### Author Controller
- `GET /authors` → Retrieve all authors
- `GET /authors/{id}` → Get author by ID
- `POST /authors` → Create a new author
  - **Request Body:**
    ```json
    {
      "name": "Author Name",
      "email": "author@example.com",
      "genre": "Fiction",
      "country": "USA",
      "age": 45
    }
    ```
- `PUT /authors/{id}` → Update an author
- `DELETE /authors/{id}` → Delete an author

### Book Controller
- `GET /books` → Retrieve all books
- `GET /books/{id}` → Get book by ID
- `POST /books` → Add a new book
  - **Request Body:**
    ```json
    {
      "title": "Book Title",
      "publicationYear": "2023",
      "genre": "Science Fiction",
      "authorId": "authorId123",
      "categoryId": "categoryId456"
    }
    ```
- `PUT /books/{id}` → Update book details
- `DELETE /books/{id}` → Remove a book

### Category Controller
- `GET /categories` → Retrieve all categories
- `GET /categories/{id}` → Get category by ID
- `POST /categories` → Create a new category
  - **Request Body:**
    ```json
    {
      "name": "Fantasy",
      "description": "Fantasy books",
      "numberOfBooks": 100,
      "isActive": true,
      "createdBy": "admin"
    }
    ```
- `PUT /categories/{id}` → Update a category
- `DELETE /categories/{id}` → Delete a category

### Reader Controller
- `GET /readers` → Retrieve all readers
- `GET /readers/{id}` → Get reader by ID
- `POST /readers` → Register a new reader
  - **Request Body:**
    ```json
    {
      "firstname": "John",
      "lastname": "Doe",
      "email": "john.doe@example.com",
      "phone": "+1234567890"
    }
    ```
- `PUT /readers/{id}` → Update reader information
- `DELETE /readers/{id}` → Remove a reader

### Loan Controller
- `GET /loans` → Retrieve all loans
- `GET /loans/{id}` → Get loan details by ID
- `POST /loans` → Create a new loan
  - **Request Body:**
    ```json
    {
      "bookId": "bookId789",
      "readerId": "readerId321",
      "loanDate": "2024-01-01",
      "dueDate": "2024-02-01",
      "returnDate": "2024-01-25"
    }
    ```
- `PUT /loans/{id}` → Update a loan
- `DELETE /loans/{id}` → Remove a loan record
  
## ⚡ Technologies Used

- **Backend**: Java, Spring Boot
- **Database**: MongoDB
- **API Testing**: Postman
