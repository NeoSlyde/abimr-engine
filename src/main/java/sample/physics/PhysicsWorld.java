package sample.physics;

import java.util.List;
import java.util.Optional;

public class PhysicsWorld {
    private List<PhysicsEntity> entities;
    
    public PhysicsWorld(List<PhysicsEntity> entities) {
        this.entities = entities;
    }

    public List<PhysicsEntity> getEntities() {
        return entities;
    }

    public Optional<PhysicsEntity> getEntityById(long id) {
        return entities.stream().filter(e -> e.getId() == id).findFirst();
    }
}
