/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secapp;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author Dieudo M
 */
public class MongoUtil {

    static MongoClient mongoClient = null;

    static MongoDatabase db = null;

    static final void connect() {
        String uri = "mongodb://Gareth:kebrUEqN7QHatMQW@secapp-clus-shard-00-00-elwim.mongodb.net:27017,secapp-clus-shard-00-01-elwim.mongodb.net:27017,secapp-clus-shard-00-02-elwim.mongodb.net:27017/test?ssl=true&replicaSet=Secapp-clus-shard-0&authSource=admin&retryWrites=true&w=majority";

        mongoClient = new MongoClient(new MongoClientURI(uri));
//        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDatabase("SECAPP");
    }

    static MongoCollection getCollection(String collectionName) {
        return db.getCollection(collectionName);
    }

    final void disconnect() {
        mongoClient.close();
    }
}
