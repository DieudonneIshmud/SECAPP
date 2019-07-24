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
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;


public class MongoJTable {
    int i=0;


    public static void main(String[] args) {
        final JFrame frame = new JFrame("JTable Demo");
        int y=0;
        String [] columns1 = null;
        List<String> animals = new ArrayList<>();

        Mongo mongo = new Mongo("localhost",27017);
        DB db = mongo.getDB("SECAPP");
        DBCollection DBCollection = db.getCollection("testq");


        DBCursor cursor = DBCollection.find();
        /*String[] temps = new String [cursor.size()+1];
        for (String key: cursor.next().keySet()) {
            // do whatever with the key name here, f.ex.
            System.out.println(key);
           animals.add(key.toString());

        }*/
        String[] nameList = new String[cursor.size()];

        DBObject obj = cursor.next(); 
        Set<String> keys = obj.keySet(); 
        Iterator iterator = keys.iterator();

        /*while(iterator.hasNext()){

              String key = (String) iterator.next().toString();
              nameList[y] =key;
              y++;
              /* Do whatever you want 
            }*/
        for(int i = 0; i <cursor.size(); i++){
            nameList[i] = cursor.next().keySet().toString();
            System.out.println(nameList[i]);
            break;
        }

        //String[] columns = {"Code", "Name", "High", "Low", "Close", "Volume", "Change","Change %"};
        //System.out.println(Arrays.deepToString(columns));

        DefaultTableModel model = new DefaultTableModel(nameList, 0);
        Object[][] data = {
//                {"MBF", "CITYGROUP", 10.16, 10.16, 10.16, 200, 0.08,0.79},
                //{"MBL", "BANK OF AMERICA", 12.66, 12.66, 12.66, 6600, 0.13,1.04},
                //{"MJP", "Morgan Stanley Dean Witter & Co.", 24.97, 24.97, 24.97, 1000, -0.04,-0.16}
            };


        JTable table = new JTable(data,nameList);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        JLabel lblHeading = new JLabel("Stock Quotes");
        lblHeading.setFont(new Font("Arial",Font.TRUETYPE_FONT,24)); 
        frame.getContentPane().setLayout(new BorderLayout()); 
        frame.getContentPane().add(lblHeading,BorderLayout.PAGE_START);
        frame.getContentPane().add(scrollPane,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 200);
        frame.setVisible(true);
    }
}
