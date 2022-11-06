package engine.graphics;

import java.util.List;

public class GraphicsWorld {
    private List<GraphicsEntity> entities;

    public GraphicsWorld(List<GraphicsEntity> entities) {
        this.entities = entities;
    }

    public List<GraphicsEntity> getEntities() {
        return entities;
    }
}
