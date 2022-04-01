package ui.components;

import ui.listener.GUIListenerMap;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class GUIComponent implements Serializable {
    /**
     * Metadata map, place where renderers or event processors can store state of component.
     */
    private final Map<String, Object> metadata = new HashMap<>();
    /**
     * List of child components.
     */
    private final List<GUIComponent> childComponents = new CopyOnWriteArrayList<>();
    /**
     * Parent component container. For root components it could be null.
     */
    private GUIComponent parent;
    /**
     * Map for UI event listeners.
     */
    private GUIListenerMap GUIListenerMap = new GUIListenerMap();
    /**
     * Returns event listeners for component instance.
     *
     * @return event listeners map.
     */
    public GUIListenerMap getListenerMap() {
        return GUIListenerMap;
    }
}
