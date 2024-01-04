package com.example.library.repository;

import com.example.library.connector.MongoDBConnector;
import com.example.library.domain.Author;
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

    public AuthorRepository() {
        authorCollection = MongoDBConnector.getDatabase().getCollection("author");
    }

    public void createAuthor(Author author) {
        Document document = EntityToDocumentMapper.authorToDocument(author);
        authorCollection.insertOne(document);
    }

    public Author getAuthorById(String authorId) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", new ObjectId(authorId));
        for (Document document : authorCollection.find(searchQuery)) {
            return DocumentToEntityMapper.documentToAuthor(document);
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

    public List<Author> getAllAuthors() {
        FindIterable<Document> iterDoc = authorCollection.find();
        List<Author> authors = new ArrayList<>();
        for (Document document : iterDoc) {
            Author author = DocumentToEntityMapper.documentToAuthor(document);
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

