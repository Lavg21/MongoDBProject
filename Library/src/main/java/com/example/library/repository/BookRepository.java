package com.example.library.repository;

import com.example.library.connector.MongoDBConnector;
import com.example.library.domain.Book;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    private final MongoCollection<Book> bookCollection;

    public BookRepository() {
        bookCollection = MongoDBConnector.getDatabase().getCollection("book", Book.class);
    }

    public void createBook(Book book) {
        bookCollection.insertOne(book);
    }

    public Book getBookById(String bookId) {
        return bookCollection.find(Filters.eq("_id", new ObjectId(bookId))).first();
    }

    public void updateBook(String bookId, Book updatedBook) {
        bookCollection.replaceOne(Filters.eq("_id", new ObjectId(bookId)), updatedBook);
    }

    public void deleteBook(String bookId) {
        bookCollection.deleteOne(Filters.eq("_id", new ObjectId(bookId)));
    }

    public List<Book> getAllBooks() {
        List<Book> allBooks = new ArrayList<>();
        FindIterable<Book> booksIterable = bookCollection.find();

        try (MongoCursor<Book> cursor = booksIterable.iterator()) {
            while (cursor.hasNext()) {
                allBooks.add(cursor.next());
            }
        }

        return allBooks;
    }
}
