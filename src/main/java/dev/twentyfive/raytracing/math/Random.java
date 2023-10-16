package dev.twentyfive.raytracing.math;

public class Random {

    private static final java.util.Random random = new java.util.Random();

    public static float randomFloat() {
        return random.nextFloat();
    }

}
