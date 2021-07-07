package ch.obermuhlner.pixels;

import javax.imageio.ImageTypeSpecifier;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BufferedImages {
    enum Channels {
        RGB(0, 1, 2),
        RGBA(0, 1, 2, 3),
        ABGR(3, 2, 1, 0),
        ARGB(3, 0, 1, 2),
        GRAY(0);

        int[] bandOffsets;

        Channels(int... bandOffsets) {
            this.bandOffsets = bandOffsets;
        }
    }

    public enum DataType {
        BYTE(DataBuffer.TYPE_BYTE),
        USHORT(DataBuffer.TYPE_USHORT),
        SHORT(DataBuffer.TYPE_SHORT),
        INT(DataBuffer.TYPE_INT),
        FLOAT(DataBuffer.TYPE_FLOAT),
        DOUBLE(DataBuffer.TYPE_DOUBLE);

        int dataBufferType;

        DataType(int dataBufferType) {
            this.dataBufferType = dataBufferType;
        }
    }

    public static BufferedImage createImage(int width, int height, Channels channels, DataType dataType, boolean hasAlpha) {
        return ImageTypeSpecifier.createInterleaved(
                ColorSpace.getInstance(ColorSpace.CS_sRGB),
                channels.bandOffsets,
                dataType.dataBufferType,
                hasAlpha,
                false
        ).createBufferedImage(width, height);
    }

    private static final Map<Integer, String> imageTypeName = new HashMap<>();
    static {
        imageTypeName.put(BufferedImage.TYPE_INT_RGB, "TYPE_INT_RGB");
        imageTypeName.put(BufferedImage.TYPE_INT_ARGB, "TYPE_INT_ARGB");
        imageTypeName.put(BufferedImage.TYPE_INT_ARGB_PRE, "TYPE_INT_ARGB_PRE");
        imageTypeName.put(BufferedImage.TYPE_INT_BGR, "TYPE_INT_BGR");
        imageTypeName.put(BufferedImage.TYPE_3BYTE_BGR, "TYPE_3BYTE_BGR");
        imageTypeName.put(BufferedImage.TYPE_4BYTE_ABGR, "TYPE_4BYTE_ABGR");
        imageTypeName.put(BufferedImage.TYPE_4BYTE_ABGR_PRE, "TYPE_4BYTE_ABGR_PRE");
        imageTypeName.put(BufferedImage.TYPE_BYTE_GRAY, "TYPE_BYTE_GRAY");
        imageTypeName.put(BufferedImage.TYPE_BYTE_BINARY, "TYPE_BYTE_BINARY");
        imageTypeName.put(BufferedImage.TYPE_BYTE_INDEXED, "TYPE_BYTE_INDEXED");
        imageTypeName.put(BufferedImage.TYPE_USHORT_GRAY, "TYPE_USHORT_GRAY");
        imageTypeName.put(BufferedImage.TYPE_USHORT_565_RGB, "TYPE_USHORT_565_RGB");
        imageTypeName.put(BufferedImage.TYPE_USHORT_555_RGB, "TYPE_USHORT_555_RGB");
        imageTypeName.put(BufferedImage.TYPE_CUSTOM, "TYPE_CUSTOM");
    }

    private static final Map<Integer, String> dataTypeName = new HashMap<>();
    static {
        dataTypeName.put(DataBuffer.TYPE_BYTE, "TYPE_INT_RGB");
        dataTypeName.put(DataBuffer.TYPE_USHORT, "TYPE_USHORT");
        dataTypeName.put(DataBuffer.TYPE_SHORT, "TYPE_SHORT");
        dataTypeName.put(DataBuffer.TYPE_INT, "TYPE_INT");
        dataTypeName.put(DataBuffer.TYPE_FLOAT, "TYPE_FLOAT");
        dataTypeName.put(DataBuffer.TYPE_DOUBLE, "TYPE_DOUBLE");
    }

    public static String describe(BufferedImage image) {
        return "type=" + imageTypeName.get(image.getType()) +
                ", sampleSize=" + Arrays.toString(image.getColorModel().getComponentSize()) +
                ", dataSize=" + image.getRaster().getNumDataElements() +
                ", dataType=" + dataTypeName.get(image.getRaster().getDataBuffer().getDataType());
    }
}
