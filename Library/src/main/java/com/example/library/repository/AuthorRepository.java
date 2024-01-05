package com.example.library.repository;

import com.example.library.connector.MongoDBConnector;
import com.example.library.domain.entity.Author;
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
public class AuthorRepository {

    private final MongoCollection<Document> authorCollection;
    private final BookRepository bookRepository;

    public AuthorRepository() {

        authorCollection = MongoDBConnector.getDatabase().getCollection("author");
        bookRepository = new BookRepository();
    }

//    public void createAuthor(Author author) {
//        Document document = EntityToDocumentMapper.authorToDocument(author);
//        authorCollection.insertOne(document);
//    }

    public Author createAuthor(Author author) {
        Document authorDocument = EntityToDocumentMapper.authorToDocument(author);
        authorCollection.insertOne(authorDocument);

        // Retrieve the generated _id for the newly inserted author
        String authorId = authorDocument.getObjectId("_id").toString();

        // Associate books with the author
        if (author.getBooks() != null) {
            for (Book book : author.getBooks()) {
                book.setAuthorId(authorId);
                bookRepository.createBook(book);
            }
        }

        return author;
    }

//    public Author getAuthorById(String authorId) {
//        BasicDBObject searchQuery = new BasicDBObject();
//        searchQuery.put("_id", new ObjectId(authorId));
//        for (Document document : authorCollection.find(searchQuery)) {
//            return DocumentToEntityMapper.documentToAuthor(document);
//        }
//
//        return null;
//    }

    public Author getAuthorById(String authorId) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", new ObjectId(authorId));

        for (Document document : authorCollection.find(searchQuery)) {
            Author author = DocumentToEntityMapper.documentToAuthor(document);

            // Retrieve and set the associated books
            List<Book> books = bookRepository.getBooksByAuthorId(author.get_id());
            author.setBooks(books);

            return author;
        }

        return null;
    }

    public void updateAuthor(String authorId, Author updatedAuthor) {
        authorCollection.updateOne(Filters.eq("_id", new ObjectId(authorId)), Updates.set("name", updatedAuthor.getName()));
        authorCollection.updateOne(Filters.eq("_id", new ObjectId(authorId)), Updates.set("email", updatedAuthor.getEmail()));
        authorCollection.updateOne(Filters.eq("_id", new ObjectId(authorId)), Updates.set("genre", updatedAuthor.getGenre()));
        authorCollection.updateOne(Filters.eq("_id", new ObjectId(authorId)), Updates.set("country", updatedAuthor.getCountry()));
        authorCollection.updateOne(Filters.eq("_id", new ObjectId(authorId)), Updates.set("age", updatedAuthor.getAge()));
    }

    public void deleteAuthor(String authorId) {
        authorCollection.deleteOne(Filters.eq("_id", new ObjectId(authorId)));
    }

//    public List<Author> getAllAuthors() {
//        FindIterable<Document> iterDoc = authorCollection.find();
//        List<Author> authors = new ArrayList<>();
//        for (Document document : iterDoc) {
//            Author author = DocumentToEntityMapper.documentToAuthor(document);
//            authors.add(author);
//        }
//
//        return authors;
//    }

    public List<Author> getAllAuthors() {
        FindIterable<Document> iterDoc = authorCollection.find();
        List<Author> authors = new ArrayList<>();

        for (Document document : iterDoc) {
            Author author = DocumentToEntityMapper.documentToAuthor(document);

            // Retrieve and set the associated books
            List<Book> books = bookRepository.getBooksByAuthorId(author.get_id());
            author.setBooks(books);

            authors.add(author);
        }

        return authors;
    }

    public boolean isEmailUnique(String email) {
        return authorCollection.countDocuments(Filters.eq("email", email)) == 0;
    }

    public boolean isNameUnique(String name) {
        return authorCollection.countDocuments(Filters.eq("name", name)) == 0;
    }

    public boolean isAuthorExists(String authorId) {
        return authorCollection.countDocuments(Filters.eq("_id", new ObjectId(authorId))) > 0;
    }
}

