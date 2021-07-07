package ch.obermuhlner.pixels;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;

import static ch.obermuhlner.pixels.BufferedImages.describe;

public class Pixels {
    private static double BYTE_MAX_VALUE = 0xff;
    private static double SHORT_MAX_VALUE = 0x7fff;
    private static double USHORT_MAX_VALUE = 0xffff;
    private static double INT_MAX_VALUE = 0x7fffffff;

    public static double getRed(BufferedImage image, int x, int y) {
        if (image.getType() != BufferedImage.TYPE_CUSTOM) {
            int rgb = image.getRGB(x, y);
            return ((rgb >> 16) & 0xff) / 255.0;
        }

        int n = image.getColorModel().getNumComponents();
        double[] array = new double[n];
        image.getRaster().getPixel(x, y, array);
        return getPixelValue(image, 0, array);
    }

    public static double getGreen(BufferedImage image, int x, int y) {
        if (image.getType() != BufferedImage.TYPE_CUSTOM) {
            int rgb = image.getRGB(x, y);
            return ((rgb >> 8) & 0xff) / 255.0;
        }

        int n = image.getColorModel().getNumComponents();
        double[] array = new double[n];
        image.getRaster().getPixel(x, y, array);
        return getPixelValue(image, Math.min(1, n-1), array);
    }

    public static double getBlue(BufferedImage image, int x, int y) {
        if (image.getType() != BufferedImage.TYPE_CUSTOM) {
            int rgb = image.getRGB(x, y);
            return (rgb & 0xff) / 255.0;
        }

        int n = image.getColorModel().getNumComponents();
        double[] array = new double[n];
        image.getRaster().getPixel(x, y, array);
        return getPixelValue(image, Math.min(2, n-1), array);
    }

    public static double getAlpha(BufferedImage image, int x, int y) {
        if (image.getType() != BufferedImage.TYPE_CUSTOM) {
            int rgb = image.getRGB(x, y);
            return ((rgb >> 24) & 0xff) / 255.0;
        }

        int n = image.getColorModel().getNumComponents();
        if (n == 4) {
            double[] array = new double[n];
            image.getRaster().getPixel(x, y, array);
            return getPixelValue(image, 3, array);
        }

        return 1.0;
    }

    public static double getGray(BufferedImage image, int x, int y) {
        // TODO optimized impl

        return (getRed(image, x, y) + getGreen(image, x, y) + getBlue(image, x, y)) / 3;
    }

    public static double[] getRGB(BufferedImage image, int x, int y, double[] rgb) {
        if (rgb == null) {
            rgb = new double[3];
        }

        if (image.getType() != BufferedImage.TYPE_CUSTOM) {
            int intRGB = image.getRGB(x, y);
            rgb[0] = ((intRGB >> 16) & 0xff) / 255.0;
            rgb[1] = ((intRGB >> 8) & 0xff) / 255.0;
            rgb[2] = (intRGB & 0xff) / 255.0;
        } else {
            int n = image.getColorModel().getNumComponents();
            double[] rasterArray = n > rgb.length ? new double[n] : rgb;
            image.getRaster().getPixel(x, y, rasterArray);
            double r = getPixelValue(image, 0, rasterArray);
            double g = getPixelValue(image, 1, rasterArray);
            double b = getPixelValue(image, 2, rasterArray);

            rgb[0] = r;
            rgb[1] = g;
            rgb[2] = b;
        }

        return rgb;
    }

    public static double[] getARGB(BufferedImage image, int x, int y, double[] argb) {
        if (argb == null) {
            argb = new double[4];
        }

        if (image.getType() != BufferedImage.TYPE_CUSTOM) {
            int intARGB = image.getRGB(x, y);
            argb[0] = ((intARGB >> 24) & 0xff) / 255.0;
            argb[1] = ((intARGB >> 16) & 0xff) / 255.0;
            argb[2] = ((intARGB >> 8) & 0xff) / 255.0;
            argb[3] = (intARGB & 0xff) / 255.0;
        } else {
            int n = image.getColorModel().getNumComponents();
            double[] rasterArray = n > argb.length ? new double[n] : argb;
            image.getRaster().getPixel(x, y, rasterArray);
            double a = n >= 4 ? getPixelValue(image, 3, rasterArray) : 1.0;
            double r = getPixelValue(image, Math.min(0, n-1), rasterArray);
            double g = getPixelValue(image, Math.min(1, n-1), rasterArray);
            double b = getPixelValue(image, Math.min(2, n-1), rasterArray);

            argb[0] = a;
            argb[1] = r;
            argb[2] = g;
            argb[3] = b;
        }

        return argb;
    }

    public static void setRed(BufferedImage image, int x, int y, double value) {
        if (image.getType() != BufferedImage.TYPE_CUSTOM) {
            int rgb = image.getRGB(x, y) & 0xff00ffff;
            int v = (int) (value * 0xff);
            rgb = rgb | v << 16;
            image.setRGB(x, y, rgb);
            return;
        }

        setPixelValue(image, x, y, 0, value);
    }

    public static void setGreen(BufferedImage image, int x, int y, double value) {
        if (image.getType() != BufferedImage.TYPE_CUSTOM) {
            int rgb = image.getRGB(x, y) & 0xffff00ff;
            int v = (int) (value * 0xff);
            rgb = rgb | v << 8;
            image.setRGB(x, y, rgb);
            return;
        }

        setPixelValue(image, x, y, 1, value);
    }

    public static void setBlue(BufferedImage image, int x, int y, double value) {
        if (image.getType() != BufferedImage.TYPE_CUSTOM) {
            int rgb = image.getRGB(x, y) & 0xffffff00;
            int v = (int) (value * 0xff);
            rgb = rgb | v;
            image.setRGB(x, y, rgb);
            return;
        }

        setPixelValue(image, x, y, 2, value);
    }

    public static void setAlpha(BufferedImage image, int x, int y, double value) {
        if (image.getType() != BufferedImage.TYPE_CUSTOM) {
            int rgb = image.getRGB(x, y) & 0x00ffffff;
            int v = (int) (value * 0xff) << 24;
            rgb = rgb | v;
            image.setRGB(x, y, rgb);
            return;
        }

        setPixelValue(image, x, y, 3, value);
    }

    public static void setGrey(BufferedImage image, int x, int y, double value) {
        int n = image.getColorModel().getNumComponents();

        if (n == 1) {
            setPixelValue(image, x, y, 0, value);
        } else {
            setPixelValue(image, x, y, 0, value);
            setPixelValue(image, x, y, 1, value);
            setPixelValue(image, x, y, 2, value);
        }
    }

    public static void setRGB(BufferedImage image, int x, int y, double[] rgb) {
        setPixelValue(image, x, y, 0, rgb[0]);
        setPixelValue(image, x, y, 1, rgb[1]);
        setPixelValue(image, x, y, 2, rgb[2]);
    }

    public static void setARGB(BufferedImage image, int x, int y, double[] argb) {
        setPixelValue(image, x, y, 0, argb[0]);
        setPixelValue(image, x, y, 1, argb[1]);
        setPixelValue(image, x, y, 2, argb[2]);
        setPixelValue(image, x, y, 3, argb[3]);
    }

    private static double getPixelValue(BufferedImage image, int sample, double[] array) {
        switch (image.getRaster().getDataBuffer().getDataType()) {
            case DataBuffer.TYPE_BYTE:
                return array[sample]/BYTE_MAX_VALUE;
            case DataBuffer.TYPE_SHORT:
                return array[sample]/SHORT_MAX_VALUE;
            case DataBuffer.TYPE_USHORT:
                return array[sample]/USHORT_MAX_VALUE;
            case DataBuffer.TYPE_INT:
                return array[sample]/INT_MAX_VALUE;
            case DataBuffer.TYPE_FLOAT:
            case DataBuffer.TYPE_DOUBLE:
                return array[sample];
        }

        throw new IllegalArgumentException("Not supported: " + describe(image));
    }


    private static void setPixelValue(BufferedImage image, int x, int y, int sample, double value) {
        if (sample >= image.getRaster().getNumDataElements()) {
            return;
        }

        double maxValue;

        switch (image.getRaster().getDataBuffer().getDataType()) {
            case DataBuffer.TYPE_BYTE:
                maxValue = BYTE_MAX_VALUE;
                break;
            case DataBuffer.TYPE_SHORT:
                maxValue = SHORT_MAX_VALUE;
                break;
            case DataBuffer.TYPE_USHORT:
                maxValue = USHORT_MAX_VALUE;
                break;
            case DataBuffer.TYPE_INT:
                maxValue = INT_MAX_VALUE;
                break;
            case DataBuffer.TYPE_FLOAT:
            case DataBuffer.TYPE_DOUBLE:
                maxValue = 1.0;
                break;
            default:
                throw new IllegalArgumentException("Not supported: " + describe(image));
        }

        image.getRaster().setSample(x, y, sample, value * maxValue);
    }
}
