package pong;

import javax.swing.*;

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

        JTextField tf = new JTextField("3");
        f.add(tf);
        tf.setBounds(350 - x_diff, 100 + y_diff, 150, 20);

        tf.setHorizontalAlignment(SwingConstants.CENTER);

        JButton b = new JButton("Start Game");

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
                Pong.run(scoreToWin, (winner) -> {
                    JFrame winFrame = new JFrame("WIN!");
                    winFrame.setSize(800, 800);
                    winFrame.setVisible(true);
                    var winnerText = new JLabel(winner.toString() + " WON!");
                    winFrame.add(winnerText);
                    new Thread(() -> {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        System.exit(0);
                    }).start();
                });
            }

        });
        f.add(b);
        f.setSize(800, 800);
        f.setLayout(null);
        f.setVisible(true);
    }
}