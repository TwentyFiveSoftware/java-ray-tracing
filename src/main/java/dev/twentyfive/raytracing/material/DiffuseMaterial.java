package dev.twentyfive.raytracing.material;

import dev.twentyfive.raytracing.math.Vector3;
import dev.twentyfive.raytracing.primitives.Ray;
import dev.twentyfive.raytracing.records.HitRecord;
import dev.twentyfive.raytracing.records.ScatterRecord;

public class DiffuseMaterial implements Material {
    @Override
    public ScatterRecord scatter(Ray ray, HitRecord hitRecord) {
        return new ScatterRecord(true, new Vector3(0.9f, 0.9f, 0.9f), new Ray(hitRecord.point(), hitRecord.normal()));
    }
}
