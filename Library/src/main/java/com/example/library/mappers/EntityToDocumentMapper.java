package com.example.library.mappers;

import com.example.library.domain.Author;
import com.example.library.domain.Category;
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

}
