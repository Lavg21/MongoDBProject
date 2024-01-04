package com.example.library.mappers;

import com.example.library.domain.Author;
import com.example.library.domain.Category;
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

    public static Category documentToCategory(Document document) {
        return new Category(document.get("_id").toString(),
                document.get("name").toString(),
                document.get("description").toString(),
                Integer.parseInt(document.get("numberOfBooks").toString()),
                Boolean.parseBoolean(document.get("isActive").toString()),
                document.get("createdBy").toString());
    }
}
