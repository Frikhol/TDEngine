package data.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import font.GUIText;
import font.TextMaster;
import layout.GUIObject;
import layout.components.Color;
import org.joml.Vector2f;

import java.io.IOException;

public class GUIObjectDeserializer extends StdDeserializer<GUIObject> {

    public GUIObjectDeserializer() {
        this(null);
    }

    public GUIObjectDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public GUIObject deserialize(JsonParser jsonParser, DeserializationContext deContext) throws IOException, JsonProcessingException {
        JsonNode jNode = jsonParser.getCodec().readTree(jsonParser);
        JsonNode guiPositionNode = jNode.get("position");
        JsonNode guiScaleNode = jNode.get("scale");
        JsonNode guiColorNode = jNode.get("color");
        JsonNode guiTextNode = jNode.get("text");
        GUIObject guiObject = new GUIObject(
                "WhitePane",
                new Vector2f(
                        guiPositionNode.get("x").floatValue(),
                        guiPositionNode.get("y").floatValue()
                ),
                new Vector2f(
                        guiScaleNode.get("x").floatValue(),
                        guiScaleNode.get("y").floatValue()
                ),
                new Color(
                        guiColorNode.get("r").floatValue(),
                        guiColorNode.get("g").floatValue(),
                        guiColorNode.get("b").floatValue(),
                        guiColorNode.get("a").floatValue()
                )
        );
        if(guiTextNode!=null) {
            guiObject.setText(new GUIText(
                    guiTextNode.get("text").asText(),
                    guiTextNode.get("fontSize").floatValue(),
                    TextMaster.getFonts().get(guiTextNode.get("fontType").asText()),
                    new Vector2f(
                            guiTextNode.get("position").get("x").floatValue(),
                            guiTextNode.get("position").get("y").floatValue()
                    ),
                    guiTextNode.get("maxLineSize").floatValue(),
                    guiTextNode.get("centerText").asBoolean()
            ));
        }
        return guiObject;
    }
}