package sample;

import java.util.ArrayList;

import java.awt.event.*;
import java.io.File;

import engine.graphics.Camera;
import engine.graphics.SwingGraphicsEngine;
import engine.io.IOEngine;
import engine.misc.Vec2D;
import engine.physics.DefaultPhysicsEngine;
import engine.physics.PhysicsEngine;

public class SampleMiniGame {
  private final SwingGraphicsEngine graphicsEngine;
  private final PhysicsEngine physicsEngine = new DefaultPhysicsEngine();
  private final IOEngine ioEngine;

  private final long FPS = 60;

  private final long PHYSICS_UPDATES_PER_SECOND = 30;

  public SampleMiniGame() {
    this.graphicsEngine = new SwingGraphicsEngine();
    this.ioEngine = graphicsEngine.ioEngine;
  }

  public void run() {
    var mainSquare = new SampleEntity(
        new Vec2D(0, 0),
        new Vec2D(0, 0),
        new Vec2D(0, 0),
        0,
        new Vec2D(100, 100),
        false, 0, new File("sprite.png"));

    var world = new ArrayList<SampleEntity>();
    world.add(mainSquare);

    // IO Engine update
    ioEngine.setOnPress(e -> {
      if (e.getKeyCode() == KeyEvent.VK_UP)
        mainSquare.setVelocity(new Vec2D(0, -2));
      if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        mainSquare.setVelocity(new Vec2D(2, 0));
      if (e.getKeyCode() == KeyEvent.VK_DOWN)
        mainSquare.setVelocity(new Vec2D(0, 2));
      if (e.getKeyCode() == KeyEvent.VK_LEFT)
        mainSquare.setVelocity(new Vec2D(-2, 0));
    });

    ioEngine.setOnRelease(e -> {
      mainSquare.setVelocity(Vec2D.ZERO);
    });

    // Graphics engine update
    repeat(() -> graphicsEngine.render(
        world.stream().map(SampleEntity::toGraphicsEntity),
        new Camera(new Vec2D(0, 0), 1)), 1000 / 60);

    // Physics engine update
    repeat(() -> physicsEngine.update(world, PHYSICS_UPDATES_PER_SECOND), 1000 / 30);
  }

  public static void main(String[] args) {
    new SampleMiniGame().run();
  }

  private static void repeat(Runnable r, long delay) {
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
