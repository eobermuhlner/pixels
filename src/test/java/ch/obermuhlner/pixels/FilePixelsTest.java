package ch.obermuhlner.pixels;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ch.obermuhlner.pixels.TestImageFactory.*;

public class FilePixelsTest {

    @Test
    public void testFiles() {
        writeFilesRGB();
        writeFilesARGB();

        readWriteFiles();
    }

    private void writeFilesRGB() {
        final int size = 100;
        List<BufferedImage> images = new ArrayList<>();
        images.addAll(createStandardImagesRGB(size, size));
        images.addAll(createCustomImagesRGB(size, size));

        for (BufferedImage image : images) {
            fillImage(image, false);
            for (String format : ImageIO.getWriterFormatNames()) {
                try {
                    ImageIO.write(image, format, new File("images/generated/Generated_" + BufferedImages.describe(image) + "." + format));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void writeFilesARGB() {
        final int size = 100;
        List<BufferedImage> images = new ArrayList<>();
        images.addAll(createStandardImagesARGB(size, size));
        images.addAll(createCustomImagesARGB(size, size));

        for (BufferedImage image : images) {
            fillImage(image, true);
            for (String format : ImageIO.getWriterFormatNames()) {
                try {
                    ImageIO.write(image, format, new File("images/generated/Generated_" + BufferedImages.describe(image) + "." + format));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void readWriteFiles() {
        File[] files = new File("images/generated").listFiles();

        for (File file : files) {
            try {
                BufferedImage image = ImageIO.read(file);

                boolean hasAlpha = image.getTransparency() == Transparency.TRANSLUCENT;
                BufferedImage target = BufferedImages.createImage(
                        image.getWidth(),
                        image.getHeight(),
                        hasAlpha ? BufferedImages.Channels.RGBA : BufferedImages.Channels.RGB,
                        BufferedImages.DataType.USHORT,
                        hasAlpha);

                for (int y = 0; y < image.getHeight(); y++) {
                    for (int x = 0; x < image.getWidth(); x++) {
                        Pixels.setRed(target, x, y, Pixels.getRed(image, x, y));
                        Pixels.setGreen(target, x, y, Pixels.getGreen(image, x, y));
                        Pixels.setBlue(target, x, y, Pixels.getBlue(image, x, y));
                        Pixels.setAlpha(target, x, y, Pixels.getAlpha(image, x, y));
                    }
                }

                try {
                    ImageIO.write(image, "png", new File("images/read_write/ReadWrite_" + file.getName() + ".png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void fillImage(BufferedImage image, boolean fillAlpha) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                double rValue = (double)x / image.getWidth();
                double gValue = (double)y / image.getHeight();
                double bValue = (double)(image.getWidth() - x) / image.getWidth();
                double aValue = (double)y / image.getHeight();

                Pixels.setRed(image, x, y, rValue);
                Pixels.setGreen(image, x, y, gValue);
                Pixels.setBlue(image, x, y, bValue);
                if (fillAlpha) {
                    Pixels.setAlpha(image, x, y, aValue);
                }
            }
        }
    }
}
