package engine.graphics;

import java.io.File;

import engine.misc.Vec2D;

public record GraphicsEntity(
        Vec2D position,
        Vec2D size,
        double rotation,
        File texture,
        Color color) {
};