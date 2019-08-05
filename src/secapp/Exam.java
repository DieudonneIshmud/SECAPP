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
import java.util.ArrayList;

public class Exam{
    
    private String title;
    private String courseCode;
    private String time;
    private String date;
    private String duration;
    private String examType;
    private String instrucs;
    private ArrayList<Question> questions;

    public Exam(String t, String c, String time, String date, String duration, String examtype, String instructions){//, Question[] questions){
        this.title = t;
        this.courseCode = c;
        this.time = time;
        this.date = date;
        this.duration = duration;
        this.examType = examtype;
        this.instrucs = instructions;

    }
    

    public void setTitle(String t){
        this.title = t;
    }

    public void setcourseCode(String course){
        this.courseCode = course;
    }

    public void setTime(String Time){       // use Time class for time
        this.time = Time;
    }

    public void setDuration(String dur){    // Use Time class
        this.duration = dur;
    }

    public void setClosedBook(String b){
        this.examType = b;
    }

    public String getTitle(){
        return this.title;
    }
    
    public String getDate() {
    	return date;
    }

    public String getcourseCode(){
        return this.courseCode;
    }

    public String getTime(){       // use Time class for time
        return this.time;
    }

    public String getDuration(){    // Use Time class
        return this.duration;
    }

    public String getExamType(){
        return this.examType;
    }
    
    public String getInstrucs() {
    	return this.instrucs;
    }
    
    public String toString() {
    	return title +" "+ courseCode +" "+ time +" "+ duration +" " + examType;
    }
    
    public void addQuestion(Question q) {
    	this.questions.add(q);
    }
    
    public void addQuestions(ArrayList<Question> qs) {
    	this.questions = qs;
    }
    
    public ArrayList<Question> ShowQuestions() {
    	return questions;
    }
}
