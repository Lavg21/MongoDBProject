package com.example.library.mappers;

import com.example.library.domain.*;
import org.bson.Document;

public class EntityToDocumentMapper {

    public static Document authorToDocument(Author author) {
        Document document = new Document();
        document.append("name", author.getName());
        document.append("email", author.getEmail());
        document.append("genre", author.getGenre());
        document.append("country", author.getCountry());
        document.append("age", author.getAge());
        return document;
    }

    public static Document categoryToDocument(Category category) {
        Document document = new Document();

        document.append("name", category.getName());
        document.append("description", category.getDescription());
        document.append("numberOfBooks", category.getNumberOfBooks());
        document.append("isActive", category.isActive());
        document.append("createdBy", category.getCreatedBy());
        return document;
    }

    public static Document bookToDocument(Book book) {
        Document document = new Document();

        document.append("title", book.getTitle());
        document.append("publicationYear", book.getPublicationYear());
        document.append("genre", book.getGenre());
        document.append("authorId", book.getAuthorId());
        document.append("categoryId", book.getCategoryId());
        return document;
    }

    public static Document readerToDocument(Reader reader) {
        Document document = new Document();

        document.append("firstname", reader.getFirstname());
        document.append("lastname", reader.getLastname());
        document.append("email", reader.getEmail());
        document.append("phone", reader.getPhone());
        return document;
    }

    public static Document loanToDocument(Loan loan) {
        Document document = new Document();

        document.append("bookId", loan.getBookId());
        document.append("readerId", loan.getReaderId());
        document.append("loanDate", loan.getLoanDate());
        document.append("dueDate", loan.getDueDate());
        document.append("returnDate", loan.getReturnDate());
        return document;
    }
}
