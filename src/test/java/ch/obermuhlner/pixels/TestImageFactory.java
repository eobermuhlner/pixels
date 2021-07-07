package ch.obermuhlner.pixels;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

public class TestImageFactory {

    public static List<BufferedImage> createStandardImagesRGB(int width, int height) {
        return Arrays.asList(
                new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB),
                new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR),
                new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR),
                new BufferedImage(width, height, BufferedImage.TYPE_USHORT_555_RGB),
                new BufferedImage(width, height, BufferedImage.TYPE_USHORT_565_RGB)
        );
    }

    public static List<BufferedImage> createCustomImagesRGB(int width, int height) {
        return Arrays.asList(
                BufferedImages.createImage(width, height, BufferedImages.Channels.RGB, BufferedImages.DataType.BYTE, false),
                BufferedImages.createImage(width, height, BufferedImages.Channels.RGB, BufferedImages.DataType.SHORT, false),
                BufferedImages.createImage(width, height, BufferedImages.Channels.RGB, BufferedImages.DataType.USHORT, false),
                BufferedImages.createImage(width, height, BufferedImages.Channels.RGB, BufferedImages.DataType.INT, false),
                BufferedImages.createImage(width, height, BufferedImages.Channels.RGB, BufferedImages.DataType.FLOAT, false),
                BufferedImages.createImage(width, height, BufferedImages.Channels.RGB, BufferedImages.DataType.DOUBLE, false)
        );
    }

    public static List<BufferedImage> createStandardImagesARGB(int width, int height) {
        return Arrays.asList(
                new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB),
                new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR)
        );
    }

    public static List<BufferedImage> createCustomImagesARGB(int width, int height) {
        return Arrays.asList(
                BufferedImages.createImage(width, height, BufferedImages.Channels.ARGB, BufferedImages.DataType.SHORT, true),
                BufferedImages.createImage(width, height, BufferedImages.Channels.ARGB, BufferedImages.DataType.USHORT, true),
                BufferedImages.createImage(width, height, BufferedImages.Channels.ARGB, BufferedImages.DataType.FLOAT, true),
                BufferedImages.createImage(width, height, BufferedImages.Channels.ARGB, BufferedImages.DataType.DOUBLE, true),

                BufferedImages.createImage(width, height, BufferedImages.Channels.RGBA, BufferedImages.DataType.SHORT, true),
                BufferedImages.createImage(width, height, BufferedImages.Channels.RGBA, BufferedImages.DataType.USHORT, true),
                BufferedImages.createImage(width, height, BufferedImages.Channels.RGBA, BufferedImages.DataType.FLOAT, true),
                BufferedImages.createImage(width, height, BufferedImages.Channels.RGBA, BufferedImages.DataType.DOUBLE, true)
        );
    }

    public List<BufferedImage> createStandardImagesGray(int width, int height) {
        return Arrays.asList(
                new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY),
                new BufferedImage(width, height, BufferedImage.TYPE_USHORT_GRAY)
        );
    }
}
