package engine.graphics;

import engine.misc.Vec2D;

public record Camera(
                Vec2D position,
                double zoom) {
}