package sample.graphics;

import sample.misc.Vec2D;

public class Camera {
    public final Vec2D position;
    public final double zoom;

    public Camera(Vec2D position, double zoom) {
        this.position = position;
        this.zoom = zoom;
    }
}
