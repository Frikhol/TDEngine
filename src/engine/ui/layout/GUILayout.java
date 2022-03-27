package ui.layout;

import ui.objects.GUIObject;

import java.util.ArrayList;
import java.util.List;

public abstract class GUILayout {

    private List<GUIObject> guiList;

    public GUILayout() {
        guiList = new ArrayList<>();
    }

    public void add(GUIObject guiObject) {
        guiList.add(guiObject);
    }

    public void add(GUIObject guiObject, int param, int param1, int param2, int param3) {

    }

    public void remove(GUIObject guiObject) {
        guiList.remove(guiObject);
    }

    public List<GUIObject> getGuiList() {
        return guiList;
    }


}
