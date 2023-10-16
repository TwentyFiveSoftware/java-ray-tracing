package dev.twentyfive.raytracing.scene;

import dev.twentyfive.raytracing.RayTracing;
import dev.twentyfive.raytracing.math.Random;
import dev.twentyfive.raytracing.math.Vector3;
import dev.twentyfive.raytracing.primitives.Ray;

public class Renderer {
    private final Camera camera;

    public Renderer(Camera camera) {
        this.camera = camera;
    }

    public Vector3[] render() {
        Vector3[] pixels = new Vector3[RayTracing.WIDTH * RayTracing.HEIGHT];

        for (int y = 0; y < RayTracing.HEIGHT; y++) {
            for (int x = 0; x < RayTracing.WIDTH; x++) {
                Vector3 pixelColorSum = new Vector3();

                for (int sample = 0; sample < RayTracing.SAMPLES_PER_PIXEL; sample++) {
                    float u = ((float) x + Random.randomFloat()) / (float) (RayTracing.WIDTH - 1);
                    float v = ((float) y + Random.randomFloat()) / (float) (RayTracing.HEIGHT - 1);

                    Ray ray = this.camera.getCameraRay(u, v);
                    pixelColorSum = pixelColorSum.add(calculateRayColor(ray));
                }

                Vector3 pixelColor = pixelColorSum.mul(1.0f / (float) RayTracing.SAMPLES_PER_PIXEL);
                pixels[y * RayTracing.WIDTH + x] = pixelColor;
            }
        }

        return pixels;
    }

    private Vector3 calculateRayColor(Ray ray) {
        return new Vector3();
    }
}
