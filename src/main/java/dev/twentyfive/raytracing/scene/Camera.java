package dev.twentyfive.raytracing.scene;

import dev.twentyfive.raytracing.RayTracing;
import dev.twentyfive.raytracing.math.Vector3;
import dev.twentyfive.raytracing.primitives.Ray;

public class Camera {
    private final Vector3 lookFrom;
    private final Vector3 upperLeftCorner;
    private final Vector3 horizontalDirection;
    private final Vector3 verticalDirection;

    public Camera(Vector3 lookFrom, Vector3 lookAt, float fov) {
        final float aspectRatio = (float) RayTracing.WIDTH / (float) RayTracing.HEIGHT;
        final float viewportHeight = (float) (Math.tan(fov / 360.0f * Math.PI) * 2.0f);
        final float viewportWidth = viewportHeight * aspectRatio;

        final Vector3 forward = lookAt.sub(lookFrom).normalized();
        final Vector3 right = new Vector3(0, 1, 0).cross(forward).normalized();
        final Vector3 up = forward.cross(right).normalized();

        final Vector3 horizontalDirection = right.mul(viewportWidth);
        final Vector3 verticalDirection = up.mul(viewportHeight);

        final Vector3 upperLeftCorner = lookFrom
            .sub(horizontalDirection.mul(0.5f))
            .add(verticalDirection.mul(0.5f))
            .add(forward);

        this.lookFrom = lookFrom;
        this.upperLeftCorner = upperLeftCorner;
        this.horizontalDirection = horizontalDirection;
        this.verticalDirection = verticalDirection;
    }

    public Ray getCameraRay(float u, float v) {
        final Vector3 target = upperLeftCorner
            .add(this.horizontalDirection.mul(u))
            .sub(this.verticalDirection.mul(v));

        return new Ray(this.lookFrom, target.sub(this.lookFrom).normalized());
    }
}
