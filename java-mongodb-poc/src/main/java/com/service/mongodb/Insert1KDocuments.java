package com.service.mongodb;

import java.nio.charset.StandardCharsets;
import java.nio.ByteBuffer;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import org.bson.Document;
import org.json.simple.JSONValue;
import org.slf4j.LoggerFactory;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;

public class Insert1KDocuments {
        public static String PicturesDbName = "Pictures";
        public static String PicturesCollectionName = "PicturesCollection1K";

        public static void main(String[] args) {
                ((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger("org.mongodb.driver")
                                .setLevel(Level.ERROR);

                Map<String, Integer> registeredPaths = new HashMap<>();
                Random rnd = new Random();

                String connectionString = "mongodb://jp-devpc:27017";

                try (MongoClient mongoClient = MongoClients.create(connectionString)) {
                        // taping into the 'picturesCollection' collection
                        MongoDatabase picturesDB = mongoClient.getDatabase(PicturesDbName);
                        MongoCollection<Document> picturesCollection = picturesDB.getCollection(PicturesCollectionName);

                        // Remove next line to keep adding documents
                        picturesCollection.drop();

                        // add index to speed queries
                        picturesCollection.createIndex(Indexes.ascending("pictures.path", "collectionName"));

                        System.out.println("Connected to:" + picturesDB.getName());
                        System.out.println("Acquired collection '" + PicturesCollectionName
                                        + "'' with this many documents:"
                                        + picturesCollection.countDocuments());

                        // generating random data into 'picturesCollection'
                        Resources resources = new Resources();
                        for (int index = 0; index < 1000; index++) {
                                Vector<Picture> pictures = new Vector<Picture>();
                                String[] resource = resources.getSomeResource();
                                pictures.add(new Picture(
                                                "/rest/services/" + resource[0] + "/0.1.0/" + resource[1] + "s",
                                                resources.getUuids()));
                                pictures.add(new Picture(
                                                "/rest/services/" + resource[0] + "/0.1.0/" + resource[1] + "s/{id}",
                                                resources.getUuids()));
                                pictures.add(new Picture("/rest/services/" + resource[0] + "/0.1.0/" + resource[1]
                                                + "s/{id}/resize?&len={len}&width={width}", resources.getUuids()));
                                pictures.add(new Picture(
                                                "/rest/services/" + resource[0] + "/0.1.0/" + resource[1]
                                                                + "s/{id}/bw?&method={method}",
                                                resources.getUuids()));
                                pictures.add(new Picture(
                                                "/rest/services/" + resource[0] + "/0.1.0/" + resource[1]
                                                                + "s/{id}/compression?&ratio={ratio}",
                                                resources.getUuids()));

                                ZonedDateTime dateStarted = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"))
                                                .minusDays(rnd.nextInt(365 * 2) + 3);
                                ZonedDateTime dateEnded = dateStarted.plusHours(rnd.nextInt(4));

                                PictureCollection collection = new PictureCollection(dateStarted, dateEnded,
                                                dateStarted.toString(),
                                                pictures);

                                String pathToRegister = "/rest/services/" + resource[0] + "/0.1.0/" + resource[1] + "s";

                                if (!registeredPaths.containsKey(pathToRegister)) {
                                        registeredPaths.put(pathToRegister, 0);
                                }

                                registeredPaths.replace(pathToRegister, registeredPaths.get(pathToRegister) + 1);

                                Document query = new Document();
                                query.put("pictures.path", new Document("$regex", pathToRegister));
                                // System.out.println(query);
                                FindIterable<Document> collisions = picturesCollection.find(query);

                                MongoCursor<Document> cursor = collisions.iterator();
                                try {
                                        while (cursor.hasNext()) {
                                                System.out.println("Warning: Duplicated PATH '" + pathToRegister
                                                                + "' found at documentId: "
                                                                + cursor.next().get("_id"));
                                        }
                                } finally {
                                        cursor.close();
                                }

                                picturesCollection.insertOne(collection.toDocument());
                                System.out.printf("%,8d\r", index);
                                // collection.getCollectionId());

                        }

                        mongoClient.close();

                        System.out.println();
                        System.out.println(
                                        "=================================================================================");
                        String registeredPathsJsonStr = JSONValue.toJSONString(registeredPaths); // converts Map to JSON
                        try (FileWriter fw = new FileWriter("./RegisteredPathsCount.json", StandardCharsets.UTF_8)) {
                                fw.write(registeredPathsJsonStr);
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }
}
