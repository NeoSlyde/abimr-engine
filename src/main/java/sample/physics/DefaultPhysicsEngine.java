package sample.physics;

public class DefaultPhysicsEngine implements PhysicsEngine {
  @Override
  public void update(PhysicsWorld world, double dt) {
    world.getEntities().forEach(e -> {
      e.setVelocity(e.getVelocity().add(e.getAcceleration()));
      e.setPosition(e.getPosition().add(e.getVelocity()));
      // TODO : a faire, collision

    });
  }
}