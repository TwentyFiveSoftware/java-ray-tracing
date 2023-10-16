package dev.twentyfive.raytracing.records;

import dev.twentyfive.raytracing.material.Material;
import dev.twentyfive.raytracing.math.Vector3;

public record HitRecord(boolean hit, float t, Vector3 point, Vector3 normal, boolean isFrontFace, Material material) {
    public static HitRecord noHit() {
        return new HitRecord(false, Float.MAX_VALUE, new Vector3(), new Vector3(), false, null);
    }
}
