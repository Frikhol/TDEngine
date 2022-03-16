package layout.components;

import org.joml.Vector4f;

public class Color {

    public final static Color white     = new Color(255, 255, 255);

    public final static Color WHITE = white;

    public final static Color lightGray = new Color(192, 192, 192);

    public final static Color LIGHT_GRAY = lightGray;

    public final static Color gray      = new Color(128, 128, 128);

    public final static Color GRAY = gray;

    public final static Color darkGray  = new Color(64, 64, 64);

    public final static Color DARK_GRAY = darkGray;

    public final static Color black     = new Color(0, 0, 0);

    public final static Color BLACK = black;

    public final static Color red       = new Color(255, 0, 0);

    public final static Color RED = red;

    public final static Color pink      = new Color(255, 175, 175);

    public final static Color PINK = pink;

    public final static Color orange    = new Color(255, 200, 0);

    public final static Color ORANGE = orange;

    public final static Color yellow    = new Color(255, 255, 0);

    public final static Color YELLOW = yellow;

    public final static Color green     = new Color(0, 255, 0);

    public final static Color GREEN = green;

    public final static Color magenta   = new Color(255, 0, 255);

    public final static Color MAGENTA = magenta;

    public final static Color cyan      = new Color(0, 255, 255);

    public final static Color CYAN = cyan;

    public final static Color blue      = new Color(0, 0, 255);

    public final static Color BLUE = blue;

    private float r;
    private float g;
    private float b;
    private float a;

    public Color(){
        this(0,0,0,255);
    }

    public Color(int r,int g, int b){
        this(r,g,b,255);
    }

    public Color(int r,int g, int b, int a){
        this.r = ((float)(((byte)Math.min(r,255))&0xFF))/255.0f;
        this.g = ((float)(((byte)Math.min(g,255))&0xFF))/255.0f;
        this.b = ((float)(((byte)Math.min(b,255))&0xFF))/255.0f;
        this.a = ((float)(((byte)Math.min(a,255))&0xFF))/255.0f;
    }

    public void setR(float r) {
        this.r = r;
    }

    public void setG(float g) {
        this.g = g;
    }

    public void setB(float b) {
        this.b = b;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getRed() {
        return r;
    }

    public float getGreen() {
        return g;
    }

    public float getBlue() {
        return b;
    }

    public float getAlpha() {
        return a;
    }

    public int getRedI() {
        return (int) Math.floor(r*255.0f);
    }

    public int getGreenI() {
        return (int) Math.floor(g*255.0f);
    }

    public int getBlueI() {
        return (int) Math.floor(b*255.0f);
    }

    public int getAlphaI() {
        return (int) Math.floor(a*255.0f);
    }

    public Vector4f toVector4f(){
        return new Vector4f(r,g,b,a);
    }
}
