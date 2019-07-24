/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secapp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Dieudo M
 */
public class Countdown {
      private JFrame frame;
    private Timer timer;
    private JLabel count;
    private JLabel message;
    private int[] time;
    final int MIN = 0;
    final int SEC = 1;

    public Countdown(){
        frame = new JFrame();
        frame.setUndecorated(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setAlwaysOnTop(true);
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setVisible(false);
                frame.dispose();
                frame.setUndecorated(!frame.isUndecorated());
                frame.revalidate();
                frame.setVisible(true);
            }
        });
        frame.add(addText());
        frame.setVisible(true);
    }
    public JPanel addText(){
        count = new JLabel();
        count.setFont(new Font("Arial Black", Font.BOLD, 200));
        count.setHorizontalAlignment(SwingConstants.CENTER);
        count.setBackground(Color.RED);
        message = new JLabel();
        message.setFont(new Font("Arial Black", Font.BOLD, 100));
        JPanel panel = new JPanel(new GridLayout(2,1));
        panel.add(count);
        panel.add(message);
        panel.setBackground(Color.WHITE);
        message.setHorizontalAlignment(SwingConstants.CENTER);
        return panel;
    }

    public void displayTime(int min, int sec){
        String minute = String.format("%02d",min);
        String second = (String.format("%02d",sec));
        count.setText(minute + ":" + second);
    }

    public void startCountdown(String time, String message){
        this.time = convertTimeToInt(time.split(":"));
        this.message.setText(message);
        count.setText(time);
        timer = new Timer(1000,new TimerListener());
        timer.setRepeats(true);
        timer.start();

    }
    public int[] convertTimeToInt(String[] time){
        int[] converted = new int[time.length];
        for(int i = 0; i<time.length; i++){
            converted[i] = Integer.valueOf(time[i]);
        }
        return converted;
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
    public static void main(String[] args){
        Countdown countdown = new Countdown();
        countdown.startCountdown("00:12","RUN");
    }
    
}
