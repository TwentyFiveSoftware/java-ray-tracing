package dev.twentyfive.raytracing.renderer;

import dev.twentyfive.raytracing.RayTracing;
import dev.twentyfive.raytracing.math.Vector3;
import dev.twentyfive.raytracing.scene.Camera;
import dev.twentyfive.raytracing.scene.Scene;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class RenderThread implements Runnable {
    private final AtomicInteger nextRowIndex = new AtomicInteger(-1);
    private final AtomicReferenceArray<Vector3[]> rowsOfPixels = new AtomicReferenceArray<>(RayTracing.HEIGHT);

    private final Camera camera;
    private final Scene scene;

    public RenderThread(Camera camera, Scene scene) {
        this.camera = camera;
        this.scene = scene;
    }

    @Override
    public void run() {
        while (true) {
            final int y = nextRowIndex.incrementAndGet();
            if (y >= RayTracing.HEIGHT) {
                return;
            }

            System.out.format("%d / %d (%.2f%%)\n", y + 1, RayTracing.HEIGHT, ((float) (y + 1) / (float) RayTracing.HEIGHT) * 100.0f);

            Vector3[] pixelsInRow = Renderer.renderRow(y, this.camera, this.scene);
            rowsOfPixels.set(y, pixelsInRow);
        }
    }

    public Vector3[] getPixels() {
        final Vector3[] pixels = new Vector3[RayTracing.WIDTH * RayTracing.HEIGHT];

        for (int y = 0; y < rowsOfPixels.length(); y++) {
            System.arraycopy(rowsOfPixels.get(y), 0, pixels, y * RayTracing.WIDTH, RayTracing.WIDTH);
        }

        return pixels;
    }
}
