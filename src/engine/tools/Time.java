package tools;

public abstract class Time {

    /** @param deltaTime in seconds time amount between two frames
     * @param lastTime in seconds time amount at the start of last frame
     *
     * */

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
