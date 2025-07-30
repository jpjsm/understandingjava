package com.service.mongodb;

import org.bson.Document;
import org.slf4j.LoggerFactory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;

public class InitMongoDb {
    public static String PicturesDbName = "Pictures";
    public static String PicturesCollectionName = "PicturesCollection";

    public static void main(String[] args) {
        // Configuring MongoDb logging to ERROR level or higher
        ((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger("org.mongodb.driver").setLevel(Level.ERROR);

        String connectionString = "mongodb://jp-devpc:27017/";

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase picturesDB = mongoClient.getDatabase(PicturesDbName);
            MongoCollection<Document> picturesCollection = picturesDB.getCollection(PicturesCollectionName);
            picturesCollection.drop();
            picturesCollection.createIndex(Indexes.ascending("pictures.path", "collectionName"));
        }
    }
}
