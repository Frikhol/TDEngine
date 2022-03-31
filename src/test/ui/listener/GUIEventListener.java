package ui.listener;

import ui.event.GUIEvent;

/**
 * The base event listener interface. Used to handle event.
 */
public interface GUIEventListener<E extends GUIEvent> {

    /**
     * Used to handle specific event.
     *
     * @param event event to handle.
     */
    void process(E event);
}
