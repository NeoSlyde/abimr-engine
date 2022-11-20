package pong;

import engine.graphics.GraphicsEntity;
import engine.kernel.Entity;
import engine.misc.Vec2D;

public class Wall implements Entity {
  public static final double WIDTH = 10000, HEIGHT = 100;

  private Vec2D position;

  public Wall(Vec2D position) {
    this.position = position;
  }

  @Override
  public GraphicsEntity toGraphicsEntity() {
    return new GraphicsEntity(position, getSize(), 0,
        null, null);
  }

  @Override
  public long getId() {
    return 0;
  }

  @Override
  public Vec2D getSize() {
    return new Vec2D(WIDTH, HEIGHT);
  }

  @Override
  public Vec2D getPosition() {
    return position;
  }

  @Override
  public Vec2D getVelocity() {
    return Vec2D.ZERO;
  }

  @Override
  public Vec2D getAcceleration() {
    return Vec2D.ZERO;
  }

  @Override
  public double getRotation() {
    return 0;
  }

  @Override
  public boolean isCollides() {
    return true;
  }

  @Override
  public double getBounceCoefficient() {
    return 1.05; // Accelerate the ball a little bit at each
  }

  @Override
  public void setPosition(Vec2D position) {
    this.position = position;
  }

  @Override
  public void setVelocity(Vec2D velocity) {
  }

  @Override
  public void setAcceleration(Vec2D acceleration) {
    throw new RuntimeException("No Acceleration");
  }

  @Override
  public void setRotation(double rotation) {
    throw new RuntimeException("No rotation");
  }
}
