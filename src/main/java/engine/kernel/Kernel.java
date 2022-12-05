package engine.kernel;

import static engine.misc.Repeat.repeat;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import engine.graphics.Camera;
import engine.graphics.SwingGraphicsEngine;
import engine.misc.Vec2D;
import engine.physics.DefaultPhysicsEngine;
import java.awt.event.KeyEvent;

import engine.physics.PhysicsEntity;

public record Kernel(
    List<Entity> world,
    Runnable onUpdate,
    Consumer<KeyEvent> onPress,
    Consumer<KeyEvent> onRelease,
    BiConsumer<PhysicsEntity, PhysicsEntity> collisionHandler,
    SwingGraphicsEngine graphicsEngine) {

  private static final long PHYSICS_UPDATES_PER_SECOND = 120;
  private static final long FPS = 60;


  public void start() {
    var physicsEngine = new DefaultPhysicsEngine(collisionHandler);
    graphicsEngine.ioEngine.setOnPress(onPress);
    graphicsEngine.ioEngine.setOnRelease(onRelease);

    // Graphics engine update
    repeat(() -> graphicsEngine.render(
        world.stream().map(Entity::toGraphicsEntity),
        new Camera(new Vec2D(0, 0), 1)), 1000 / FPS);

    // Physics engine update
    repeat(() -> {
      onUpdate.run();
      physicsEngine.update(world, 1000 / PHYSICS_UPDATES_PER_SECOND);
    }, 1000 / PHYSICS_UPDATES_PER_SECOND);
  }
}
