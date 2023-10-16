package dev.twentyfive.raytracing.primitives;

import dev.twentyfive.raytracing.math.Vector3;

public class Ray {
    private final Vector3 origin;
    private final Vector3 direction;

    public Ray(Vector3 origin, Vector3 direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Vector3 at(float t) {
        return this.origin.add(this.direction.mul(t));
    }

    public Vector3 getDirection() {
        return direction;
    }
}
