package ui.layout.components;

import org.joml.Vector2i;

public class GUIGridCell {
    private Vector2i location;
    private boolean filled;

    public GUIGridCell(Vector2i location) {
        this.location = location;
    }

    public void setLocation(Vector2i location) {
        this.location = location;
    }

    public Vector2i getLocation() {
        return location;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
