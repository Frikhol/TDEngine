package data.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import entities.GameObject;
import entities.Scene;
import layout.GUIObject;

import java.io.IOException;

public class SceneSerializer extends StdSerializer<Scene> {

    public SceneSerializer() {
        this(null);
    }

    public SceneSerializer(Class t) {
        super(t);
    }

    @Override
    public void serialize(Scene scene, JsonGenerator jGen, SerializerProvider serializerProvider) throws IOException {
        jGen.writeStartObject();
        jGen.writeStringField("name",scene.getName());
        jGen.writeArrayFieldStart("gameObjectList");
        for(GameObject gameObject : scene.getGameObjectList())
            jGen.writeObject(gameObject);
        jGen.writeEndArray();
        jGen.writeObjectFieldStart("currentGUI");
        jGen.writeArrayFieldStart("guiList");
        for(GUIObject guiObject : scene.getCurrentGUI().getGuiList())
            jGen.writeObject(guiObject);
        jGen.writeEndArray();
        jGen.writeEndObject();
        jGen.writeObjectFieldStart("light");
            jGen.writeObjectFieldStart("position");
                jGen.writeNumberField("x",scene.getLight().getPosition().x);
                jGen.writeNumberField("y",scene.getLight().getPosition().y);
                jGen.writeNumberField("z",scene.getLight().getPosition().z);
            jGen.writeEndObject();
            jGen.writeObjectFieldStart("colour");
                jGen.writeNumberField("x",scene.getLight().getColour().x);
                jGen.writeNumberField("y",scene.getLight().getColour().y);
                jGen.writeNumberField("z",scene.getLight().getColour().z);
            jGen.writeEndObject();
        jGen.writeEndObject();
        jGen.writeObjectFieldStart("camera");
            jGen.writeObjectField("transform",scene.getCamera().getTransform());
            jGen.writeNumberField("pitch",scene.getCamera().getPitch());
            jGen.writeNumberField("yaw",scene.getCamera().getYaw());
            jGen.writeNumberField("roll",scene.getCamera().getRoll());
        jGen.writeEndObject();
        jGen.writeEndObject();
    }
}
