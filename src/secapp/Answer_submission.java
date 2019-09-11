/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secapp;

import java.util.ArrayList;

/**
 *
 * @author Dieudo M
 */
public class Answer_submission {
        private String testTitle;
	private String studentID;
	private String answers;
	private String course_code;
        //private ArrayList<Question> AnswersList;
	//private int mark;
	
	public Answer_submission(String testTitle, String course_code, String studentID,String answers) {
		//this.qNum = qnum;
		//this.course_code = course_code;
		this.testTitle = testTitle;
		this.answers = answers;
		this.studentID = studentID;
	}
	
	public void setAnswers(String q) {
		this.answers = q;
	}
	
	public void setStudentID(String Sid) {
		this.studentID = Sid;
	}
	public void Title(String t) {
		this.testTitle = t;
	}
	
	public void courseCODE(String CC) {
		this.course_code = CC;
	}
	public String GetAnswers() {
		return answers;
	}
	
	public String GetTitle() {
		return testTitle;
	}
	
	public String GetCourseC() {
		return course_code;
	}
	
	public String GetStudentID() {
		return studentID;
	}
	public void addAnswers(ArrayList<Pending_Tests> ans) {
//    	this.AnswersList = ans;
    }
    //    public ArrayList<Pending_Tests> ShowAnswers() {
  //  	return AnswersList;
   // }
	
	public String toString() {
        return "Title: "+ this.testTitle + "Course Code: "+ this.course_code + "Student ID: "+ this.studentID + "Answer: " + this.answers ;
	}
    
    
}
