package dev.twentyfive.raytracing.primitives;

import dev.twentyfive.raytracing.math.Vector3;

public record Ray(Vector3 origin, Vector3 direction) {
    public Vector3 at(float t) {
        return this.origin.add(this.direction.mul(t));
    }
}
