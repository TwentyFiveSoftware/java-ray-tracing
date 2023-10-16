package dev.twentyfive.raytracing.material;

import dev.twentyfive.raytracing.math.Vector3;
import dev.twentyfive.raytracing.primitives.Ray;
import dev.twentyfive.raytracing.records.HitRecord;
import dev.twentyfive.raytracing.records.ScatterRecord;
import dev.twentyfive.raytracing.texture.Texture;

public record DiffuseMaterial(Texture texture) implements Material {
    @Override
    public ScatterRecord scatter(Ray ray, HitRecord hitRecord) {
        Vector3 scatterDirection = hitRecord.normal().add(Vector3.randomUnitVector()).normalized();
        if (scatterDirection.isNearZero()) {
            scatterDirection = hitRecord.normal();
        }

        return new ScatterRecord(true, this.texture.getColorAt(hitRecord.point()), new Ray(hitRecord.point(), scatterDirection));
    }
}
