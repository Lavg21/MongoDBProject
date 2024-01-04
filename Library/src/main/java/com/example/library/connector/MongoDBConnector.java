package com.example.library.connector;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.UuidRepresentation;

public class MongoDBConnector {

    private static final String CONNECTION_STRING = "mongodb+srv://laviniag336:admin@cluster0.98mq4er.mongodb.net/test?retryWrites=true&w=majority";
    private static final String DATABASE_NAME = "library";

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    static {
        String connectionString = "mongodb+srv://laviniag336:admin@cluster0.98mq4er.mongodb.net/?retryWrites=true&w=majority";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("library");
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    public static void closeConnection() {
        mongoClient.close();
    }
}
