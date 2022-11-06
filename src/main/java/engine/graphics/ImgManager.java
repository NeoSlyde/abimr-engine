package engine.graphics;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImgManager {
  public static ImgManager instance = new ImgManager();

  private static Map<String, BufferedImage> cache = new HashMap<>();

  BufferedImage read(File path) {
    if (cache.containsKey(path.getPath()))
      return cache.get(path.getPath());
    try {
      var image = ImageIO.read(Objects.requireNonNull(
          getClass().getResource(path.getPath())));

      cache.put(path.getPath(), image);
      return image;
    } catch (IOException exception) {
      exception.printStackTrace();
      return null;
    }
  }
}
