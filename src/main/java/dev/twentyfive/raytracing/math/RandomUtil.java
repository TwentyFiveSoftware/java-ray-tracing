package dev.twentyfive.raytracing.math;

public class RandomUtil {

    private static int randomSeed = 25;

    public static float randomFloat() {
        randomSeed = (214013 * randomSeed + 2531011);
        return (float) ((randomSeed >> 16) & 0x7FFF) / 0x7FFF;
    }

    public static float randomFloat(float min, float max) {
        return randomFloat() * (max - min) + min;
    }

}
