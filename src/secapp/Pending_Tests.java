/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secapp;

import static com.mongodb.client.model.Filters.text;
import static com.mongodb.client.model.Filters.text;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.Timer;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.runtime.options.Options;
import static org.bouncycastle.asn1.x500.style.RFC4519Style.title;
/**
 *
 * @author Dieudo M
 */
public class Pending_Tests extends javax.swing.JPanel {
    
    int Min, Sec;
    private Timer timer;
    private int[] time;
    final int MIN = 0;
    final int SEC = 1;
    static Pending_Tests pending;
    static all_answers answers;
    static Completed_Tests completed;
    ArrayList<String> arr;
    ArrayList<File> savedAnswers;
    String file_name = "test.txt";
  
//    private MongoClient mongoClient = null;
//    private FindIterable<Document> documents = null;
//    
//    private MongoDatabase db = null;
//    private Object[] QuestionsList;
    private Document testDocument = null;
    
    int current_question = 0;
    int anInstruction = 0;
    List<Document> questionsDocs;
    List<Document> instructionsDocs;
    /**
     * Creates new form Pending_Tests
     */
    public Pending_Tests() {
        initComponents();
      
        //finishButton.setVisible(false);
         
        answers =  new all_answers();
        GridBagLayout layout = new GridBagLayout();
        questionDisplay.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        //JOptionPane.showMessageDialog(null, "questioDisplay is+" + longANDshort_answer + " answer is " + answers);
        questionDisplay.add(answers, c);
        answers.setVisible(false);
       c.gridx =0;
       c.gridy=0;
    }
     public void displayTime(int min, int sec){
        String minute = String.format("%02d",min);
        String second = (String.format("%02d",sec));
        count.setText(minute + ":" + second);
    }
     public void startCountdown(String time, Document testDocument){
         System.out.println(testDocument.toString());
        this.testDocument = testDocument;
        questionsDocs = (List<Document>) testDocument.get("Questions");
        setUpFiles();
        this.time = convertTimeToInt(time.split(":"));
        this.message.setText(testDocument.getString("Title"));
        count.setText(time);
        timer = new Timer(1000,new TimerListener());
        timer.setRepeats(true);
        timer.start();
        displayQuestions();
    }
     
     public void setUpFiles(){
        savedAnswers = new ArrayList<>(questionsDocs.size());
        for(int i =0; i< questionsDocs.size(); ++i){
            ///look into appdata for windows
            File temp =  new File("./.question"+i);
            try {
                temp.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Pending_Tests.class.getName()).log(Level.SEVERE, null, ex);
            }
            savedAnswers.add(temp);
        }
     }
      public void Title(String t) {
       String Title = testDocument.getString("Title");
		Title = t;
	}
	
	public void courseCODE(String CC) {
        String CourseCode = testDocument.getString("course_code");
		CourseCode = CC;
	}
     public int[] convertTimeToInt(String[] time){
        int[] converted = new int[time.length];
        for(int i = 0; i<time.length; i++){
            converted[i] = Integer.valueOf(time[i]);
        }
        return converted;
    }
     
    public String getprevAnswer(int index){
         StringBuilder answer = new StringBuilder("");
         try{
             BufferedReader br = new BufferedReader(new FileReader(savedAnswers.get(index)));
             String curr = br.readLine();
             while(curr!=null&&!curr.isEmpty()){
                 answer.append(curr);
                 curr = br.readLine();
             }
             
         } catch (FileNotFoundException ex ) {
            Logger.getLogger(Pending_Tests.class.getName()).log(Level.SEVERE, null, ex);
         }catch(IOException ex){}
         return answer.toString();
   }
    
    /**
     * This method return the string on the selected JOption Pane
     * @param questionNumber the question that we want to retrieve the answer for
     * @param selectedOption the option that was selected.
     * @return returns the string in the selected option if the questions is mcq otherwise returns and empty string
     */
    public String mapOption(int questionNumber, int selectedOption){
        Document document = questionsDocs.get(questionNumber);
        if(document.getString("QType").equalsIgnoreCase("mcq")){
            String answer_options = document.getString("options");
            return answer_options.split("\\.")[selectedOption];
        }
        return "";
    }
     private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(time[MIN] == 0 && (time[SEC] == 1 || time[SEC] == 0)){
                count.setText("");
                message.setText("END");
                timer.stop();
            }else if(time[SEC] > 0){
                time[SEC] -= 1;
                displayTime(time[MIN], time[SEC]);
            }else if(time[SEC] == 0){
                time[SEC] = 59;
                time[MIN] -= 1;
                displayTime(time[MIN], time[SEC]);
            }

            count.setForeground(time[MIN] == 0 && time[SEC] >= 6 ? Color.BLACK : Color.RED);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        questionDisplay = new javax.swing.JPanel();
        questions = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        message = new javax.swing.JLabel();
        finishButton = new javax.swing.JButton();
        questionNumber = new javax.swing.JLabel();
        answer_panel = new javax.swing.JPanel();
        MCQpanel = new javax.swing.JPanel();
        option1 = new javax.swing.JRadioButton();
        option2 = new javax.swing.JRadioButton();
        option3 = new javax.swing.JRadioButton();
        option4 = new javax.swing.JRadioButton();
        longANDshort_answer = new javax.swing.JScrollPane();
        answer_area = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        question_area = new javax.swing.JTextArea();
        count = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        questionDisplay.setBackground(new java.awt.Color(255, 255, 255));

        questions.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Answer");

        btnNext.setBackground(new java.awt.Color(54, 33, 89));
        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setText("Next>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPrevious.setBackground(new java.awt.Color(54, 33, 89));
        btnPrevious.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnPrevious.setForeground(new java.awt.Color(255, 255, 255));
        btnPrevious.setText("<Previous");
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        message.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        message.setText("Message");

        finishButton.setBackground(new java.awt.Color(54, 33, 89));
        finishButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        finishButton.setForeground(new java.awt.Color(255, 255, 255));
        finishButton.setText("Finish");
        finishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishButtonActionPerformed(evt);
            }
        });

        questionNumber.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        questionNumber.setText("Question 1/4");

        answer_panel.setLayout(new java.awt.CardLayout());

        MCQpanel.setBackground(new java.awt.Color(255, 255, 255));

        option1.setText("jRadioButton1");

        option2.setText("jRadioButton2");
        option2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                option2ActionPerformed(evt);
            }
        });

        option3.setText("jRadioButton3");

        option4.setText("jRadioButton4");

        javax.swing.GroupLayout MCQpanelLayout = new javax.swing.GroupLayout(MCQpanel);
        MCQpanel.setLayout(MCQpanelLayout);
        MCQpanelLayout.setHorizontalGroup(
            MCQpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MCQpanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(MCQpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(option4)
                    .addComponent(option2)
                    .addComponent(option1)
                    .addComponent(option3))
                .addGap(550, 550, 550))
        );
        MCQpanelLayout.setVerticalGroup(
            MCQpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MCQpanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(option1)
                .addGap(18, 18, 18)
                .addComponent(option2)
                .addGap(18, 18, 18)
                .addComponent(option3)
                .addGap(18, 18, 18)
                .addComponent(option4)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        answer_panel.add(MCQpanel, "MCQ");

        answer_area.setBackground(new java.awt.Color(255, 255, 255));
        answer_area.setColumns(20);
        answer_area.setRows(5);
        longANDshort_answer.setViewportView(answer_area);

        answer_panel.add(longANDshort_answer, "Long_answer");

        question_area.setColumns(20);
        question_area.setRows(5);
        jScrollPane2.setViewportView(question_area);

        count.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        count.setText("0:0");

        javax.swing.GroupLayout questionsLayout = new javax.swing.GroupLayout(questions);
        questions.setLayout(questionsLayout);
        questionsLayout.setHorizontalGroup(
            questionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questionsLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnPrevious)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(questionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(questionsLayout.createSequentialGroup()
                        .addComponent(answer_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(questionsLayout.createSequentialGroup()
                        .addGroup(questionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(questionsLayout.createSequentialGroup()
                                .addComponent(questionNumber)
                                .addGap(492, 492, 492)
                                .addComponent(count, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(message))
                            .addGroup(questionsLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(755, 755, 755))
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNext)
                        .addGap(86, 86, 86))))
            .addGroup(questionsLayout.createSequentialGroup()
                .addGap(276, 276, 276)
                .addComponent(finishButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        questionsLayout.setVerticalGroup(
            questionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, questionsLayout.createSequentialGroup()
                .addGroup(questionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(questionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(questionsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(questionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(message)
                            .addComponent(count, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(questionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(questionsLayout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addGroup(questionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnPrevious)
                            .addComponent(btnNext))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, questionsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(answer_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(finishButton)
                .addGap(338, 338, 338))
        );

        javax.swing.GroupLayout questionDisplayLayout = new javax.swing.GroupLayout(questionDisplay);
        questionDisplay.setLayout(questionDisplayLayout);
        questionDisplayLayout.setHorizontalGroup(
            questionDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questionDisplayLayout.createSequentialGroup()
                .addComponent(questions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        questionDisplayLayout.setVerticalGroup(
            questionDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questionDisplayLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(questions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(questionDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(questionDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
        static String question1,answer1;
    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        //btnNext.setVisible();
        try {
            // TODO add your handling code here:
            saveToFile(current_question);
        } catch (IOException ex) {
            Logger.getLogger(Pending_Tests.class.getName()).log(Level.SEVERE, null, ex);
        }
        nextQuesiton();
     
        questions.setVisible(true);
        //question_area.setText("");
        btnPrevious.setVisible(true);
        finishButton.setVisible(true);
        
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        try {
            // TODO add your handling code here:
            saveToFile(current_question);
        } catch (IOException ex) {
            Logger.getLogger(Pending_Tests.class.getName()).log(Level.SEVERE, null, ex);
        }
        displayPreviousQuestion();
    }//GEN-LAST:event_btnPreviousActionPerformed
 public static final String NEW_LINE = System.getProperty("line.separator");
    private void finishButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishButtonActionPerformed
        // TODO add your handling code here:
        
        questions.setVisible(false);
        answers.setVisible(true);
        
        //String answer2 = answer_area.getText();
        //arr.add(answer2);
        try {
            saveToFile(current_question);
            
            //  if (answer_area)
        } catch (IOException ex) {
            Logger.getLogger(Pending_Tests.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        JEditorPane ansList = answers.getAnswersList();
//        String all = null;
//        for(int i = 0; i < arr.size(); i++) {   
//            
//            all += arr.get(i)+"\n\r";                 
//        System.out.println(arr.get(i));
//} 
//        ansList.setText(all);
//boolean hasNewline = all.contains(NEW_LINE);
//            if (hasNewline){
//                
//            }
        answers.displayAll();

    }//GEN-LAST:event_finishButtonActionPerformed

    private void option2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_option2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_option2ActionPerformed

    public void nextQuesiton(){
        ++current_question;
        displayQuestions();
    }
    public void displayQuestions() {
       // question_area.requestFocusInWindow();
        Document nextQuestion = questionsDocs.get(current_question);
//        question_area.setText("");
        question_area.setText(nextQuestion.getString("Qtext"));
        questionTyp = nextQuestion.getString("QType");
        String answer_options = nextQuestion.getString("options");
        questionNumber.setText("Question "+(current_question+1)+"/"+questionsDocs.size());
        btnNext.setEnabled(current_question<questionsDocs.size()-1);
        btnPrevious.setEnabled(current_question>=1);
        if(!btnNext.isEnabled())
            finishButton.setVisible(true);
         CardLayout card = (CardLayout)answer_panel.getLayout();
        //System.out.println(nextQuestion);
        //System.out.println(answer_options);
        
        
        if(questionTyp.equalsIgnoreCase("long")|| questionTyp.equalsIgnoreCase("short")){
            //not multiplichoice so show text area
           card.show(answer_panel, "Long_answer");
           String answer = getprevAnswer(current_question);   
           answer_area.setText(answer);
        }else{
            //multiple choice so show JCOMbo
            card.show(answer_panel, "MCQ");
            setMCQChoices(answer_options);
            setMCQSavedOtions();
        }
    }
    
    public void setMCQSavedOtions(){
           String prevAns = getprevAnswer(current_question);
            if(!prevAns.isEmpty()){
                int option = Integer.parseInt(prevAns);
                switch(option){
                    case 1:
                        option1.setSelected(true);
                        break;
                    case 2:
                        option2.setSelected(true);
                        break;
                    case 3:
                        option3.setSelected(true);
                        break;
                    case 4:
                        option4.setSelected(true);
                }
        }
    }
   
    public void displayInstructions(){
        
    }
     String questionTyp;
    public void saveToFile(int index) throws IOException{
            FileWriter fw = new FileWriter(savedAnswers.get(current_question));
            if(questionTyp.equalsIgnoreCase("mcq")){
                if(option1.isSelected()){
                    JOptionPane.showMessageDialog(null, "Selected pane is "+1);
                    fw.write(option1.getText());
                }
                else if(option2.isSelected()){
                    JOptionPane.showMessageDialog(null, "Selected pane is "+2);
                    fw.write(option2.getText());
                }
                else if(option3.isSelected()){
                    JOptionPane.showMessageDialog(null, "Selected pane is "+3); 
                    fw.write(option3.getText());
                }
                else if(option4.isSelected()){
                    JOptionPane.showMessageDialog(null, "Selected option is option"+4);
                    fw.write(option4.getText());
                }
                else
                    fw.write("");
            }else{
                fw.write(answer_area.getText());
            }
            fw.flush();
            fw.close();
    }
    
    private void displayPreviousQuestion() {
        Document nextQuestion = questionsDocs.get(--current_question-1);
        question_area.setText(nextQuestion.getString("Qtext"));
        questionTyp = nextQuestion.getString("QType");
        String answer_options = nextQuestion.getString("options");
        questionNumber.setText("Question "+current_question+"/"+questionsDocs.size());
        btnPrevious.setEnabled(current_question>1);
        btnNext.setEnabled(current_question<questionsDocs.size());
        System.out.println(nextQuestion);
        
        CardLayout card = (CardLayout)answer_panel.getLayout();
        if(questionTyp.equalsIgnoreCase("long")|| questionTyp.equalsIgnoreCase("short")){
            //not multiplichoice so show text area
            JOptionPane.showMessageDialog(null, "Long");
           card.show(answer_panel, "Long_answer");
           String answer = getprevAnswer(current_question);   
           answer_area.setText(answer);
        }else{
           // JOptionPane.showMessageDialog(null, "MCQ");
            //multiple choice so show JCOMbo
            card.show(answer_panel, "MCQ");
            setMCQChoices(answer_options);
            setMCQSavedOtions();
           
        }
    }
    
    public void setMCQChoices(String answer_options){
        
         String [] options = answer_options.split("\\.");
            
            option1.setText(options[0]);
            option2.setText(options[1]);
            option3.setText(options[2]);
            option4.setText(options[3]);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MCQpanel;
    private javax.swing.JTextArea answer_area;
    private javax.swing.JPanel answer_panel;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JLabel count;
    private javax.swing.JButton finishButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane longANDshort_answer;
    private javax.swing.JLabel message;
    private javax.swing.JRadioButton option1;
    private javax.swing.JRadioButton option2;
    private javax.swing.JRadioButton option3;
    private javax.swing.JRadioButton option4;
    private javax.swing.JPanel questionDisplay;
    private javax.swing.JLabel questionNumber;
    private javax.swing.JTextArea question_area;
    private javax.swing.JPanel questions;
    // End of variables declaration//GEN-END:variables
}
