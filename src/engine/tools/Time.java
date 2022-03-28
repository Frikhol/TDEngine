package tools;

public abstract class Time {

    private static double deltaTime;
    private static double lastTime;

    public static double getDeltaTime() {
        return deltaTime;
    }

    public static void setDeltaTime(double deltaTime) {
        Time.deltaTime = deltaTime;
    }

    public static double getLastTime() {
        return lastTime;
    }

    public static void setLastTime(double lastTime) {
        Time.lastTime = lastTime;
    }
}
