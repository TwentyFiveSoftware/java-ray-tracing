package dev.twentyfive.raytracing.records;

import dev.twentyfive.raytracing.math.Vector3;
import dev.twentyfive.raytracing.primitives.Ray;

public record ScatterRecord(Vector3 attenuation, Ray scatteredRay) {
}
