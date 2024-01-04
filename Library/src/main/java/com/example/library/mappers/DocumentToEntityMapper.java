package com.example.library.mappers;

import com.example.library.domain.Author;
import org.bson.Document;

public class DocumentToEntityMapper {

    public static Author documentToAuthor(Document document) {
        return new Author(document.get("_id").toString(),
                document.get("name").toString(),
                document.get("email").toString(),
                document.get("genre").toString(),
                document.get("country").toString(),
                Integer.parseInt(document.get("age").toString()));
    }
}
