/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secapp;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Dieudo M
 */
public class MongoAtlasTest {
    public static void main(String[] args) {
		MongoClientURI uri = new MongoClientURI(
				"mongodb://Gareth:kebrUEqN7QHatMQW@secapp-clus-shard-00-00-elwim.mongodb.net:27017,secapp-clus-shard-00-01-elwim.mongodb.net:27017,secapp-clus-shard-00-02-elwim.mongodb.net:27017/test?ssl=true&replicaSet=Secapp-clus-shard-0&authSource=admin&retryWrites=true&w=majority");

		MongoClient mongoClient = new MongoClient(uri);
		DB database = mongoClient.getDB("SECAPP");
		DBCollection examCol = database.getCollection("testq");

		DBObject query = new BasicDBObject("Title","test4").append("course_code","MAM1000W");
		DBCursor cursor = examCol.find(query);
		DBObject test1 = cursor.one();

		//System.out.println(test1);
	}

    
}
