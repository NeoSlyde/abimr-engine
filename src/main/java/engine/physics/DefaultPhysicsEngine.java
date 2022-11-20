package engine.physics;

import java.util.List;
import java.util.function.BiConsumer;

public record DefaultPhysicsEngine(
    BiConsumer<PhysicsEntity, PhysicsEntity> collisionHandler)
    implements PhysicsEngine {

  @Override
  public void update(List<? extends PhysicsEntity> entities, double dt) {
    // Collision detection
    for (int i = 0; i < entities.size(); ++i) {
      var e1 = entities.get(i);
      if (!e1.isCollides())
        continue;
      for (int j = i + 1; j < entities.size(); ++j) {
        var e2 = entities.get(j);
        if (!e2.isCollides() || !e1.collidesWith(e2))
          continue;

        var xDiff = Math.min(
            Math.abs(e1.getPosition().x() - (e2.getPosition().x() + e2.getSize().x())),
            Math.abs(e2.getPosition().x() - (e1.getPosition().x() + e1.getSize().x())));
        var yDiff = Math.min(
            Math.abs(e1.getPosition().y() - (e2.getPosition().y() + e2.getSize().y())),
            Math.abs(e2.getPosition().y() - (e1.getPosition().y() + e1.getSize().y())));

        if (yDiff < xDiff) {
          e1.setVelocity(e1.getVelocity().mapY(y -> y * -e1.getBounceCoefficient()));
          e2.setVelocity(e2.getVelocity().mapY(y -> y * -e2.getBounceCoefficient()));
        } else {
          e1.setVelocity(e1.getVelocity().mapX(x -> x * -e1.getBounceCoefficient()));
          e2.setVelocity(e2.getVelocity().mapX(x -> x * -e2.getBounceCoefficient()));
        }
        collisionHandler.accept(e1, e2);
      }
    }

    entities.forEach(e -> {
      e.setVelocity(e.getVelocity().add(e.getAcceleration().mult(dt / 1000.)));
      e.setPosition(e.getPosition().add(e.getVelocity().mult(dt / 1000.)));
    });
  }
}