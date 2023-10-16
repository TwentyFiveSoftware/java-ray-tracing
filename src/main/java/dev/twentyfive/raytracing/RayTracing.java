package dev.twentyfive.raytracing;

import dev.twentyfive.raytracing.image.ImageUtil;
import dev.twentyfive.raytracing.math.Vector3;
import dev.twentyfive.raytracing.scene.Camera;
import dev.twentyfive.raytracing.scene.Renderer;
import dev.twentyfive.raytracing.scene.Scene;

public class RayTracing {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 450;
    public static final int SAMPLES_PER_PIXEL = 1;
    public static final int MAX_RAY_TRACE_DEPTH = 50;

    public static void main(String[] args) {
        final Camera camera = new Camera(new Vector3(12, 2, -3), new Vector3(), 25);
        final Scene scene = Scene.generateRandomScene();
        final Renderer renderer = new Renderer(camera, scene);

        final Vector3[] pixels = renderer.render();
        ImageUtil.savePixelsAsPNG("render.png", pixels);
    }

}
