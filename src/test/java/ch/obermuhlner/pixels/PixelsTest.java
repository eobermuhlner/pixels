package ch.obermuhlner.pixels;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.*;

import static ch.obermuhlner.pixels.BufferedImages.describe;
import static ch.obermuhlner.pixels.TestImageFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PixelsTest {
    private static final double COLOR_DELTA = 0.1;

    private static final double STRICT = 0.0;

    @Test
    public void testStandardGetRedGreenBlue() {
        for (BufferedImage image : createStandardImagesRGB(2, 2)) {
            int x = 0;
            int y = 1;

            image.setRGB(x, y, 0xff0000);
            assertEquals(1.0, Pixels.getAlpha(image, x, y), STRICT, describe(image));
            assertEquals(1.0, Pixels.getRed(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getGreen(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getBlue(image, x, y), STRICT, describe(image));

            image.setRGB(x, y, 0x00ff00);
            assertEquals(1.0, Pixels.getAlpha(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getRed(image, x, y), STRICT, describe(image));
            assertEquals(1.0, Pixels.getGreen(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getBlue(image, x, y), STRICT, describe(image));

            image.setRGB(x, y, 0x0000ff);
            assertEquals(1.0, Pixels.getAlpha(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getRed(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getGreen(image, x, y), STRICT, describe(image));
            assertEquals(1.0, Pixels.getBlue(image, x, y), STRICT, describe(image));

            image.setRGB(x, y, 0x336699);
            assertEquals(1.0, Pixels.getAlpha(image, x, y), STRICT, describe(image));
            assertEquals(0.2, Pixels.getRed(image, x, y), COLOR_DELTA, describe(image));
            assertEquals(0.4, Pixels.getGreen(image, x, y), COLOR_DELTA, describe(image));
            assertEquals(0.6, Pixels.getBlue(image, x, y), COLOR_DELTA, describe(image));
        }
    }

    @Test
    public void testStandardGetAlphaRedGreenBlue() {
        for (BufferedImage image : createStandardImagesARGB(2, 2)) {
            int x = 0;
            int y = 1;

            image.setRGB(x, y, 0xff0000);
            assertEquals(0.0, Pixels.getAlpha(image, x, y), STRICT, describe(image));
            assertEquals(1.0, Pixels.getRed(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getGreen(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getBlue(image, x, y), STRICT, describe(image));

            image.setRGB(x, y, 0xffff0000);
            assertEquals(1.0, Pixels.getAlpha(image, x, y), STRICT, describe(image));
            assertEquals(1.0, Pixels.getRed(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getGreen(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getBlue(image, x, y), STRICT, describe(image));

            image.setRGB(x, y, 0x0000ff00);
            assertEquals(0.0, Pixels.getAlpha(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getRed(image, x, y), STRICT, describe(image));
            assertEquals(1.0, Pixels.getGreen(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getBlue(image, x, y), STRICT, describe(image));

            image.setRGB(x, y, 0xff00ff00);
            assertEquals(1.0, Pixels.getAlpha(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getRed(image, x, y), STRICT, describe(image));
            assertEquals(1.0, Pixels.getGreen(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getBlue(image, x, y), STRICT, describe(image));

            image.setRGB(x, y, 0x000000ff);
            assertEquals(0.0, Pixels.getAlpha(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getRed(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getGreen(image, x, y), STRICT, describe(image));
            assertEquals(1.0, Pixels.getBlue(image, x, y), STRICT, describe(image));

            image.setRGB(x, y, 0xff0000ff);
            assertEquals(1.0, Pixels.getAlpha(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getRed(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getGreen(image, x, y), STRICT, describe(image));
            assertEquals(1.0, Pixels.getBlue(image, x, y), STRICT, describe(image));

            image.setRGB(x, y, 0xcc336699);
            assertEquals(0.8, Pixels.getAlpha(image, x, y), COLOR_DELTA, describe(image));
            assertEquals(0.2, Pixels.getRed(image, x, y), COLOR_DELTA, describe(image));
            assertEquals(0.4, Pixels.getGreen(image, x, y), COLOR_DELTA, describe(image));
            assertEquals(0.6, Pixels.getBlue(image, x, y), COLOR_DELTA, describe(image));
        }
    }

    @Test
    public void testSetGetRedGreenBlue() {
        List<BufferedImage> images = new ArrayList<>();
        images.addAll(createStandardImagesRGB(2, 2));
        images.addAll(createCustomImagesRGB(2, 2));

        for (BufferedImage image : images) {
            int x = 0;
            int y = 1;

            Pixels.setRed(image, x, y, 1.0);
            Pixels.setGreen(image, x, y, 0.0);
            Pixels.setBlue(image, x, y, 0.0);
            assertEquals(1.0, Pixels.getAlpha(image, x, y), STRICT, describe(image));
            assertEquals(1.0, Pixels.getRed(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getGreen(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getBlue(image, x, y), STRICT, describe(image));

            Pixels.setRed(image, x, y, 0.0);
            Pixels.setGreen(image, x, y, 1.0);
            Pixels.setBlue(image, x, y, 0.0);
            assertEquals(1.0, Pixels.getAlpha(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getRed(image, x, y), STRICT, describe(image));
            assertEquals(1.0, Pixels.getGreen(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getBlue(image, x, y), STRICT, describe(image));

            Pixels.setRed(image, x, y, 0.0);
            Pixels.setGreen(image, x, y, 0.0);
            Pixels.setBlue(image, x, y, 1.0);
            assertEquals(1.0, Pixels.getAlpha(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getRed(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getGreen(image, x, y), STRICT, describe(image));
            assertEquals(1.0, Pixels.getBlue(image, x, y), STRICT, describe(image));

            Pixels.setRed(image, x, y, 0.2);
            Pixels.setGreen(image, x, y, 0.4);
            Pixels.setBlue(image, x, y, 0.6);
            assertEquals(1.0, Pixels.getAlpha(image, x, y), STRICT, describe(image));
            assertEquals(0.2, Pixels.getRed(image, x, y), COLOR_DELTA, describe(image));
            assertEquals(0.4, Pixels.getGreen(image, x, y), COLOR_DELTA, describe(image));
            assertEquals(0.6, Pixels.getBlue(image, x, y), COLOR_DELTA, describe(image));
        }
    }

    @Test
    public void testSetGetAlphaRedGreenBlue() {
        List<BufferedImage> images = new ArrayList<>();
        images.addAll(createStandardImagesARGB(2, 2));
        images.addAll(createCustomImagesARGB(2, 2));

        for (BufferedImage image : images) {
            int x = 0;
            int y = 1;

            Pixels.setAlpha(image, x, y, 0.0);
            Pixels.setRed(image, x, y, 1.0);
            Pixels.setGreen(image, x, y, 0.0);
            Pixels.setBlue(image, x, y, 0.0);
            assertEquals(0.0, Pixels.getAlpha(image, x, y), STRICT, describe(image));
            assertEquals(1.0, Pixels.getRed(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getGreen(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getBlue(image, x, y), STRICT, describe(image));

            Pixels.setAlpha(image, x, y, 1.0);
            Pixels.setRed(image, x, y, 1.0);
            Pixels.setGreen(image, x, y, 0.0);
            Pixels.setBlue(image, x, y, 0.0);
            assertEquals(1.0, Pixels.getAlpha(image, x, y), STRICT, describe(image));
            assertEquals(1.0, Pixels.getRed(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getGreen(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getBlue(image, x, y), STRICT, describe(image));

            Pixels.setAlpha(image, x, y, 0.0);
            Pixels.setRed(image, x, y, 0.0);
            Pixels.setGreen(image, x, y, 1.0);
            Pixels.setBlue(image, x, y, 0.0);
            assertEquals(0.0, Pixels.getAlpha(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getRed(image, x, y), STRICT, describe(image));
            assertEquals(1.0, Pixels.getGreen(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getBlue(image, x, y), STRICT, describe(image));

            Pixels.setAlpha(image, x, y, 1.0);
            Pixels.setRed(image, x, y, 0.0);
            Pixels.setGreen(image, x, y, 1.0);
            Pixels.setBlue(image, x, y, 0.0);
            assertEquals(1.0, Pixels.getAlpha(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getRed(image, x, y), STRICT, describe(image));
            assertEquals(1.0, Pixels.getGreen(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getBlue(image, x, y), STRICT, describe(image));

            Pixels.setAlpha(image, x, y, 0.0);
            Pixels.setRed(image, x, y, 0.0);
            Pixels.setGreen(image, x, y, 0.0);
            Pixels.setBlue(image, x, y, 1.0);
            assertEquals(0.0, Pixels.getAlpha(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getRed(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getGreen(image, x, y), STRICT, describe(image));
            assertEquals(1.0, Pixels.getBlue(image, x, y), STRICT, describe(image));

            Pixels.setAlpha(image, x, y, 1.0);
            Pixels.setRed(image, x, y, 0.0);
            Pixels.setGreen(image, x, y, 0.0);
            Pixels.setBlue(image, x, y, 1.0);
            assertEquals(1.0, Pixels.getAlpha(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getRed(image, x, y), STRICT, describe(image));
            assertEquals(0.0, Pixels.getGreen(image, x, y), STRICT, describe(image));
            assertEquals(1.0, Pixels.getBlue(image, x, y), STRICT, describe(image));

            Pixels.setAlpha(image, x, y, 0.8);
            Pixels.setRed(image, x, y, 0.2);
            Pixels.setGreen(image, x, y, 0.4);
            Pixels.setBlue(image, x, y, 0.6);
            assertEquals(0.8, Pixels.getAlpha(image, x, y), COLOR_DELTA, describe(image));
            assertEquals(0.2, Pixels.getRed(image, x, y), COLOR_DELTA, describe(image));
            assertEquals(0.4, Pixels.getGreen(image, x, y), COLOR_DELTA, describe(image));
            assertEquals(0.6, Pixels.getBlue(image, x, y), COLOR_DELTA, describe(image));
        }
    }

    @Test
    public void testGetRGB_ARGB() {
        List<BufferedImage> images = new ArrayList<>();
        images.addAll(createStandardImagesRGB(2, 2));
        images.addAll(createCustomImagesRGB(2, 2));

        for (BufferedImage image : images) {
            int x = 0;
            int y = 1;

            double[] rgb;
            double[] argb;

            Pixels.setRed(image, x, y, 1.0);
            Pixels.setGreen(image, x, y, 0.0);
            Pixels.setBlue(image, x, y, 0.0);
            rgb = Pixels.getRGB(image, x, y, null);
            assertEquals(1.0, rgb[0], STRICT, describe(image));
            assertEquals(0.0, rgb[1], STRICT, describe(image));
            assertEquals(0.0, rgb[2], STRICT, describe(image));
            argb = Pixels.getARGB(image, x, y, null);
            assertEquals(1.0, argb[0], STRICT, describe(image));
            assertEquals(1.0, argb[1], STRICT, describe(image));
            assertEquals(0.0, argb[2], STRICT, describe(image));
            assertEquals(0.0, argb[3], STRICT, describe(image));

            Pixels.setRed(image, x, y, 0.0);
            Pixels.setGreen(image, x, y, 1.0);
            Pixels.setBlue(image, x, y, 0.0);
            rgb = Pixels.getRGB(image, x, y, null);
            assertEquals(0.0, rgb[0], STRICT, describe(image));
            assertEquals(1.0, rgb[1], STRICT, describe(image));
            assertEquals(0.0, rgb[2], STRICT, describe(image));
            argb = Pixels.getARGB(image, x, y, null);
            assertEquals(1.0, argb[0], STRICT, describe(image));
            assertEquals(0.0, argb[1], STRICT, describe(image));
            assertEquals(1.0, argb[2], STRICT, describe(image));
            assertEquals(0.0, argb[3], STRICT, describe(image));

            Pixels.setRed(image, x, y, 0.0);
            Pixels.setGreen(image, x, y, 0.0);
            Pixels.setBlue(image, x, y, 1.0);
            rgb = Pixels.getRGB(image, x, y, null);
            assertEquals(0.0, rgb[0], STRICT, describe(image));
            assertEquals(0.0, rgb[1], STRICT, describe(image));
            assertEquals(1.0, rgb[2], STRICT, describe(image));
            argb = Pixels.getARGB(image, x, y, null);
            assertEquals(1.0, argb[0], STRICT, describe(image));
            assertEquals(0.0, argb[1], STRICT, describe(image));
            assertEquals(0.0, argb[2], STRICT, describe(image));
            assertEquals(1.0, argb[3], STRICT, describe(image));

            Pixels.setRed(image, x, y, 0.2);
            Pixels.setGreen(image, x, y, 0.4);
            Pixels.setBlue(image, x, y, 0.6);
            rgb = Pixels.getRGB(image, x, y, null);
            assertEquals(0.2, rgb[0], COLOR_DELTA, describe(image));
            assertEquals(0.4, rgb[1], COLOR_DELTA, describe(image));
            assertEquals(0.6, rgb[2], COLOR_DELTA, describe(image));
            argb = Pixels.getARGB(image, x, y, null);
            assertEquals(1.0, argb[0], STRICT, describe(image));
            assertEquals(0.2, argb[1], COLOR_DELTA, describe(image));
            assertEquals(0.4, argb[2], COLOR_DELTA, describe(image));
            assertEquals(0.6, argb[3], COLOR_DELTA, describe(image));
        }
    }

}
