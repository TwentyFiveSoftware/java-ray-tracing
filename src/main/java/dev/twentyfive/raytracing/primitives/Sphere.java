package dev.twentyfive.raytracing.primitives;

import dev.twentyfive.raytracing.material.Material;
import dev.twentyfive.raytracing.math.Vector3;
import dev.twentyfive.raytracing.records.HitRecord;

public record Sphere(Vector3 center, float radius, Material material) {
    public HitRecord rayHitsSphere(Ray ray, float tMin, float tMax) {
        final Vector3 oc = ray.origin().sub(this.center);
        final float a = ray.direction().lengthSquared();
        final float halfB = oc.dot(ray.direction());
        final float c = oc.lengthSquared() - this.radius * this.radius;
        final float discriminant = halfB * halfB - a * c;

        if (discriminant < 0.0f) {
            return HitRecord.noHit();
        }

        final float sqrtD = (float) Math.sqrt(discriminant);

        float t = (-halfB - sqrtD) / a;
        if (t < tMin || t > tMax) {
            t = (-halfB + sqrtD) / a;

            if (t < tMin || t > tMax) {
                return HitRecord.noHit();
            }
        }

        final Vector3 point = ray.at(t);
        final Vector3 normal = point.sub(this.center).mul(1.0f / this.radius);
        final boolean isFrontFace = ray.direction().dot(normal) < 0.0f;

        return new HitRecord(true, t, point, isFrontFace ? normal : normal.neg(), isFrontFace, this.material);
    }
}
