package com.Hackthon.coderclan.Repository;

import com.Hackthon.coderclan.DTO.EmailDetailsDTO;
import com.Hackthon.coderclan.Entity.EmailDetails;
import com.mongodb.client.*;
import org.bson.Document;
import com.google.gson.Gson;
import org.bson.conversions.Bson;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;

@Repository
@ApplicationScope
public class EmailRepository  {

    public EmailDetails getEmailDataById(String emailId) {
        Gson gson = new Gson();
       EmailDetails emailDetails=new EmailDetails();

        String uri = "mongodb://localhost:27017";
        EmailDetails emailDetailsModel;
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("coderclans");
        MongoCollection<Document> collection = database.getCollection("EmailDetails");
        Bson equalComparison = eq("wareHouse_email_id", emailId);
        MongoCursor<Document> cursor = collection.find(equalComparison).iterator();
        emailDetailsModel= gson.fromJson(cursor.next().toJson(), EmailDetails.class);

        return emailDetailsModel;
    }
    public void inserEmailDetails(EmailDetails details) {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("coderclans");
        MongoCollection<Document> collection = database.getCollection("EmailDetails");
        var document=new Document();
        document.append("email_id", UUID.randomUUID().toString());
        document.append("wareHouse_email_id",details.getWareHouse_email_id());
        document.append("mail_sent_at",DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format( LocalDateTime.now()));
        document.append("wareHouse_Contact_no",details.getWareHouse_Contact_no());
        document.append("source_address",details.getSource_address());
        document.append("delivery_location",details.getDelivery_location());
        document.append("truckLocationReachedTo",details.getTruckLocationReachedTo());
        document.append("loadingStuatus",details.getLoadingStuatus());
        collection.insertOne(document);
    }


    public EmailDetailsDTO getAllEmailData() {
        Gson gson = new Gson();
        EmailDetailsDTO getEmailDTO = new EmailDetailsDTO();
        ArrayList<EmailDetails> AllEmails = new ArrayList<>();
        String uri = "mongodb://localhost:27017";
        EmailDetails emailDetails;
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("coderclans");
        MongoCollection<Document> collection = database.getCollection("EmailDetails");
        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()) {
            emailDetails = gson.fromJson(cursor.next().toJson(), EmailDetails.class);
            AllEmails.add(emailDetails);
        }
        getEmailDTO.setEmailDetailsDTO(AllEmails);
        return getEmailDTO ;
    }
}
