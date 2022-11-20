package pong;

import java.awt.event.*;
import javax.swing.*;

public class MainMenu {
    public static void main(String[] args) {
        JFrame f = new JFrame("ABIMR's Pong");

        JLabel l = new JLabel("ABIMR's Pong");

        int x_diff = 40;
        int y_diff = 180;

        l.setBounds(390-x_diff, 50+y_diff, 100, 30);

        f.add(l);

        JLabel scoreLabel = new JLabel("Score to win: ");
        scoreLabel.setBounds(350-x_diff, 75+y_diff, 100, 30);

        f.add(scoreLabel);


        JTextField tf = new JTextField("3");
        f.add(tf);
        tf.setBounds(350-x_diff,100+y_diff, 150,20);  

        tf.setHorizontalAlignment(SwingConstants.CENTER);



        JButton b = new JButton("Start Game");

        b.setBounds(355-x_diff, 200+y_diff, 140, 40);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = tf.getText();

                if (s == null || s.matches(".*[a-zA-Z]+.*") || s.equals("")) {
                    JOptionPane.showMessageDialog(f, "Please enter a number");
                } else {

                    int scoreToWin = Integer.parseInt(s);

                    if (scoreToWin < 1) {
                        JOptionPane.showMessageDialog(f, "Please enter a number greater than 0");
                    } else {

                        f.dispose();
                        Pong.main(new String[] {s});
                    }
                }
            }
        });
        f.add(b);
        f.setSize(800, 800);
        f.setLayout(null);
        f.setVisible(true);
    }
}