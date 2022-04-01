package tools;

public abstract class Time {

    private static float deltaTime;
    private static float lastTime;

    public static float getDeltaTime() {
        return deltaTime;
    }

    public static void setDeltaTime(float deltaTime) {
        Time.deltaTime = deltaTime;
    }

    public static float getLastTime() {
        return lastTime;
    }

    public static void setLastTime(float lastTime) {
        Time.lastTime = lastTime;
    }
}
