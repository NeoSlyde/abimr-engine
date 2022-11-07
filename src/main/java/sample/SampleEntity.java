package sample;

import java.util.Random;
import java.awt.Color;

import java.io.File;

import engine.graphics.GraphicsEntity;
import engine.misc.Vec2D;
import engine.physics.PhysicsEntity;

public class SampleEntity implements PhysicsEntity {
  private final long id;
  private final Vec2D size;
  private final boolean collides;
  private final double bounceCoeficient;
  private Vec2D position;
  private Vec2D velocity;
  private Vec2D acceleration;
  private double rotation;
  private final File texture;
  private final Color color;

  public SampleEntity(
      Vec2D position, Vec2D velocity, Vec2D acceleration, double rotation,
      Vec2D size, boolean collides, double bounceCoeficient, File texture, Color color) {
    this.id = new Random().nextLong();
    this.size = size;
    this.collides = collides;
    this.bounceCoeficient = bounceCoeficient;
    this.position = position;
    this.velocity = velocity;
    this.acceleration = acceleration;
    this.rotation = rotation;
    this.texture = texture;
    this.color = color;
  }

  public GraphicsEntity toGraphicsEntity() {
    return new GraphicsEntity(position, size, rotation, texture, color);
  }

  @Override
  public long getId() {
    return id;
  }

  @Override
  public Vec2D getSize() {
    return size;
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
    return acceleration;
  }

  @Override
  public double getRotation() {
    return rotation;
  }

  @Override
  public boolean isCollides() {
    return collides;
  }

  @Override
  public double getBounceCoefficient() {
    return bounceCoeficient;
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
    this.acceleration = acceleration;
  }

  @Override
  public void setRotation(double rotation) {
    this.rotation = rotation;
  }
}
