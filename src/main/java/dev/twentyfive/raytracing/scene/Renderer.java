package dev.twentyfive.raytracing.scene;

import dev.twentyfive.raytracing.RayTracing;
import dev.twentyfive.raytracing.math.RandomUtil;
import dev.twentyfive.raytracing.math.Vector3;
import dev.twentyfive.raytracing.primitives.Ray;
import dev.twentyfive.raytracing.records.HitRecord;
import dev.twentyfive.raytracing.records.ScatterRecord;

public class Renderer {
    private final Camera camera;
    private final Scene scene;

    public Renderer(Camera camera, Scene scene) {
        this.camera = camera;
        this.scene = scene;
    }

    public Vector3[] render() {
        Vector3[] pixels = new Vector3[RayTracing.WIDTH * RayTracing.HEIGHT];

        for (int y = 0; y < RayTracing.HEIGHT; y++) {
            System.out.println((y + 1) + " / " + RayTracing.HEIGHT);

            for (int x = 0; x < RayTracing.WIDTH; x++) {
                Vector3 pixelColorSum = new Vector3();

                for (int sample = 0; sample < RayTracing.SAMPLES_PER_PIXEL; sample++) {
                    float u = ((float) x + RandomUtil.randomFloat()) / (float) (RayTracing.WIDTH - 1);
                    float v = ((float) y + RandomUtil.randomFloat()) / (float) (RayTracing.HEIGHT - 1);

                    Ray ray = this.camera.getCameraRay(u, v);
                    pixelColorSum = pixelColorSum.add(calculateRayColor(ray, RayTracing.MAX_RAY_TRACE_DEPTH));
                }

                Vector3 pixelColor = pixelColorSum.mul(1.0f / (float) RayTracing.SAMPLES_PER_PIXEL);
                pixels[y * RayTracing.WIDTH + x] = pixelColor;
            }
        }

        return pixels;
    }

    private Vector3 calculateRayColor(Ray ray, int remainingDepth) {
        if (remainingDepth <= 0) {
            return new Vector3();
        }

        final HitRecord hitRecord = this.scene.rayHitsScene(ray);
        if (!hitRecord.hit()) {
            final float t = 0.5f * (ray.direction().getY() + 1.0f);
            return new Vector3(1.0f, 1.0f, 1.0f).mul(1.0f - t).add(new Vector3(0.5f, 0.7f, 1.0f).mul(t));
        }

        ScatterRecord scatterRecord = hitRecord.material().scatter(ray, hitRecord);
        if (scatterRecord.doesScatter()) {
            return scatterRecord.attenuation().mul(calculateRayColor(scatterRecord.scatteredRay(), remainingDepth - 1));
        }

        return new Vector3();
    }
}
