package dev.twentyfive.raytracing.renderer;

import dev.twentyfive.raytracing.RayTracing;
import dev.twentyfive.raytracing.math.RandomUtil;
import dev.twentyfive.raytracing.math.Vector3;
import dev.twentyfive.raytracing.primitives.Ray;
import dev.twentyfive.raytracing.records.HitRecord;
import dev.twentyfive.raytracing.records.ScatterRecord;
import dev.twentyfive.raytracing.scene.Camera;
import dev.twentyfive.raytracing.scene.Scene;

import java.util.ArrayList;

public class Renderer {
    public static Vector3[] render(Camera camera, Scene scene) {
        RenderThread renderThread = new RenderThread(camera, scene);

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < RayTracing.RENDER_THREADS; i++) {
            Thread newThread = new Thread(renderThread);
            newThread.start();
            threads.add(newThread);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ignored) {
            }
        }

        return renderThread.getPixels();
    }

    public static Vector3[] renderRow(int y, Camera camera, Scene scene) {
        Vector3[] pixelsInRow = new Vector3[RayTracing.WIDTH];

        for (int x = 0; x < RayTracing.WIDTH; x++) {
            Vector3 pixelColorSum = new Vector3();

            for (int sample = 0; sample < RayTracing.SAMPLES_PER_PIXEL; sample++) {
                float u = ((float) x + RandomUtil.randomFloat()) / (float) (RayTracing.WIDTH - 1);
                float v = ((float) y + RandomUtil.randomFloat()) / (float) (RayTracing.HEIGHT - 1);

                Ray ray = camera.getCameraRay(u, v);
                pixelColorSum = pixelColorSum.add(calculateRayColor(scene, ray, RayTracing.MAX_RAY_TRACE_DEPTH));
            }

            Vector3 pixelColor = pixelColorSum.mul(1.0f / (float) RayTracing.SAMPLES_PER_PIXEL);
            pixelsInRow[x] = pixelColor;
        }

        return pixelsInRow;
    }

    private static Vector3 calculateRayColor(Scene scene, Ray ray, int remainingDepth) {
        if (remainingDepth <= 0) {
            return new Vector3();
        }

        final HitRecord hitRecord = scene.rayHitsScene(ray);
        if (!hitRecord.hit()) {
            final float t = 0.5f * (ray.direction().getY() + 1.0f);
            return new Vector3(1.0f, 1.0f, 1.0f).mul(1.0f - t).add(new Vector3(0.5f, 0.7f, 1.0f).mul(t));
        }

        ScatterRecord scatterRecord = hitRecord.material().scatter(ray, hitRecord);
        if (scatterRecord.doesScatter()) {
            return scatterRecord.attenuation().mul(calculateRayColor(scene, scatterRecord.scatteredRay(), remainingDepth - 1));
        }

        return new Vector3();
    }
}
