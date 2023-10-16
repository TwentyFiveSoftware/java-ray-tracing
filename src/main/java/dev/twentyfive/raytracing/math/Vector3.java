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
