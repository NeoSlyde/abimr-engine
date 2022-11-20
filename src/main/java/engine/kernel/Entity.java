package engine.kernel;

import engine.graphics.GraphicsEntity;
import engine.physics.PhysicsEntity;

public interface Entity extends PhysicsEntity {
  GraphicsEntity toGraphicsEntity();
}
