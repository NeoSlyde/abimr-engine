package engine.misc;

public class Repeat {
  public static void repeat(Runnable r, long delay) {
    new Thread(() -> {
      while (true) {
        r.run();
        try {
          Thread.sleep(delay);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }
}
