package dev.twentyfive.raytracing.scene;

import dev.twentyfive.raytracing.material.DiffuseMaterial;
import dev.twentyfive.raytracing.math.Vector3;
import dev.twentyfive.raytracing.primitives.Ray;
import dev.twentyfive.raytracing.primitives.Sphere;
import dev.twentyfive.raytracing.records.HitRecord;

import java.util.ArrayList;

public class Scene {
    private final ArrayList<Sphere> spheres;

    public Scene(ArrayList<Sphere> spheres) {
        this.spheres = spheres;
    }

    public static Scene generateRandomScene() {
        final ArrayList<Sphere> spheres = new ArrayList<>();

        spheres.add(new Sphere(new Vector3(), 1.0f, new DiffuseMaterial()));

        return new Scene(spheres);
    }

    public HitRecord rayHitsScene(Ray ray) {
        HitRecord currentHitRecord = new HitRecord();

        for (final Sphere sphere : this.spheres) {
            final HitRecord hitRecord = sphere.rayHitsSphere(ray, 0.001f, currentHitRecord.t());
            if (hitRecord.hit()) {
                currentHitRecord = hitRecord;
            }
        }

        return currentHitRecord;
    }
}
