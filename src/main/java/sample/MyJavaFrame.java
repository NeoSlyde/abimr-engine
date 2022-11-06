package sample;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyJavaFrame extends JFrame implements KeyListener{

  ImageIcon icon;
  JLabel label;


  public MyJavaFrame() {
    super("Demo");
    setSize(800, 800);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    addKeyListener(this);

    icon = new ImageIcon(getClass().getResource("sprite.png"));

    label = new JLabel();
    label.setBounds(0,0,10,10);
    label.setIcon(icon);
    label.setOpaque(true);
    add(label);

    setVisible(true);
  }

  public static void main(String[] args) {
    new MyJavaFrame();
  }

  @Override
  public void keyTyped(KeyEvent e) {
    switch(e.getKeyCode()){
      case KeyEvent.VK_LEFT:
        label.setLocation(label.getX()-10, label.getY());
        break;
      case KeyEvent.VK_RIGHT:
        label.setLocation(label.getX()+10, label.getY());
        break;
      case KeyEvent.VK_UP:
        label.setLocation(label.getX(), label.getY()-10);
        break;
      case KeyEvent.VK_DOWN:
        label.setLocation(label.getX(), label.getY()+10);
        break;
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch(e.getKeyCode()){
      case KeyEvent.VK_LEFT:
        label.setLocation(label.getX()-10, label.getY());
        break;
      case KeyEvent.VK_RIGHT:
        label.setLocation(label.getX()+10, label.getY());
        break;
      case KeyEvent.VK_UP:
        label.setLocation(label.getX(), label.getY()-10);
        break;
      case KeyEvent.VK_DOWN:
        label.setLocation(label.getX(), label.getY()+10);
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    switch(e.getKeyCode()){
      case KeyEvent.VK_LEFT:
        label.setLocation(label.getX()-10, label.getY());
        break;
      case KeyEvent.VK_RIGHT:
        label.setLocation(label.getX()+10, label.getY());
        break;
      case KeyEvent.VK_UP:
        label.setLocation(label.getX(), label.getY()-10);
        break;
      case KeyEvent.VK_DOWN:
        label.setLocation(label.getX(), label.getY()+10);
        break;
      case KeyEvent.VK_SPACE:
        label.setLocation(label.getX(), label.getY()-100);
        break;  
    }
  }

}
