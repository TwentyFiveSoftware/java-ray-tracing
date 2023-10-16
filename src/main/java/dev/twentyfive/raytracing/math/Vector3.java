package dev.twentyfive.raytracing.math;

public class Vector3 {
    private final float x, y, z;

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public static Vector3 randomUnitVector() {
        while (true) {
            final Vector3 point = new Vector3(Random.randomFloat(-1.0f, 1.0f), Random.randomFloat(-1.0f, 1.0f), Random.randomFloat(-1.0f, 1.0f));
            if (point.lengthSquared() < 1.0f) {
                return point.normalized();
            }
        }
    }

    public Vector3 add(Vector3 other) {
        return new Vector3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vector3 sub(Vector3 other) {
        return new Vector3(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public Vector3 neg() {
        return new Vector3(-this.x, -this.y, -this.z);
    }

    public Vector3 mul(float scalar) {
        return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public Vector3 mul(Vector3 other) {
        return new Vector3(this.x * other.x, this.y * other.y, this.z * other.z);
    }

    public Vector3 cross(Vector3 other) {
        return new Vector3(
            this.y * other.z - this.z * other.y,
            this.z * other.x - this.x * other.z,
            this.x * other.y - this.y * other.x
        );
    }

    public float dot(Vector3 other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public Vector3 sqrt() {
        return new Vector3((float) Math.sqrt(this.x), (float) Math.sqrt(this.y), (float) Math.sqrt(this.z));
    }

    public float lengthSquared() {
        return this.dot(this);
    }

    public float length() {
        return (float) Math.sqrt(this.lengthSquared());
    }

    public Vector3 normalized() {
        float length = this.length();
        if (length == 0) {
            return this;
        }

        return this.mul(1.0f / length);
    }

    public boolean isNearZero() {
        final float epsilon = 1e-8f;
        return Math.abs(this.x) < epsilon && Math.abs(this.y) < epsilon && Math.abs(this.z) < epsilon;
    }

    public Vector3 reflect(Vector3 normal) {
        return this.sub(normal.mul(2.0f * this.dot(normal)));
    }

    public Vector3 refract(Vector3 normal, float refractionRatio) {
        final float cosTheta = Math.min(normal.dot(this.neg()), 1.0f);
        final float sinTheta = (float) Math.sqrt(1.0f - cosTheta * cosTheta);

        final float r0 = (1.0f - refractionRatio) / (1.0f + refractionRatio);
        final float reflectance = (float) (r0 * r0 + (1.0f - r0 * r0) * Math.pow(1.0f - cosTheta, 5));

        if (refractionRatio * sinTheta > 1.0f || reflectance > Random.randomFloat()) {
            return this.reflect(normal);
        }

        final Vector3 rOutPerpendicular = this.add(normal.mul(cosTheta)).mul(refractionRatio);
        final Vector3 rOutParallel = normal.mul((float) -Math.sqrt(1.0f - rOutPerpendicular.lengthSquared()));
        return rOutPerpendicular.add(rOutParallel);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }
}
