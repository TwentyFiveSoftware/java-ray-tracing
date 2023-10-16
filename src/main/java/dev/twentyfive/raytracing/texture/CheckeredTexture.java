package dev.twentyfive.raytracing.texture;

import dev.twentyfive.raytracing.math.Vector3;

public record CheckeredTexture(Vector3 albedo1, Vector3 albedo2) implements Texture {
    @Override
    public Vector3 getColorAt(Vector3 point) {
        final float size = 6.0f;
        final float sines = (float) Math.sin(size * point.getX()) *
            (float) Math.sin(size * point.getY()) * (float) Math.sin(size * point.getZ());

        return sines < 0.0f ? albedo1 : albedo2;
    }
}
