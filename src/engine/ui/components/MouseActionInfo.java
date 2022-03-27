package ui.components;

import entities.GameObject;
import ui.objects.GUIObject;
import org.joml.Vector2f;
import org.joml.Vector2i;

public class MouseActionInfo {
    private int button = -1;
    private int mode = 0;
    private Vector2i guiPressedPosition = null;
    private Vector2i guiReleasedPosition = null;
    private Vector2f worldPressedPosition = null;
    private Vector2f worldReleasedPosition = null;
    private GUIObject guiPressedObject = null;
    private GameObject worldPressedObject = null;


}
