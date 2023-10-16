package dev.twentyfive.raytracing.image;

import dev.twentyfive.raytracing.math.RandomUtil;
import dev.twentyfive.raytracing.math.Vector3;

public class ColorUtil {

    public static Vector3 getRandomColor() {
        return hsvToRgb(RandomUtil.randomFloat(0.0f, 360.0f), 0.75f, 0.45f);
    }

    private static Vector3 hsvToRgb(float hue, float s, float v) {
        final float h = hue / 60.0f;
        final float fraction = (float) (h - Math.floor(h));

        final float p = v * (1.0f - s);
        final float q = v * (1.0f - s * fraction);
        final float t = v * (1.0f - s * (1.0f - fraction));

        if (0 <= h && h < 1) return new Vector3(v, t, p);
        else if (1 <= h && h < 2) return new Vector3(q, v, p);
        else if (2 <= h && h < 3) return new Vector3(p, v, t);
        else if (3 <= h && h < 4) return new Vector3(p, q, v);
        else if (4 <= h && h < 5) return new Vector3(t, p, v);
        else if (5 <= h && h < 6) return new Vector3(v, p, q);

        return new Vector3();
    }

}
