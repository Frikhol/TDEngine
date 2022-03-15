package entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.joml.Vector3f;

public class Light {
    @JsonIgnoreProperties({"finite"})
    private Vector3f position;
    @JsonIgnoreProperties({"finite"})
    private Vector3f colour;

    public Light() {
    }

    public Light(Vector3f position, Vector3f colour) {
        this.position = position;
        this.colour = colour;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getColour() {
        return colour;
    }

    public void setColour(Vector3f colour) {
        this.colour = colour;
    }
}
