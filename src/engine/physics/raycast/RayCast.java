package physics.raycast;

import core.render.Renderer;
import core.entities.Camera;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import tools.Maths;

import static core.display.GameDisplay.*;
import static core.GameEngine.*;

public class RayCast {

    private static boolean pointingInWorld;

    private static Vector3f currentRay;

    private static Matrix4f projectionMatrix;
    private static Matrix4f viewMatrix;
    private static Camera camera;

    public RayCast() {
        projectionMatrix = Renderer.getProjectionMatrix();
        camera = getCurrentScene().getCamera();
        viewMatrix = Maths.createViewMatrix(camera);
    }

    public static boolean isPointingInWorld() {
        return pointingInWorld;
    }

    public static void setPointingInWorld(boolean pointingInWorld) {
        RayCast.pointingInWorld = pointingInWorld;
    }

    public static Vector3f getCurrentRay() {
        return currentRay;
    }

    public static void update(){
        viewMatrix = Maths.createViewMatrix(camera);
        currentRay = calculateMouseRay();
        projectionMatrix = Renderer.getProjectionMatrix();
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
}
