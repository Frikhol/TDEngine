package ui.listener;

import ui.event.GUIEvent;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Used to hold event listeners.
 */

@SuppressWarnings({"unchecked"})
public class GUIListenerMap {

    private final Lock lock = new ReentrantLock();
    private Map<Class<? extends GUIEvent>, List<? extends GUIEventListener>> listenerMap = new ConcurrentHashMap<>();


    /**
     * Used to add event listener for specified event type.
     *
     * @param eventClass event class.
     * @param listener   listener to add for specified event.
     * @param <E>        event type.
     */
    public <E extends GUIEvent> void addListener(Class<E> eventClass, GUIEventListener<E> listener) {
        getListeners(eventClass).add(listener);
    }

    /**
     * Returns event listeners for specified event type.
     *
     * @param eventClass event class for which registered listeners.
     * @param <E>        event type.
     * @return event listeners for specified event type.
     */
    public <E extends GUIEvent> List<GUIEventListener<E>> getListeners(Class<E> eventClass) {
        lock.lock();
        List<GUIEventListener<E>> eventListeners = (List<GUIEventListener<E>>) listenerMap.get(eventClass);
        if (eventListeners == null) {
            listenerMap.put(eventClass, eventListeners = new CopyOnWriteArrayList<>());
        }
        lock.unlock();
        return eventListeners;
    }
    /**
     * Used to remove specified event listener.
     *
     * @param eventClass event class.
     * @param listener   listener to remove.
     * @param <E>        event type.
     */
    public <E extends GUIEvent> void removeListener(Class<E> eventClass, GUIEventListener<E> listener) {
        getListeners(eventClass).remove(listener);
    }

    /**
     * Used to remove all listeners for specified event type.
     *
     * @param eventClass event class.
     * @param <E>        event type.
     */
    public <E extends GUIEvent> void removeAllListeners(Class<E> eventClass) {
        listenerMap.remove(eventClass);
    }
}
