package com.service.mongodb;

import org.bson.Document;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

public class PictureCollection {
    private UUID collectionId;
    private ZonedDateTime dateStarted;
    private ZonedDateTime dateEnded;
    private String collectionName;
    private Vector<Picture> pictures;

    public PictureCollection(ZonedDateTime dateStarted, ZonedDateTime dateEnded, String collectionName,
            Collection<Picture> pictures) {
        this.collectionId = UUID.randomUUID();
        this.dateStarted = dateStarted;
        this.dateEnded = dateEnded;
        this.collectionName = collectionName;
        this.pictures = new Vector<Picture>(pictures);
    }

    public String getCollectionId() {
        return collectionId.toString();
    }

    public void setDateStarted(ZonedDateTime dateStarted) {
        this.dateStarted = dateStarted;
    }

    public ZonedDateTime getDateStarted() {
        return this.dateStarted;
    }

    public void setDateEnded(ZonedDateTime dateEnded) {
        this.dateEnded = dateEnded;
    }

    public ZonedDateTime getDateEnded() {
        return this.dateEnded;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getCollectionName() {
        return this.collectionName;
    }

    public void setPictures(Collection<Picture> pictures) {
        this.pictures = new Vector<Picture>(pictures);
    }

    public Vector<Picture> getPictures() {
        return this.pictures;
    }

    public Document toDocument() {
        Document document = new Document("_id", this.collectionId.toString());
        List<Document> pictures = new ArrayList<>();

        for (Picture picture : this.pictures) {
            pictures.add(new Document("path", picture.getPath()).append("thumbIds", picture.getThumbIdStrs()));
        }

        document.append("collectionId", this.collectionId.toString())
                .append("dateStarted", this.dateStarted.toString())
                .append("dateEnded", this.dateEnded.toString())
                .append("collectionName", this.collectionName)
                .append("pictures", pictures);

        return document;
    }
}
