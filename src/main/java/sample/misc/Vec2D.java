package sample.misc;

public class Vec2D {
    public final double x;
    public final double y;

    public Vec2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2D add(Vec2D o) {
        return new Vec2D(x + o.x, y + o.y);
    }

    public Vec2D neg() {
        return new Vec2D(-x, -y);
    }

    public Vec2D mult(double k) {
        return new Vec2D(k * x, k * y);
    }
}
