package fr.uge.memory;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import javax.imageio.ImageIO;

public class ImageLoader {
    private final BufferedImage[] animalImages;
    private final BufferedImage[] habitatImages;

    public ImageLoader(String dir, String[] animals, String[] habitats) {
        Objects.requireNonNull(animals);
        Objects.requireNonNull(habitats);
        animalImages = new BufferedImage[animals.length];
        habitatImages = new BufferedImage[habitats.length];
        for (var i = 0; i < animals.length; i++) {
            setImage(animalImages, i, dir, animals[i]);
        }
        for (var i = 0; i < habitats.length; i++) {
            setImage(habitatImages, i, dir, habitats[i]);
        }
    }

    private void setImage(BufferedImage[] images, int position, String dirPath, String imagePath) {
        Objects.requireNonNull(dirPath);
        Objects.requireNonNull(imagePath);
        var path = Path.of(dirPath + "/" + imagePath);
        try (var input = Files.newInputStream(path)) {
            images[position] = ImageIO.read(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public BufferedImage animalImage(int id) {
        return animalImages[id];
    }

    public BufferedImage habitatImage(int id) {
        return habitatImages[id];
    }
}