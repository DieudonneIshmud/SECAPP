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
public class Question {
        	private String qNum;
	private String qtype;
	private String question;
	private String ans;
	private int mark;
	
	public Question(String qnum, String qtype, String question, String ans, int mark) {
		this.qNum = qnum;
		this.qtype = qtype;
		this.question = question;
		this.ans = ans;
		this.mark = mark;
	}
	
	public void setQtype(String q) {
		this.qtype = q;
	}
	
	public void setQuestion(String qs) {
		this.question = qs;
	}
	
	public String getQnum() {
		return qNum;
	}
	
	public String getAns() {
		return ans;
	}
	
	public int getMark() {
		return mark;
	}
	
	public String getQtype() {
		return qtype;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String toString() {
		return "Question number: "+ this.qNum + "Type: "+ this.qtype + "Question: "+ this.question + "Answer: " + this.ans + "Mark: " + this.mark;
	}
    
}
