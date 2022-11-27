package pong;

import java.awt.Color;

import engine.graphics.GraphicsEntity;
import engine.kernel.Entity;
import engine.misc.Vec2D;

public class Score implements Entity {
  public static enum Side {
    LEFT, RIGHT, NONE
  }

  public static int SIZE = 20;

  public final Vec2D pos;
  public Side side = Side.NONE;

  public Score(Vec2D pos) {
    this.pos = pos;
  }

  public void setSide(Side side) {
    this.side = side;
  }

  @Override
  public long getId() {
    return 0;
  }

  @Override
  public Vec2D getSize() {
    return new Vec2D(SIZE, SIZE);
  }

  @Override
  public Vec2D getPosition() {
    return pos;
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
    return false;
  }

  @Override
  public double getBounceCoefficient() {
    return 0;
  }

  @Override
  public void setPosition(Vec2D position) {
  }

  @Override
  public void setVelocity(Vec2D velocity) {

  }

  @Override
  public void setAcceleration(Vec2D acceleration) {

  }

  @Override
  public void setRotation(double rotation) {

  }

  @Override
  public GraphicsEntity toGraphicsEntity() {
    return new GraphicsEntity(getPosition(), getSize(), getRotation(), null,
        side == Side.LEFT ? Color.BLUE : side == Side.RIGHT ? Color.RED : Color.GRAY);
  }
}
