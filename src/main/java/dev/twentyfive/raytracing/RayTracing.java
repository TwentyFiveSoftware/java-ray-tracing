package dev.twentyfive.raytracing;

import dev.twentyfive.raytracing.image.ImageUtil;
import dev.twentyfive.raytracing.math.Vector3;
import dev.twentyfive.raytracing.scene.Camera;
import dev.twentyfive.raytracing.renderer.Renderer;
import dev.twentyfive.raytracing.scene.Scene;

public class RayTracing {
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;
    public static final int SAMPLES_PER_PIXEL = 100;
    public static final int MAX_RAY_TRACE_DEPTH = 50;
    public static final int RENDER_THREADS = 24;

    public static void main(String[] args) {
        final Camera camera = new Camera(new Vector3(12, 2, -3), new Vector3(), 25);
        final Scene scene = Scene.generateRandomScene();

        final long renderStartTime = System.currentTimeMillis();

        final Vector3[] pixels = Renderer.render(camera, scene);

        final long renderFinishTime = System.currentTimeMillis();
        final long renderTime = renderFinishTime - renderStartTime;
        System.out.format("rendered %d samples/pixel with %d threads in %d ms\n", SAMPLES_PER_PIXEL, RENDER_THREADS, renderTime);

        ImageUtil.savePixelsAsPNG("render.png", pixels);
    }
}
