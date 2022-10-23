package sample.graphics;

import java.io.File;

import sample.misc.Vec2D;

public class GraphicsEntity {
    public final Vec2D position;
    public final Vec2D size;
    public final double rotation;
    public final File texture;
    public final int zIndex;

    public GraphicsEntity(Vec2D position, Vec2D size, double rotation, File texture, int zIndex) {
        this.position = position;
        this.size = size;
        this.rotation = rotation;
        this.texture = texture;
        this.zIndex = zIndex;
    }
}
