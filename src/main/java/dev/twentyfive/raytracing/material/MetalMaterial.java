package dev.twentyfive.raytracing.material;

import dev.twentyfive.raytracing.math.Vector3;
import dev.twentyfive.raytracing.primitives.Ray;
import dev.twentyfive.raytracing.records.HitRecord;
import dev.twentyfive.raytracing.records.ScatterRecord;
import dev.twentyfive.raytracing.texture.Texture;

public record MetalMaterial(Texture texture) implements Material {
    @Override
    public ScatterRecord scatter(Ray ray, HitRecord hitRecord) {
        final Vector3 scatterDirection = ray.direction().reflect(hitRecord.normal());

        if (scatterDirection.dot(hitRecord.normal()) <= 0.0f) {
            return null;
        }

        return new ScatterRecord(this.texture.getColorAt(hitRecord.point()), new Ray(hitRecord.point(), scatterDirection));
    }
}
