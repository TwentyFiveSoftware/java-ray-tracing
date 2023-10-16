package dev.twentyfive.raytracing.image;

import dev.twentyfive.raytracing.RayTracing;
import dev.twentyfive.raytracing.math.Vector3;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class ImageUtil {

    public static void savePixelsAsPNG(String path, Vector3[] pixels) {
        if (pixels.length != RayTracing.WIDTH * RayTracing.HEIGHT) {
            throw new IllegalArgumentException("pixels array has invalid length");
        }

        int[] packedPixels = new int[RayTracing.WIDTH * RayTracing.HEIGHT * 3];
        for (int i = 0; i < pixels.length; i++) {
            if(pixels[i] == null) {
                System.err.println("skipped pixel " + i);
                continue;
            }

            final Vector3 pixel = pixels[i].sqrt().mul(0xFF);

            packedPixels[i * 3] = (int) pixel.getX();
            packedPixels[i * 3 + 1] = (int) pixel.getY();
            packedPixels[i * 3 + 2] = (int) pixel.getZ();
        }

        BufferedImage image = new BufferedImage(RayTracing.WIDTH, RayTracing.HEIGHT, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = (WritableRaster) image.getData();
        raster.setPixels(0, 0, RayTracing.WIDTH, RayTracing.HEIGHT, packedPixels);
        image.setData(raster);

        try {
            File output = new File(path);
            ImageIO.write(image, "PNG", output);
        } catch (IOException e) {
            System.err.println("error saving image to file");
        }
    }

}
