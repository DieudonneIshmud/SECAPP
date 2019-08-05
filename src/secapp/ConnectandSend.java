/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secapp;

/**
 *
 * @author Dieudo M
 */

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.util.ArrayList;
//import com.mongodb.client.MongoDatabase;
import java.util.Scanner;

public class ConnectandSend {
	//private static String hostname = "localhost";
	//private static final int portNumber = 27017;
	private static String uri = "mongodb://localhost:27017";
	
	private static ArrayList<Question> questions = new ArrayList<Question>();
	private static Exam ex;
	private static ArrayList<BasicDBObject> qtsList = new ArrayList<BasicDBObject>();
	private static DBCollection Examcollection;
	private static DBCollection testaCollection;
	private static DBCollection marksCol;
	
	
	public static void main(String args[]) {
		System.out.println("Connecting to DB");
		MongoClient mc = new MongoClient(new MongoClientURI(uri));
		System.out.println("Connection successful");
		
		System.out.println("Accessing Lecturer database");
		@SuppressWarnings("deprecation")
		DB database = mc.getDB("Lecturer");
		System.out.println("Lectuerer DB accessed successfully");
		
		System.out.println("Fetching database information");
		Examcollection = database.getCollection("exam");
		testaCollection = database.getCollection("testa");
		marksCol = database.getCollection("marks"); 
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			System.out.println("____Home____");
			System.out.println("1.) Create Assessment\n2.) My Assessments");
			int opt = scan.nextInt();

			if (opt == 1) {
				// Interface
				System.out.println("___Assessment Creation___\nTitle:");
				String title = scan.next();
				System.out.println("Course Code:");
				String cc = scan.next();
				System.out.println("Time:");
				String time = scan.next();
				System.out.println("Date:");
				String date = scan.next();
				System.out.println("Duration:");
				String duration = scan.next();
				System.out.println("Exam type (open or closed):");
				String ob = scan.next();
				System.out.println("Test Instructions:");
				scan.nextLine();
				String instrucs = scan.nextLine();

				//Create Exam object
				ex = new Exam(title,cc,time,date,duration,ob,instrucs);
				
				// Question Creation part
				while(true) {
					scan.nextLine();
					System.out.println("___Question Creation___\nEnter question number:");
					//System.out.println("Enter question number:");
					String qNum = scan.nextLine();
					System.out.println("Enter question type:");
					String qtype = scan.nextLine();
					System.out.println("Enter question:");
					//scan.next();
					String quest = scan.nextLine();
					System.out.println("Enter answer:");
					String ans = scan.nextLine();
					System.out.println("Enter maximum mark:");
					int mark = scan.nextInt();

					//Inserting information into a question object
					Question questioon = new Question(qNum,qtype,quest,ans,mark);
					BasicDBObject qts = new BasicDBObject("QNo", qNum).append("QType", qtype).append("Qtext",quest).append("answer",ans).append("mark", mark);
					
					qtsList.add(qts);
					
					questions.add(questioon);

					System.out.println("Add another question?(y/n)");
					String another = scan.next();

					if(another.equals("n")) {
						ex.addQuestions(questions);
						DBObject exam = new BasicDBObject("Title",ex.getTitle()).append("course_code",ex.getcourseCode()).append("Time",ex.getTime()).append("Date", ex.getDate()).append("Duration",ex.getDuration()).append("Closed/Open",ex.getExamType()).append("instructions", ex.getInstrucs()).append("Questions",qtsList.toArray());
						
						
						System.out.println("Inserting exam into database");
						Examcollection.insert(exam);
						System.out.println("Exam successfully uploaded");
						
						
						break;
					}
					else {
						continue;
					}
				}
			}
			
			if(opt==2) {
				// Print out tests currently on the database
				DBObject query = new BasicDBObject("Time","14:00");	// needs to be changed to query a lecturer ID
				DBCursor cursor = Examcollection.find(query);
				
				// Auxillary variables for querying
				int i = 1;
				ArrayList<String> examTits = new ArrayList<String>();
				ArrayList<String> ccs = new ArrayList<String>();
				
				// prints out titles of exams that are written by a certain lecturer using the lecID as query
				for(DBObject result : cursor) {
					String ExamTit = (String)result.get("Title");
					String cc = (String)result.get("course_code");
					examTits.add(ExamTit);
					ccs.add(cc);
					System.out.println(i+". "+ExamTit+ "("+cc+")");
					i++;
				}
				
				// lec selects a test
				int testOpt = scan.nextInt();
				String testPicked = examTits.get(testOpt-1);
				String ccPicked = ccs.get(testOpt-1);
				
					
				// displays all the student numbers that have written that test (And if it's marked or not)
				// Must add "marked" field to testa json file that shows if the test is marked or not.
				// Setup for querying testa collection
				DBObject compTestQuery = new BasicDBObject("Title",testPicked).append("coursecode", ccPicked);
				
				// Searching testa collection
				DBCursor cursor2 = testaCollection.find(compTestQuery);
				
				// Auxillary variables for querying
				int o = 1;
				ArrayList<String> studentNums = new ArrayList<String>();
				
				// iterating through testa and printing out the student numbers who have written the particular test
				for(DBObject result2 : cursor2) {
					String studentNum = (String)result2.get("Student_ID");
					studentNums.add(studentNum);
					System.out.println(o + ". " + studentNum);
					o++;
				}
				
				//lec selects a student number to mark				
				int studentNumOpt = scan.nextInt();
				String selectedStu = studentNums.get(studentNumOpt - 1);
				
				// question and students answers gets displayed and lec is able to give a mark for each answer.
				DBObject stuAns = new BasicDBObject("Student_ID",selectedStu);
				
				//fetching student answers
				System.out.println(testaCollection.distinct("Answer details.answer",stuAns));
				// Allow access to test information
				
				// aux variables
				ArrayList<BasicDBObject> mrkslist = new ArrayList<BasicDBObject>();
				
				// -- create maxMark field in answer fields for each question
				for(int x=0;x<testaCollection.distinct("Answer details.answer",stuAns).size();x++) {
					System.out.println(testaCollection.distinct("Answer details.Qno",stuAns).get(x) + ".)" + testaCollection.distinct("Answer details.answer",stuAns).get(x));
					System.out.println("Enter mark:");
					int mark = scan.nextInt();
					scan.nextLine();
					System.out.println("Feedback: ");
					
					String feedback = scan.nextLine();
					
					// Create Json file to send to database
					BasicDBObject mrked = new BasicDBObject("Qno",testaCollection.distinct("Answer details.Qno",stuAns).get(x)).append("mark", mark).append("feedback", feedback);
					mrkslist.add(mrked);					
					
				}
				//construct json file to send to database
				System.out.println(mrkslist);
								
				
			}
			
			else {mc.close();}//scan.close();}
		}
	}
}

