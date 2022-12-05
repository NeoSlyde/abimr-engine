package engine.graphics;

import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.io.IOEngine;
import engine.io.SwingIOEngine;

import java.awt.Graphics;

public class SwingGraphicsEngine implements GraphicsEngine {
  public final IOEngine ioEngine;

  private final JPanel panel;

  private final JFrame window;

  public SwingGraphicsEngine() {
    window = new JFrame();
    this.panel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (renderFunction != null)
          renderFunction.accept(g);
      }
    };

    window.add(panel);
    window.setSize(800, 800);
    window.setLocationRelativeTo(null);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setVisible(true);

    this.ioEngine = new SwingIOEngine(window);
  }

  private Consumer<Graphics> renderFunction;

  @Override
  public void render(Stream<GraphicsEntity> worldStream, Camera camera) {
    var world = worldStream.toList();
    this.renderFunction = (g) -> {
      world.forEach(e -> {
        int screenPosX = (int) (e.position().x() - camera.position().x()),
            screenPosY = (int) (e.position().y() - camera.position().y()),
            screenSizeX = (int) e.size().x(),
            screenSizeY = (int) e.size().y();

        if (e.texture() != null) {
          var image = ImgManager.instance.read(e.texture());

          g.drawImage(image, screenPosX, screenPosY, screenSizeX, screenSizeY, panel);
        } else {
          g.setColor(e.color());
          g.fillRect(
              screenPosX,
              screenPosY,
              screenSizeX,
              screenSizeY);
        }
      });
    };
    this.panel.repaint();
  }

  public void dispose(){
    this.window.dispose();
  }

}
