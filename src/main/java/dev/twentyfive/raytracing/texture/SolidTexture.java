package dev.twentyfive.raytracing.texture;

import dev.twentyfive.raytracing.math.Vector3;

public record SolidTexture(Vector3 albedo) implements Texture {
    @Override
    public Vector3 getColorAt(Vector3 point) {
        return albedo;
    }
}
