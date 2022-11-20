package engine.pong;

import engine.graphics.GraphicsEntity;
import engine.kernel.Entity;
import engine.misc.Vec2D;
import java.awt.Color;

public class Racket implements Entity {
  public static enum Direction {
    UP, DOWN, IDLE
  }

  private double WIDTH = 10, HEIGHT = 100;

  private Vec2D position;
  private Vec2D velocity = new Vec2D(0, 0);

  public Racket(Vec2D position) {
    this.position = position;
  }

  @Override
  public GraphicsEntity toGraphicsEntity() {
    return new GraphicsEntity(position, getSize(), 0,
        null, Color.BLACK);
  }

  public void setDirection(Direction d) {
    if (d == Direction.UP)
      velocity = new Vec2D(0, -600);
    else if (d == Direction.DOWN)
      velocity = new Vec2D(0, 600);
    else
      velocity = Vec2D.ZERO;
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
    return velocity;
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
    return 0.05; // Accelerate the ball a little bit at each
  }

  @Override
  public void setPosition(Vec2D position) {
    this.position = position;
  }

  @Override
  public void setVelocity(Vec2D velocity) {
    this.velocity = velocity;
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
