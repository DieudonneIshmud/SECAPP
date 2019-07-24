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
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.bson.types.ObjectId;

public class MongoStackoverflow {
    private static JTable table;

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            public void run() {
                table = new JTable(){
                    @Override
                    public Dimension getPreferredScrollableViewportSize() {
                        return new Dimension(500, 300);
                    }
                };
                JPanel panel = new JPanel(new BorderLayout());
                JButton button = new JButton("Show Data");
                button.addActionListener(listener);
                panel.add(new JScrollPane(table));
                panel.add(button, BorderLayout.PAGE_END);
                JOptionPane.showMessageDialog(null, panel);
            }
        };
        SwingUtilities.invokeLater(runnable);
    }

    static ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            MongoClient mongoClient = null;
            DBCursor cursor = null;
            
            try {
                
                mongoClient = new MongoClient( "localhost" , 27017 );
                DB db = mongoClient.getDB( "SECAPP" );
                DBCollection coll = db.getCollection("testq");
                cursor = coll.find();

                String[] columnNames = {"id", "Test description", "Course code", "Date", "Time","Open/Closed"};
                DefaultTableModel model = new DefaultTableModel(columnNames, 0);

                while(cursor.hasNext()) {
                    DBObject obj = cursor.next();
                    String testTitle = (String)obj.get("Title");
                    String courseCode = (String)obj.get("Course code");
                    String date  = (String)obj.get("Date");
                    String time = (String)obj.get("Time");
                    String openORclosed = (String)obj.get("Open/Closed");
                    ObjectId id = (ObjectId)obj.get("_id");
                    model.addRow(new Object[] { id, testTitle, courseCode, date, time, openORclosed });
                   // model.addRow(new Object[] { id, testTitle, courseCode, date, time, openORclosed });
                }
                table.setModel(model);

                cursor.close(); 
                mongoClient.close();
                throw new UnknownHostException("I am Exception Alpha!");
            }
          catch(UnknownHostException ex) {
                Logger.getLogger(MongoStackoverflow.class.getName()).log(Level.SEVERE, null, ex);
            } 
            finally {
                if (cursor!= null) {
                    cursor.close();
                }
                if (mongoClient != null) {
                     mongoClient.close();
                }   
            }
        }
    }; 
}
