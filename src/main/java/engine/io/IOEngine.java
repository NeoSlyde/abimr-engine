package engine.io;

import java.awt.event.*;
import java.util.function.Consumer;

public interface IOEngine {
  public void setOnPress(Consumer<KeyEvent> onEvent);

  public void setOnRelease(Consumer<KeyEvent> onEvent);
}