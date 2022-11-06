package sample;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MyJavaFrame extends JFrame {
  private transient BufferedImage image;

  public static void main(String[] args) {
    new MyJavaFrame();
  }

  public class MyJavaPanel extends JPanel {
    private int x = 0;

    public MyJavaPanel() {
      new Thread(() -> {
        while (true) {
          x += 1;
          repaint();
          try {
            Thread.sleep(1000 / 60);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }).start();
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(image, x, 10, null);
    }
  }

  public MyJavaFrame() {
    super("Swing Game ");
    try {
      this.image = ImageIO.read(Objects.requireNonNull(getClass().getResource("sprite.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }

    add(new MyJavaPanel());
    setSize(800, 800);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setVisible(true);
  }
}
