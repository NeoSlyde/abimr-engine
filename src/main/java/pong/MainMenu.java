package pong;

import javax.swing.*;

import pong.Score.Side;

public class MainMenu {
    public static void main(String[] args) {
        JFrame f = new JFrame("ABIMR's Pong");

        JLabel l = new JLabel("ABIMR's Pong");

        int x_diff = 40;
        int y_diff = 180;

        l.setBounds(390 - x_diff, 50 + y_diff, 100, 30);

        f.add(l);

        JLabel scoreLabel = new JLabel("Score to win: ");
        scoreLabel.setBounds(350 - x_diff, 75 + y_diff, 100, 30);

        f.add(scoreLabel);

        JTextField tf = new JTextField("1");
        f.add(tf);
        tf.setBounds(350 - x_diff, 100 + y_diff, 150, 20);

        tf.setHorizontalAlignment(SwingConstants.CENTER);

        JButton b = startButton(f, tf, "Start Game");
        f.add(b);
        f.setSize(800, 800);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static JButton startButton(JFrame f,JTextField tf, String string){
        JButton b = new JButton(string);

        int x_diff = 40;
        int y_diff = 180;

        b.setBounds(355 - x_diff, 200 + y_diff, 140, 40);
        b.addActionListener(e -> {
            String s = tf.getText();
            if (s == null || s.matches(".*[a-zA-Z]+.*") || s.equals("")) {
                JOptionPane.showMessageDialog(f, "Please enter a number");
                return;
            }
            int scoreToWin = Integer.parseInt(s);
            if (scoreToWin < 1) {
                JOptionPane.showMessageDialog(f, "Please enter a number greater than 0");
            } else {
                f.dispose();
                Pong.run(scoreToWin, (winner) -> onWin(winner));
            }
        });
        return b;
    }

    public static void onWin(Side winner){
        JFrame winFrame = new JFrame("WIN!");
        winFrame.setSize(800, 800);
        winFrame.setVisible(true);
        var winnerText = new JLabel("");
        if(winner == Side.RIGHT){
            winnerText = new JLabel("Red WON!");
            winnerText.setForeground(java.awt.Color.RED);
            winnerText.setBounds(350,100 , 100, 30);
        }
        if(winner == Side.LEFT){
            winnerText = new JLabel("Blue WON!");
            winnerText.setForeground(java.awt.Color.BLUE);
            winnerText.setBounds(350, 100 , 100, 30);
        }
        
        int y_diff = 180;

        JLabel scoreLabel = new JLabel("Score to win: ");
        scoreLabel.setBounds(310, 75+ y_diff, 100, 30);

        winFrame.add(scoreLabel);

        JTextField tf = new JTextField("3");
        winFrame.add(tf);
        tf.setBounds(310, 100 + y_diff, 150, 20);

        tf.setHorizontalAlignment(SwingConstants.CENTER);

        JButton b = startButton(winFrame, tf, "Play Again");

        winFrame.add(b);
        
        winFrame.add(winnerText);
        winFrame.setLayout(null);
    }
}