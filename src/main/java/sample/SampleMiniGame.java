package sample;

import java.awt.Color;
import java.util.ArrayList;

import javax.sound.sampled.Clip;

import engine.audio.AudioPlayer;
import engine.audio.StandardAudioDataFactory;
import engine.graphics.Camera;
import engine.graphics.SwingGraphicsEngine;
import engine.io.IOEngine;
import engine.misc.Vec2D;
import engine.physics.DefaultPhysicsEngine;
import engine.physics.PhysicsEngine;

public class SampleMiniGame {
  private final SwingGraphicsEngine graphicsEngine;
  private final PhysicsEngine physicsEngine = new DefaultPhysicsEngine((e1, e2) -> {
    System.out.println("COLLISION");
  });
  private final IOEngine ioEngine;

  private final long FPS = 60;

  private final long PHYSICS_UPDATES_PER_SECOND = 60;

  Clip music;

  Clip gigaChad;

  SampleEntity mainSquare;

  public SampleMiniGame() {
    this.graphicsEngine = new SwingGraphicsEngine();
    this.ioEngine = graphicsEngine.ioEngine;
  }

  public void run() {
    var audioDataFactory = new StandardAudioDataFactory();
    var audioPlayer = new AudioPlayer();

    music = audioPlayer.play(audioDataFactory.peacefulMusic());

    var world = new ArrayList<SampleEntity>();

    world.add(new SampleEntity(
        Vec2D.ZERO,
        Vec2D.ZERO,
        Vec2D.ZERO,
        0,
        new Vec2D(40, 10000),
        true,
        0,
        null,
        Color.BLACK));
    world.add(new SampleEntity(
        new Vec2D(40, 0),
        Vec2D.ZERO,
        Vec2D.ZERO,
        0,
        new Vec2D(10000, 40),
        true,
        0,
        null,
        Color.BLACK));
    world.add(new SampleEntity(
        new Vec2D(750, 40),
        Vec2D.ZERO,
        Vec2D.ZERO,
        0,
        new Vec2D(40, 10000),
        true,
        0,
        null,
        Color.BLACK));
    world.add(new SampleEntity(
        new Vec2D(40, 725),
        Vec2D.ZERO,
        Vec2D.ZERO,
        0,
        new Vec2D(710, 40),
        true,
        0,
        null,
        Color.BLACK));

    world.add(new SampleEntity(
        new Vec2D(350, 350),
        new Vec2D(50, 100),
        Vec2D.ZERO,
        0,
        new Vec2D(50, 50),
        true,
        1,
        null,
        Color.MAGENTA));

    // IO Engine update
    ioEngine.setOnPress((e) -> {

    });

    ioEngine.setOnRelease((e) -> {

    });

    // Graphics engine update
    repeat(() -> graphicsEngine.render(
        world.stream().map(SampleEntity::toGraphicsEntity),
        new Camera(new Vec2D(0, 0), 1)), 1000 / FPS);

    // Physics engine update
    repeat(() -> {
      physicsEngine.update(world, 1000 / PHYSICS_UPDATES_PER_SECOND);
    }, 1000 / PHYSICS_UPDATES_PER_SECOND);
  }

  public static void main(String[] args) {
    new SampleMiniGame().run();
  }

  private static void repeat(Runnable r, long delay) {
    new Thread(() -> {
      while (true) {
        r.run();
        try {
          Thread.sleep(delay);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }
}
