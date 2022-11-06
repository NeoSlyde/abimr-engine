package engine.physics;

import java.util.List;

public interface PhysicsEngine {
    void update(List<? extends PhysicsEntity> entities, double dt);
}
