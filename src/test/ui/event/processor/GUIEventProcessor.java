package ui.event.processor;

import ui.components.GUIComponent;
import ui.event.GUIEvent;
import ui.listener.GUIEventListener;

import static game.event.processor.GameEventProcessor.isPause;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@SuppressWarnings({"unchecked", "rawtypes"})
public class GUIEventProcessor {

    private static Queue<GUIEvent> first = new ConcurrentLinkedQueue<>();
    private static Queue<GUIEvent> second = new ConcurrentLinkedQueue<>();

    /**
     * processing GUI events
     */

    public static void processGUIEvents(){
        if(!isPause()){
            swap();
            for(GUIEvent event = second.poll(); event!=null; event = second.poll()){
                GUIComponent targetComponent = event.getTargetComponent();
                if(targetComponent==null)
                    return;
                List<? extends GUIEventListener> listeners = targetComponent.getListenerMap().getListeners(event.getClass());
                for(GUIEventListener listener : listeners)
                    listener.process(event);
            }
        }
    }

    /**
     * swapping current event processor buffers
     */

    private static void swap(){
        Queue<GUIEvent> temp = first;
        first = second;
        second = temp;
    }

    /**
     * Used to push event to event processor.
     *
     * @param event event to push to event processor.
     */

    public static void pushGUIEvent(GUIEvent event){
        first.add(event);
    }

    /**
     * Returns true if there are events that should be processed.
     *
     * @return true if there are events that should be processed.
     */

    public boolean hasEvents() {
        return !(first.isEmpty() && second.isEmpty());
    }

}
