package dev.twentyfive.raytracing.material;

import dev.twentyfive.raytracing.primitives.Ray;
import dev.twentyfive.raytracing.records.HitRecord;
import dev.twentyfive.raytracing.records.ScatterRecord;

public interface Material {
    ScatterRecord scatter(Ray ray, HitRecord hitRecord);
}
