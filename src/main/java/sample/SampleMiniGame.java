package sample;

import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.Clip;

import java.awt.event.*;
import java.awt.Color;
import java.io.File;

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
  private final PhysicsEngine physicsEngine = new DefaultPhysicsEngine();
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
    //wait 5 seconds
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    var audioDataFactory = new StandardAudioDataFactory();
    var audioPlayer = new AudioPlayer();

    music = audioPlayer.play(audioDataFactory.peacefulMusic());

    gigaChad = audioPlayer.play(audioDataFactory.gigaChad());

    gigaChad.stop();

    mainSquare = new SampleEntity(
        new Vec2D(150, 0),
        new Vec2D(0, 0),
        new Vec2D(0, 1500),
        0,
        new Vec2D(46, 41),
        false, 0, new File("flop.png"), null);

    var world = new ArrayList<SampleEntity>();

    for (int i = 0; i < 1000; ++i) {
      double gap = 240;
      double maxheight = 800;
      double y = new Random().nextDouble() * (maxheight - gap);

      world.add(new SampleEntity(new Vec2D(200 * (i + 2), 0),
          new Vec2D(-120, 0),
          Vec2D.ZERO,
          0,
          new Vec2D(60, y),
          false,
          0,
          null, Color.DARK_GRAY));
      world.add(new SampleEntity(new Vec2D(200 * (i + 2), y + gap),
          new Vec2D(-120, 0),
          Vec2D.ZERO,
          0,
          new Vec2D(60, 10000),
          false,
          0,
          null, Color.DARK_GRAY));
    }
    world.add(mainSquare);

    // IO Engine update
    ioEngine.setOnPress((e) -> {
      if (e.getKeyCode() == KeyEvent.VK_SPACE){mainSquare.setVelocity(mainSquare.getVelocity().add(new Vec2D(0, -1000)));
        audioPlayer.play(audioDataFactory.jumpSound());}
      else if (e.getKeyCode() == KeyEvent.VK_G){
          if (gigaChad.isRunning()){
            return;
          }
          else{
            mainSquare.setTexture(new File("gigachad.png"));
            music.stop();
            gigaChad.setFramePosition(0);
            gigaChad.start();
          }
      
    }});

    ioEngine.setOnRelease((e) -> {

    });

    // Graphics engine update
    repeat(() -> graphicsEngine.render(
        world.stream().map(SampleEntity::toGraphicsEntity),
        new Camera(new Vec2D(0, 0), 1)), 1000 / FPS);

    // Physics engine update
    repeat(() -> {
      if (mainSquare.getVelocity().y() < -450) {
        mainSquare.setVelocity(new Vec2D(mainSquare.getVelocity().x(), -450));
      }
      if (mainSquare.getVelocity().y() > 2000) {
        mainSquare.setVelocity(new Vec2D(mainSquare.getVelocity().x(), 2000));
      }
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
