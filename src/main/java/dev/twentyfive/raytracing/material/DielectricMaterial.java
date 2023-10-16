package dev.twentyfive.raytracing.material;

import dev.twentyfive.raytracing.math.Vector3;
import dev.twentyfive.raytracing.primitives.Ray;
import dev.twentyfive.raytracing.records.HitRecord;
import dev.twentyfive.raytracing.records.ScatterRecord;
import dev.twentyfive.raytracing.texture.Texture;

public record DielectricMaterial(Texture texture, float refractionIndex) implements Material {
    @Override
    public ScatterRecord scatter(Ray ray, HitRecord hitRecord) {
        final float refractionRatio = hitRecord.isFrontFace() ? (1.0f / this.refractionIndex) : this.refractionIndex;
        final Vector3 scatterDirection = ray.direction().refract(hitRecord.normal(), refractionRatio);

        return new ScatterRecord(true, new Vector3(1.0f, 1.0f, 1.0f), new Ray(hitRecord.point(), scatterDirection));
    }
}