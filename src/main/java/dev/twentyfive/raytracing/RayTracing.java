package dev.twentyfive.raytracing;

import dev.twentyfive.raytracing.image.ImageUtil;
import dev.twentyfive.raytracing.math.Vector3;
import dev.twentyfive.raytracing.scene.Camera;
import dev.twentyfive.raytracing.scene.Renderer;

public class RayTracing {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 450;
    public static final int SAMPLES_PER_PIXEL = 10;

    public static void main(String[] args) {
        final Camera camera = new Camera(new Vector3(12, 2, -3), new Vector3(), 25);
        final Renderer renderer = new Renderer(camera);

        final Vector3[] pixels = renderer.render();
        ImageUtil.savePixelsAsPNG("render.png", pixels);
    }

}
