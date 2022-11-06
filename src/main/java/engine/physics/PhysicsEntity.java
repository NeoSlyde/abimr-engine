package engine.physics;

import engine.misc.Vec2D;

public interface PhysicsEntity {
    public long getId();

    public Vec2D getSize();

    public Vec2D getPosition();

    public Vec2D getVelocity();

    public Vec2D getAcceleration();

    public double getRotation();

    public boolean isCollides();

    public double getBounceCoefficient();

    public void setPosition(Vec2D position);

    public void setVelocity(Vec2D velocity);

    public void setAcceleration(Vec2D acceleration);

    public void setRotation(double rotation);
}
