package com.example.library.mappers;

import com.example.library.domain.Author;
import org.bson.Document;

public class EntityToDocumentMapper {

    public static Document authorToDocument(Author author) {
        Document document = new Document();
        document.append("name", author.getName());
        document.append("age", author.getAge());
        return document;
    }
}
