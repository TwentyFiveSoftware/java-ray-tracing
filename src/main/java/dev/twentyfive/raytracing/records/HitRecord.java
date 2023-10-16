package dev.twentyfive.raytracing.records;

import dev.twentyfive.raytracing.material.Material;
import dev.twentyfive.raytracing.math.Vector3;

public record HitRecord(float t, Vector3 point, Vector3 normal, boolean isFrontFace, Material material) {
}
