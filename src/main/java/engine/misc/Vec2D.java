package engine.misc;

public record Vec2D(
        double x,
        double y) {

    public Vec2D add(Vec2D o) {
        return new Vec2D(x + o.x, y + o.y);
    }

    public Vec2D neg() {
        return new Vec2D(-x, -y);
    }

    public Vec2D mult(double k) {
        return new Vec2D(k * x, k * y);
    }

    public static Vec2D ZERO = new Vec2D(0, 0);
}
