package com.example.library.repository;

import com.example.library.connector.MongoDBConnector;
import com.example.library.domain.entity.Reader;
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
public class ReaderRepository {

    private final MongoCollection<Document> readerCollection;

    public ReaderRepository() {
        readerCollection = MongoDBConnector.getDatabase().getCollection("reader");
    }

    public void createReader(Reader reader) {
        Document document = EntityToDocumentMapper.readerToDocument(reader);
        readerCollection.insertOne(document);
    }

    public Reader getReaderById(String readerId) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", new ObjectId(readerId));
        for (Document document : readerCollection.find(searchQuery)) {
            return DocumentToEntityMapper.documentToReader(document);
        }

        return null;
    }

    public void updateReader(String readerId, Reader updatedReader) {
        readerCollection.updateOne(Filters.eq("_id", new ObjectId(readerId)), Updates.set("firstname", updatedReader.getFirstname()));
        readerCollection.updateOne(Filters.eq("_id", new ObjectId(readerId)), Updates.set("lastname", updatedReader.getLastname()));
        readerCollection.updateOne(Filters.eq("_id", new ObjectId(readerId)), Updates.set("email", updatedReader.getEmail()));
        readerCollection.updateOne(Filters.eq("_id", new ObjectId(readerId)), Updates.set("phone", updatedReader.getPhone()));
    }

    public void deleteReader(String readerId) {
        readerCollection.deleteOne(Filters.eq("_id", new ObjectId(readerId)));
    }

    public List<Reader> getAllReaders() {
        FindIterable<Document> iterDoc = readerCollection.find();
        List<Reader> readers = new ArrayList<>();
        for (Document document : iterDoc) {
            Reader reader = DocumentToEntityMapper.documentToReader(document);
            readers.add(reader);
        }

        return readers;
    }

    public boolean isReaderExists(String readerId) {
        return readerCollection.countDocuments(Filters.eq("_id", new ObjectId(readerId))) > 0;
    }

    public boolean isEmailUnique(String email) {
        return readerCollection.countDocuments(Filters.eq("email", email)) == 0;
    }
}
