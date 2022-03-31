package ui.event;

import ui.components.GUIComponent;
import ui.components.GUIFrame;

public class GUIEvent<T extends GUIComponent> {

    private final T targetComponent;
    private final GUIFrame frame;

    public GUIEvent(T targetComponent, GUIFrame frame){
        this.targetComponent = targetComponent;
        this.frame = frame;
    }

    public T getTargetComponent() {
        return targetComponent;
    }

    public GUIFrame getFrame() {
        return frame;
    }
}
