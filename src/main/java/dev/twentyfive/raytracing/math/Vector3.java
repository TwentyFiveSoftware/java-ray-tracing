package dev.twentyfive.raytracing.math;

public class Vector3 {
    private final float x, y, z;

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    public float dot(Vector3 other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
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

}
