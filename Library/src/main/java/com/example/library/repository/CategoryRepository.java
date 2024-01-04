package com.example.library.repository;

import com.example.library.connector.MongoDBConnector;
import com.example.library.domain.Category;
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
public class CategoryRepository {
    private final MongoCollection<Document> categoryCollection;

    public CategoryRepository() {
        categoryCollection = MongoDBConnector.getDatabase().getCollection("category");
    }

    public void createCategory(Category category) {
        Document document = EntityToDocumentMapper.categoryToDocument(category);
        categoryCollection.insertOne(document);
    }

    public Category getCategoryById(String categoryId) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", new ObjectId(categoryId));
        for (Document document : categoryCollection.find(searchQuery)) {
            return DocumentToEntityMapper.documentToCategory(document);
        }

        return null;
    }

    public void updateCategory(String categoryId, Category updatedCategory) {
        categoryCollection.updateOne(Filters.eq("_id", new ObjectId(categoryId)), Updates.set("name", updatedCategory.getName()));
        categoryCollection.updateOne(Filters.eq("_id", new ObjectId(categoryId)), Updates.set("description", updatedCategory.getDescription()));
        categoryCollection.updateOne(Filters.eq("_id", new ObjectId(categoryId)), Updates.set("numberOfBooks", updatedCategory.getNumberOfBooks()));
        categoryCollection.updateOne(Filters.eq("_id", new ObjectId(categoryId)), Updates.set("isActive", updatedCategory.isActive()));
        categoryCollection.updateOne(Filters.eq("_id", new ObjectId(categoryId)), Updates.set("createdBy", updatedCategory.getCreatedBy()));
    }

    public void deleteCategory(String categoryId) {
        categoryCollection.deleteOne(Filters.eq("_id", new ObjectId(categoryId)));
    }

    public List<Category> getAllCategories() {
        FindIterable<Document> iterDoc = categoryCollection.find();
        List<Category> categorys = new ArrayList<>();
        for (Document document : iterDoc) {
            Category category = DocumentToEntityMapper.documentToCategory(document);
            categorys.add(category);
        }

        return categorys;
    }

    public boolean isNameUnique(String name) {
        return categoryCollection.countDocuments(Filters.eq("name", name)) == 0;
    }

    public boolean isCategoryExists(String categoryId) {
        return categoryCollection.countDocuments(Filters.eq("_id", new ObjectId(categoryId))) > 0;
    }
}
