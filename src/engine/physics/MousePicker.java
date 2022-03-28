package physics;

import core.Renderer;
import entities.Camera;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import tools.Maths;

import static display.GameDisplay.*;
import static core.GameEngine.*;

public class MousePicker {

    private static final int RECURSION_COUNT = 200;
    private static final float RAY_RANGE = 600;

    private static Vector3f currentRay;

    private static Matrix4f projectionMatrix;
    private static Matrix4f viewMatrix;
    private static Camera camera;

    private static Vector3f currentPoint;

    public MousePicker() {
        projectionMatrix = Renderer.getProjectionMatrix();
        camera = getCurrentScene().getCamera();
        viewMatrix = Maths.createViewMatrix(camera);
    }

    public static Vector3f getCurrentRay() {
        return currentRay;
    }

    public static Vector3f getCurrentPoint() {
        return currentPoint;
    }

    public static void update(){
        viewMatrix = Maths.createViewMatrix(camera);
        currentRay = calculateMouseRay();
        //System.out.println("x "+currentRay.x+" y "+currentRay.y+" z:"+currentRay.z);
        if (intersectionInRange(0, RAY_RANGE, currentRay)) {
            currentPoint = binarySearch(0, 0, RAY_RANGE, currentRay);
        } else {
            currentPoint = null;
        }
    }

    private static Vector3f calculateMouseRay() {
        Vector2f normalizedCoords = getNormolizedDeviceCoords();
        Vector4f clipCoords = new Vector4f(normalizedCoords.x,normalizedCoords.y,-1f,1f);
        Vector4f eyeCoords = toEyeCoords(clipCoords);
        return toWorldCoords(eyeCoords);
    }

    private static Vector3f toWorldCoords(Vector4f eyeCoords){
        Matrix4f invertedView = new Matrix4f(viewMatrix).invert();
        Vector4f rayWorld = new Matrix4f(invertedView).transform(new Vector4f(eyeCoords));
        return new Vector3f(rayWorld.x,rayWorld.y,rayWorld.z).normalize();
    }

    private static Vector4f toEyeCoords(Vector4f clipCoords){
        Matrix4f invertedProjection = new Matrix4f(projectionMatrix).invert();
        Vector4f eyeCoords = invertedProjection.transform(new Vector4f(clipCoords));
        return new Vector4f(eyeCoords.x,eyeCoords.y,-1f,0f);
    }

    private static Vector2f getNormolizedDeviceCoords() {
        return new Vector2f((float) (2f*getCursorX()/getDisplayWIDTH()[0]-1f), (float) (1f-(2f*getCursorY())/getDisplayHEIGHT()[0]));
    }

    private static Vector3f getPointOnRay(Vector3f ray, float distance) {
        Vector3f camPos = camera.getTransform().getPosition();
        Vector3f start = new Vector3f(camPos.x, camPos.y, camPos.z);
        Vector3f scaledRay = new Vector3f(ray.x * distance, ray.y * distance, ray.z * distance);
        return new Vector3f(start).add(new Vector3f(scaledRay));
    }

    private static Vector3f binarySearch(int count, float start, float finish, Vector3f ray) {
        float half = start + ((finish - start) / 2f);
        if (count >= RECURSION_COUNT) {
            return getPointOnRay(ray, half);
        }
        if (intersectionInRange(start, half, ray)) {
            return binarySearch(count + 1, start, half, ray);
        } else {
            return binarySearch(count + 1, half, finish, ray);
        }
    }

    private static boolean intersectionInRange(float start, float finish, Vector3f ray) {
        Vector3f startPoint = getPointOnRay(ray, start);
        Vector3f endPoint = getPointOnRay(ray, finish);
        return !isUnderGround(startPoint) && isUnderGround(endPoint);
    }

    private static boolean isUnderGround(Vector3f testPoint) {
        return testPoint.y < 0;
    }
}
