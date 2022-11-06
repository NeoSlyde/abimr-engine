package engine.io;

public interface IOEngine {
  public void setOnLeft(KeyEvent onEvent);

  public void setOnRight(KeyEvent onEvent);

  public void setOnBottom(KeyEvent onEvent);

  public void setOnTop(KeyEvent onEvent);

  public static interface KeyEvent {
    void onEvent(boolean pressed);
  }
}