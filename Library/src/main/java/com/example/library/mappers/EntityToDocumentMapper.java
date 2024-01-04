package com.example.library.mappers;

import com.example.library.domain.Author;
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

}
