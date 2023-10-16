package dev.twentyfive.raytracing.texture;

import dev.twentyfive.raytracing.math.Vector3;

public interface Texture {
    Vector3 getColorAt(Vector3 point);
}
