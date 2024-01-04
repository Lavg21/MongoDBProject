package com.example.library.repository;

import com.example.library.connector.MongoDBConnector;
import com.example.library.domain.entity.Book;
import com.example.library.mappers.DocumentToEntityMapper;
import com.example.library.mappers.EntityToDocumentMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    private final MongoCollection<Document> bookCollection;

    public BookRepository() {
        bookCollection = MongoDBConnector.getDatabase().getCollection("book");
    }

    public void createBook(Book book) {
        Document document = EntityToDocumentMapper.bookToDocument(book);
        bookCollection.insertOne(document);
    }

    public Book getBookById(String bookId) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", new ObjectId(bookId));
        for (Document document : bookCollection.find(searchQuery)) {
            return DocumentToEntityMapper.documentToBook(document);
        }

        return null;
    }

    public void updateBook(String bookId, Book updatedBook) {
        bookCollection.updateOne(Filters.eq("_id", new ObjectId(bookId)), Updates.set("title", updatedBook.getTitle()));
        bookCollection.updateOne(Filters.eq("_id", new ObjectId(bookId)), Updates.set("publicationYear", updatedBook.getPublicationYear()));
        bookCollection.updateOne(Filters.eq("_id", new ObjectId(bookId)), Updates.set("genre", updatedBook.getGenre()));
        bookCollection.updateOne(Filters.eq("_id", new ObjectId(bookId)), Updates.set("authorId", updatedBook.getAuthorId()));
        bookCollection.updateOne(Filters.eq("_id", new ObjectId(bookId)), Updates.set("categoryId", updatedBook.getCategoryId()));
    }

    public void deleteBook(String bookId) {
        bookCollection.deleteOne(Filters.eq("_id", new ObjectId(bookId)));
    }

    public List<Book> getAllBooks() {
        FindIterable<Document> iterDoc = bookCollection.find();
        List<Book> books = new ArrayList<>();
        for (Document document : iterDoc) {
            Book book = DocumentToEntityMapper.documentToBook(document);
            books.add(book);
        }

        return books;
    }

    public boolean isBookExists(String bookId) {
        return bookCollection.countDocuments(Filters.eq("_id", new ObjectId(bookId))) > 0;
    }
}
