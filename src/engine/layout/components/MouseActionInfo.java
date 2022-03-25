package layout.components;

import entities.GameObject;
import layout.GUIObject;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;

public class MouseActionInfo {
    private int button = -1;
    private int mods = 0;
    private Vector2i guiPressedPosition = null;
    private Vector2i guiReleasedPosition = null;
    private Vector2f worldPressedPosition = null;
    private Vector2f worldReleasedPosition = null;
    private GUIObject guiPressedObject = null;
    private GameObject worldPressedObject = null;

}
