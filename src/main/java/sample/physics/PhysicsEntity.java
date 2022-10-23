package sample.physics;

import sample.misc.Vec2D;

public class PhysicsEntity {
    public final long id;
    public final Vec2D position;
    public final Vec2D size;
    public final Vec2D velocity;
    public final Vec2D acceleration;
    public final double rotation;
    public final boolean collides;
    public final double bounceCoefficient;

    public PhysicsEntity(long id, Vec2D position, Vec2D size, Vec2D velocity, Vec2D acceleration, double rotation, boolean collides, double bounceCoefficient) {
        this.id = id;
        this.position = position;
        this.size = size;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.rotation = rotation;
        this.collides = collides;
        this.bounceCoefficient = bounceCoefficient;
    }

    public long getId() {
        return id;
    }


}
