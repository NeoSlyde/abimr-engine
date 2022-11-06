package engine.graphics;

import java.util.stream.Stream;

public interface GraphicsEngine {
    void render(Stream<GraphicsEntity> world, Camera camera);
}
