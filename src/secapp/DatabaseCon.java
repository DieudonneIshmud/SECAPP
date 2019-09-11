/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secapp;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.util.ArrayList;

/**
 *
 * @author garet
 */
public class DatabaseCon {
    private static String uri = "mongodb://Gareth:kebrUEqN7QHatMQW@secapp-clus-shard-00-00-elwim.mongodb.net:27017,secapp-clus-shard-00-01-elwim.mongodb.net:27017,secapp-clus-shard-00-02-elwim.mongodb.net:27017/test?ssl=true&replicaSet=Secapp-clus-shard-0&authSource=admin&retryWrites=true&w=majority";
    private DBCollection Examcollection; //testa
    private DBCollection testaCollection;
    private DBCollection marksCol;
    
    DB database;
    
    public DB getDB()
    {
    	LoadDBs();
    	return database;
    }
    
    /*public DatabaseCon(DBCollection exam, DBCollection testa, DBCollection marks){
        Examcollection = exam;
        testaCollection = testa;
        marksCol = marks;
        
    }*/
    
    //private ArrayList<DBCollection> collectionList = new ArrayList<DBCollection>();
    // Method to connect to the remote mongoDB and loads the databases to local variables
    public void LoadDBs(){
        //ArrayList<DBCollection> collectionList = new ArrayList<DBCollection>();
        System.out.println("Connecting to DB");
	MongoClient mc = new MongoClient(new MongoClientURI(uri));
	System.out.println("Connection successful");
		
	System.out.println("Accessing Lecturer database");
	//@SuppressWarnings("deprecation")
        database = mc.getDB("SECAPP");
	System.out.println("SECAPP DB accessed successfully");
	
        // initialise collection variables
	System.out.println("Fetching database information");
	Examcollection = database.getCollection("testq");
	testaCollection = database.getCollection("testa");
	marksCol = database.getCollection("marks");
        
    }
    
    public DBCollection getExam(){
        return Examcollection;
    }
    
    public DBCollection getTesta(){
        return testaCollection;
    }
    
    public DBCollection getMarks(){
        return marksCol;
    }
    
    /*// Connects to the mongo server and returns the database requested
    public DB connect(String uriAd, String database){
        MongoClient client = new MongoClient(new MongoClientURI(uriAd));
        @SuppressWarnings("deprecation")
        DB mongoDatabase = client.getDB(database);
        return mongoDatabase;
    }*/
    
    // Sends exam details placed in gui to the mongo database
    //public void insertToDB(Exam exam, ArrayList<BasicDBObject> qtsList, DBCollection examCol){
      //  DBObject ExamToStudent = new BasicDBObject("Title",exam.getTitle()).append("course_code",exam.getcourseCode()).append("Time",exam.getTime()).append("Date", exam.getDate()).append("Duration",exam.getDuration()).append("Closed/Open",exam.getExamType()).append("instructions", exam.getInstrucs()).append("Questions",qtsList.toArray());
        //examCol.insert(ExamToStudent);
    //}
    
    public static void main(String args[]){
        DatabaseCon dbc = new DatabaseCon();
        //System.out.println(dbc.LoadDBs());
        //ArrayList<DBCollection> collectionls;
        //collectionls = dbc.LoadDBs();
        /*dbc.LoadDBs();
        Question q1 = new Question("1","short","This is the question","answer1.answer2.answer3.",5);
        Question q2 = new Question("2","short","This is the second question","answer1.answer2.answer3.",6);
        BasicDBObject Q1 = new BasicDBObject("QNo", q1.getQnum()).append("QType", q1.getQtype()).append("Qtext",q1.getQuestion()).append("answer",q1.getAns()).append("mark", q1.getMark());
        BasicDBObject Q2 = new BasicDBObject("QNo", q2.getQnum()).append("QType", q2.getQtype()).append("Qtext",q2.getQuestion()).append("answer",q2.getAns()).append("mark", q2.getMark());
        
        ArrayList<BasicDBObject> qsList = new ArrayList<BasicDBObject>();
        qsList.add(Q1);
        qsList.add(Q2);
        
        Exam ex = new Exam("test92","INF2009F","14:00","04/03/2019",45,"open","these are some instructions");
        
        
        dbc.insertToDB(ex, qsList,dbc.getExam());*/

    }
}
