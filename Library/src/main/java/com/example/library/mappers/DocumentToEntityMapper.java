package com.example.library.mappers;

import com.example.library.domain.entity.*;
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

    public static Book documentToBook(Document document) {
        return new Book(document.get("_id").toString(),
                document.get("title").toString(),
                document.get("publicationYear").toString(),
                document.get("genre").toString(),
                document.get("authorId").toString(),
                document.get("categoryId").toString());
    }

    public static Reader documentToReader(Document document) {
        return new Reader(document.get("_id").toString(),
                document.get("firstname").toString(),
                document.get("lastname").toString(),
                document.get("email").toString(),
                document.get("phone").toString());
    }

    public static Loan documentToLoan(Document document) {
        return new Loan(document.get("_id").toString(),
                document.get("bookId").toString(),
                document.get("readerId").toString(),
                document.get("loanDate").toString(),
                document.get("dueDate").toString(),
                document.get("returnDate").toString());
    }
}
