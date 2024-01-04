package com.example.library.repository;

import com.example.library.connector.MongoDBConnector;
import com.example.library.domain.Loan;
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
public class LoanRepository {

    private final MongoCollection<Document> loanCollection;

    public LoanRepository() {
        loanCollection = MongoDBConnector.getDatabase().getCollection("loan");
    }

    public void createLoan(Loan loan) {
        Document document = EntityToDocumentMapper.loanToDocument(loan);
        loanCollection.insertOne(document);
    }

    public Loan getLoanById(String loanId) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", new ObjectId(loanId));
        for (Document document : loanCollection.find(searchQuery)) {
            return DocumentToEntityMapper.documentToLoan(document);
        }

        return null;
    }

    public void updateLoan(String loanId, Loan updatedLoan) {
        loanCollection.updateOne(Filters.eq("_id", new ObjectId(loanId)), Updates.set("bookId", updatedLoan.getBookId()));
        loanCollection.updateOne(Filters.eq("_id", new ObjectId(loanId)), Updates.set("readerId", updatedLoan.getReaderId()));
        loanCollection.updateOne(Filters.eq("_id", new ObjectId(loanId)), Updates.set("loanDate", updatedLoan.getLoanDate()));
        loanCollection.updateOne(Filters.eq("_id", new ObjectId(loanId)), Updates.set("dueDate", updatedLoan.getDueDate()));
        loanCollection.updateOne(Filters.eq("_id", new ObjectId(loanId)), Updates.set("returnDate", updatedLoan.getReturnDate()));
    }

    public void deleteLoan(String loanId) {
        loanCollection.deleteOne(Filters.eq("_id", new ObjectId(loanId)));
    }

    public List<Loan> getAllLoans() {
        FindIterable<Document> iterDoc = loanCollection.find();
        List<Loan> loans = new ArrayList<>();
        for (Document document : iterDoc) {
            Loan loan = DocumentToEntityMapper.documentToLoan(document);
            loans.add(loan);
        }

        return loans;
    }

    public boolean isLoanExists(String loanId) {
        return loanCollection.countDocuments(Filters.eq("_id", new ObjectId(loanId))) > 0;
    }
}
