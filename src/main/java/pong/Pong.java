package pong;

import java.util.Stack;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import engine.audio.AudioPlayer;
import engine.audio.StandardAudioDataFactory;
import engine.kernel.Kernel;
import engine.misc.Vec2D;
import engine.physics.PhysicsEntity;
import pong.Racket.Direction;
import pong.Score.Side;

import java.awt.event.KeyEvent;

public class Pong {
  public static void run(int scoreToWin) {
    var HEIGHT = 800;
    var WIDTH = 800;

    var AudioPlayer = new AudioPlayer();
    var audioDataFactory = new StandardAudioDataFactory();

    var ball = new Ball();

    var leftRacket = new Racket(Vec2D.ZERO, Side.LEFT);
    var rightRacket = new Racket(Vec2D.ZERO, Side.RIGHT);

    var start = (Runnable) () -> {
      var random = (int) (Math.random() * 150 + 150);
      var randomDirection = (int) (Math.random() * 2) * 2 - 1;
      leftRacket.setPosition(new Vec2D(10, 350));
      rightRacket.setPosition(new Vec2D(764, 350));
      ball.setPosition(new Vec2D(375, 375));
      ball.setVelocity(new Vec2D(randomDirection*320, random));
    };

    var resetBlue = (Runnable) () -> {
      var random = (int) (Math.random() * 150 + 150);
      leftRacket.setPosition(new Vec2D(10, 350));
      rightRacket.setPosition(new Vec2D(764, 350));
      ball.setPosition(new Vec2D(375, 375));
      ball.setVelocity(new Vec2D(-320, random));
    };

    var resetRed = (Runnable) () -> {
      var random = (int) (Math.random() * 150 + 150);
      leftRacket.setPosition(new Vec2D(10, 350));
      rightRacket.setPosition(new Vec2D(764, 350));
      ball.setPosition(new Vec2D(375, 375));
      ball.setVelocity(new Vec2D(320, random));
    };

    start.run();

    AudioPlayer.play(audioDataFactory.music());

    var scoreEntities = IntStream.range(0, scoreToWin * 2)
        .mapToObj((i) -> new Score(new Vec2D(i * 28, 0)))
        .toList();

    var world = Stream.concat(
        Stream.of(ball, leftRacket, rightRacket,
            new Wall(new Vec2D(0, -Wall.HEIGHT)),
            new Wall(new Vec2D(0, 760))),
        scoreEntities.stream())
        .toList();

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
          rightRacket.getPosition().mapY(y -> Math.min(Math.max(y, 0), HEIGHT - rightRacket.getSize().y() - 40)));
      leftRacket.setPosition(
          leftRacket.getPosition().mapY(y -> Math.min(Math.max(y, 0), HEIGHT - leftRacket.getSize().y() - 40)));

      if (ball.getPosition().x() < 0) {
        AudioPlayer.play(audioDataFactory.score());
        resetRed.run();
        scoreEntities.stream().filter(e -> e.side == Side.NONE).findFirst().get().setSide(Side.RIGHT);
        if (scoreEntities.stream().filter(e -> e.side == Side.RIGHT).count() >= scoreToWin)
          System.exit(0);
      }
      if (ball.getPosition().x() + ball.getSize().x() > WIDTH) {
        AudioPlayer.play(audioDataFactory.score());
        resetBlue.run();
        scoreEntities.stream().filter(e -> e.side == Side.NONE).findFirst().get().setSide(Side.LEFT);
        if (scoreEntities.stream().filter(e -> e.side == Side.LEFT).count() >= scoreToWin)
          System.exit(0);
      }
    };

    var onCollision = (BiConsumer<PhysicsEntity, PhysicsEntity>) (e1, e2) -> {
      if (e1 instanceof Ball && e2 instanceof Racket) {
          AudioPlayer.play(audioDataFactory.bounce());
      }

      if(e1 instanceof Ball && e2 instanceof Wall) {
          AudioPlayer.play(audioDataFactory.wall());
      }
  };

    var kernel = new Kernel(world, onUpdate, onPress, onRelease, onCollision);
    kernel.start();
  }
}