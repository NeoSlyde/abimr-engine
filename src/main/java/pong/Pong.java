package pong;

import java.util.Arrays;
import java.util.Stack;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import engine.kernel.Kernel;
import engine.misc.Vec2D;
import engine.physics.PhysicsEntity;
import pong.Racket.Direction;

import java.awt.event.KeyEvent;

public class Pong {
  public static void main(String[] args) {
    var HEIGHT = 800;

    var ball = new Ball();
    ball.setPosition(new Vec2D(375, 375));
    ball.setVelocity(new Vec2D(320, 160));

    var leftRacket = new Racket(new Vec2D(0, 350));
    var rightRacket = new Racket(new Vec2D(778, 350));

    var world = Arrays.asList(ball, leftRacket, rightRacket,
        new Wall(new Vec2D(0, -Wall.HEIGHT)),
        new Wall(new Vec2D(0, 760)));

    var rightKeyStack = new Stack<Direction>();
    var leftKeyStack = new Stack<Direction>();

    var updateRackets = (Runnable) () -> {
      if (rightKeyStack.isEmpty())
        rightRacket.setDirection(Direction.IDLE);
      else
        rightRacket.setDirection(rightKeyStack.peek());
      if (leftKeyStack.isEmpty())
        leftRacket.setDirection(Direction.IDLE);
      else
        leftRacket.setDirection(leftKeyStack.peek());
    };

    var onPress = (Consumer<KeyEvent>) (e) -> {
      if (e.getKeyCode() == KeyEvent.VK_Z && !leftKeyStack.contains(Direction.UP))
        leftKeyStack.add(Direction.UP);
      if (e.getKeyCode() == KeyEvent.VK_S && !leftKeyStack.contains(Direction.DOWN))
        leftKeyStack.add(Direction.DOWN);
      if (e.getKeyCode() == KeyEvent.VK_UP && !rightKeyStack.contains(Direction.UP))
        rightKeyStack.add(Direction.UP);
      if (e.getKeyCode() == KeyEvent.VK_DOWN && !rightKeyStack.contains(Direction.DOWN))
        rightKeyStack.add(Direction.DOWN);
      updateRackets.run();
    };

    var onRelease = (Consumer<KeyEvent>) (e) -> {
      if (e.getKeyCode() == KeyEvent.VK_Z)
        leftKeyStack.remove(Direction.UP);
      if (e.getKeyCode() == KeyEvent.VK_S)
        leftKeyStack.remove(Direction.DOWN);
      if (e.getKeyCode() == KeyEvent.VK_UP)
        rightKeyStack.remove(Direction.UP);
      if (e.getKeyCode() == KeyEvent.VK_DOWN)
        rightKeyStack.remove(Direction.DOWN);
      updateRackets.run();
    };

    var onUpdate = (Runnable) () -> {
      rightRacket.setPosition(
          rightRacket.getPosition().mapY(y -> Math.min(Math.max(y, 0), HEIGHT - rightRacket.getSize().y())));
      leftRacket.setPosition(
          leftRacket.getPosition().mapY(y -> Math.min(Math.max(y, 0), HEIGHT - leftRacket.getSize().y())));
    };

    var onCollision = (BiConsumer<PhysicsEntity, PhysicsEntity>) (e1, e2) -> {
      if (e1 instanceof Ball && e2 instanceof Wall) {
        System.out.println("Wall collision");
      }
    };

    var kernel = new Kernel(world, onUpdate, onPress, onRelease, onCollision);
    kernel.start();
  }
}
