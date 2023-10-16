package dev.twentyfive.raytracing.scene;

import dev.twentyfive.raytracing.image.ColorUtil;
import dev.twentyfive.raytracing.material.DielectricMaterial;
import dev.twentyfive.raytracing.material.DiffuseMaterial;
import dev.twentyfive.raytracing.material.Material;
import dev.twentyfive.raytracing.material.MetalMaterial;
import dev.twentyfive.raytracing.math.RandomUtil;
import dev.twentyfive.raytracing.math.Vector3;
import dev.twentyfive.raytracing.primitives.Ray;
import dev.twentyfive.raytracing.primitives.Sphere;
import dev.twentyfive.raytracing.records.HitRecord;
import dev.twentyfive.raytracing.texture.CheckeredTexture;
import dev.twentyfive.raytracing.texture.SolidTexture;

import java.util.ArrayList;

public class Scene {
    private final Sphere[] spheres;

    public Scene(ArrayList<Sphere> spheres) {
        Sphere[] sphereArray = new Sphere[spheres.size()];
        for (int i = 0; i < spheres.size(); i++) {
            sphereArray[i] = spheres.get(i);
        }

        this.spheres = sphereArray;
    }

    public static Scene generateRandomScene() {
        final ArrayList<Sphere> spheres = new ArrayList<>();

        for (int x = -11; x < 11; x++) {
            for (int z = -11; z < 11; z++) {
                Material material;

                final float materialRandom = RandomUtil.randomFloat();
                if (materialRandom < 0.8f) {
                    material = new DiffuseMaterial(new SolidTexture(ColorUtil.getRandomColor()));
                } else if (materialRandom < 0.95f) {
                    material = new MetalMaterial(new SolidTexture(ColorUtil.getRandomColor()));
                } else {
                    material = new DielectricMaterial(1.5f);
                }

                final Vector3 center = new Vector3(
                    (float) x + RandomUtil.randomFloat(0.0f, 0.9f),
                    0.2f,
                    (float) z + RandomUtil.randomFloat(0.0f, 0.9f)
                );
                spheres.add(new Sphere(center, 0.2f, material));
            }
        }

        // GROUND
        spheres.add(new Sphere(
            new Vector3(0.0f, -1000.0f, 0.0f),
            1000.0f,
            new DiffuseMaterial(new CheckeredTexture(new Vector3(0.05f, 0.05f, 0.05f), new Vector3(0.95f, 0.95f, 0.95f))))
        );

        // LEFT
        spheres.add(new Sphere(
            new Vector3(-4.0f, 1.0f, 0.0f), 1.0f, new DiffuseMaterial(new SolidTexture(new Vector3(0.6f, 0.3f, 0.1f)))
        ));

        // CENTER
        spheres.add(new Sphere(new Vector3(0.0f, 1.0f, 0.0f), 1.0f, new DielectricMaterial(1.5f)));

        // RIGHT
        spheres.add(new Sphere(
            new Vector3(4.0f, 1.0f, 0.0f), 1.0f, new MetalMaterial(new SolidTexture(new Vector3(0.7f, 0.6f, 0.5f)))
        ));

        return new Scene(spheres);
    }

    public HitRecord rayHitsScene(Ray ray) {
        HitRecord currentHitRecord = null;
        float maxT = Float.MAX_VALUE;

        for (final Sphere sphere : this.spheres) {
            final HitRecord hitRecord = sphere.rayHitsSphere(ray, 0.001f, maxT);
            if (hitRecord != null) {
                currentHitRecord = hitRecord;
                maxT = hitRecord.t();
            }
        }

        return currentHitRecord;
    }
}
