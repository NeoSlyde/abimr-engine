package engine.physics;

import java.util.List;

public class DefaultPhysicsEngine implements PhysicsEngine {
  @Override
  public void update(List<? extends PhysicsEntity> entities, double dt) {
    entities.forEach(e -> {
      e.setVelocity(e.getVelocity().add(e.getAcceleration()));
      e.setPosition(e.getPosition().add(e.getVelocity()));
      // TODO : a faire, collision

    });
  }
}