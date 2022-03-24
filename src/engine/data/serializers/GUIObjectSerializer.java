package data.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import layout.GUI;
import layout.GUIObject;
import org.joml.Vector2f;

import java.io.IOException;

public class GUIObjectSerializer extends StdSerializer<GUIObject> {

    public GUIObjectSerializer() {
        this(null);
    }

    public GUIObjectSerializer(Class<GUIObject> t) {
        super(t);
    }

    @Override
    public void serialize(GUIObject guiObject, JsonGenerator jGen, SerializerProvider serializerProvider) throws IOException {
        jGen.writeStartObject();
            jGen.writeStringField("guiClass",guiObject.getClass().getName());
            jGen.writeObjectFieldStart("position");
                jGen.writeNumberField("x",guiObject.getPosition().x);
                jGen.writeNumberField("y",guiObject.getPosition().y);
            jGen.writeEndObject();
            jGen.writeObjectFieldStart("scale");
                jGen.writeNumberField("x",guiObject.getScale().x);
                jGen.writeNumberField("y",guiObject.getScale().y);
            jGen.writeEndObject();
            jGen.writeObjectFieldStart("textureColor");
                jGen.writeNumberField("r",guiObject.getTexture().getColor().getRed());
                jGen.writeNumberField("g",guiObject.getTexture().getColor().getGreen());
                jGen.writeNumberField("b",guiObject.getTexture().getColor().getBlue());
                jGen.writeNumberField("a",guiObject.getTexture().getColor().getAlpha());
            jGen.writeEndObject();
            /*if(guiObject.getText() != null) {
                jGen.writeObjectFieldStart("text");
                jGen.writeStringField("text", guiObject.getTextString());
                jGen.writeNumberField("fontSize", guiObject.getText().getFontSize());
                jGen.writeStringField("fontType", guiObject.getText().getFontName());
                jGen.writeObjectFieldStart("position");
                Vector2f screenPos = GUI.getScreenPosition(guiObject.getPosition().x, guiObject.getPosition().y);
                jGen.writeNumberField("x", screenPos.x - guiObject.getScale().x / 2f);
                jGen.writeNumberField("y", screenPos.y);
                jGen.writeEndObject();
                jGen.writeNumberField("maxLineSize", guiObject.getText().getMaxLineSize());
                jGen.writeBooleanField("centerText", guiObject.getText().isCentered());
                jGen.writeEndObject();
            }*/
        jGen.writeEndObject();
    }
}
