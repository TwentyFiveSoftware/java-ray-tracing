package dev.twentyfive.raytracing.primitives;

import dev.twentyfive.raytracing.math.Vector3;

public class Sphere {
    private final Vector3 center;
    private final float radius;

    public Sphere(Vector3 center, float radius) {
        this.center = center;
        this.radius = radius;
    }
}
