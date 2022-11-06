package sample;

import java.util.ArrayList;

import engine.graphics.Camera;
import engine.graphics.GraphicsEngine;
import engine.io.IOEngine;
import engine.misc.Vec2D;
import engine.physics.DefaultPhysicsEngine;
import engine.physics.PhysicsEngine;

public class SampleMiniGame {
  private final GraphicsEngine graphicsEngine;
  private final PhysicsEngine physicsEngine = new DefaultPhysicsEngine();
  private final IOEngine ioEngine = null; // TODO

  private final long FPS = 60;

  private final long PHYSICS_UPDATES_PER_SECOND = 30;

  public SampleMiniGame(GraphicsEngine graphicsEngine) {
    this.graphicsEngine = graphicsEngine;
  }

  public void run() {
    var mainSquare = new SampleEntity(
        new Vec2D(0, 0),
        new Vec2D(0, 0),
        new Vec2D(0, 0),
        0,
        new Vec2D(1, 1),
        false, 0);

    var world = new ArrayList<SampleEntity>();
    world.add(mainSquare);

    // Graphics engine update
    new Thread(() -> {
      while (true) {
        graphicsEngine.render(
            world.stream().map(SampleEntity::toGraphicsEntity),
            new Camera(new Vec2D(0, 0), 1));
        try {
          Thread.sleep(1000 / FPS);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).run();

    // Physics engine update
    new Thread(() -> {
      while (true) {
        physicsEngine.update(world, PHYSICS_UPDATES_PER_SECOND);
        try {
          Thread.sleep(1000 / FPS);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).run();

    ioEngine.setOnLeft((pushed) -> mainSquare.setVelocity(
        new Vec2D(pushed ? -1 : 0, 0)));
    ioEngine.setOnRight((pushed) -> mainSquare.setVelocity(
        new Vec2D(pushed ? 1 : 0, 0)));
    ioEngine.setOnBottom((pushed) -> mainSquare.setVelocity(
        new Vec2D(0, pushed ? 1 : 0)));
    ioEngine.setOnTop((pushed) -> mainSquare.setVelocity(
        new Vec2D(0, pushed ? -1 : 0)));
  }
}
