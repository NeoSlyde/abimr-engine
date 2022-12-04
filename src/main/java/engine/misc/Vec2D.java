package engine.misc;

import java.util.function.Function;

public record Vec2D(
        double x,
        double y) {

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public Vec2D setAngle(double radians) {
        return new Vec2D(Math.cos(radians), Math.sin(radians)).withLength(this.length());
    }

    public double angle() {
        return Math.atan2(y, x);
    }

    public Vec2D withLength(double len) {
        return this.mult(len / this.length());
    }

    public Vec2D add(Vec2D o) {
        return new Vec2D(x + o.x, y + o.y);
    }

    public Vec2D neg() {
        return new Vec2D(-x, -y);
    }

    public Vec2D mult(double k) {
        return mapBoth(v -> v * k);
    }

    public Vec2D mapX(Function<Double, Double> f) {
        return new Vec2D(f.apply(x), y);
    }

    public Vec2D mapY(Function<Double, Double> f) {
        return new Vec2D(x, f.apply(y));
    }

    public Vec2D mapBoth(Function<Double, Double> f) {
        return new Vec2D(f.apply(x), f.apply(y));
    }

    public static Vec2D ZERO = new Vec2D(0, 0);
}
