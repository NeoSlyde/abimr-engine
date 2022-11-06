package engine.io;

import java.awt.event.*;
import java.util.function.Consumer;

import javax.swing.JFrame;

public class SwingIOEngine implements IOEngine, KeyListener {
  public SwingIOEngine(JFrame window) {
    window.addKeyListener(this);
  }

  private Consumer<KeyEvent> onPress = null, onRelease = null;

  @Override
  public void setOnPress(Consumer<KeyEvent> onPress) {
    this.onPress = onPress;

  }

  @Override
  public void setOnRelease(Consumer<KeyEvent> onRelease) {
    this.onRelease = onRelease;

  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    onPress.accept(e);
  }

  @Override
  public void keyReleased(KeyEvent e) {
    onRelease.accept(e);
  }
}
