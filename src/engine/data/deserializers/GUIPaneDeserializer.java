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
import layout.objects.GUIPane;
import org.joml.Vector2f;

import java.io.IOException;

public class GUIPaneDeserializer extends StdDeserializer<GUIPane> {

    public GUIPaneDeserializer() {
        this(null);
    }

    public GUIPaneDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public GUIPane deserialize(JsonParser jsonParser, DeserializationContext deContext) throws IOException, JsonProcessingException {
        JsonNode jNode = jsonParser.getCodec().readTree(jsonParser);
        JsonNode guiPositionNode = jNode.get("position");
        JsonNode guiScaleNode = jNode.get("scale");
        JsonNode guiColorNode = jNode.get("color");
        JsonNode guiTextNode = jNode.get("text");
        GUIPane guiObject = (GUIPane) new GUIObject(
                new Vector2f(
                        guiPositionNode.get("x").floatValue(),
                        guiPositionNode.get("y").floatValue()
                ),
                new Vector2f(
                        guiScaleNode.get("x").floatValue(),
                        guiScaleNode.get("y").floatValue()
                )
        );
        guiObject.getTexture().setColor(new Color(
                guiColorNode.get("r").floatValue(),
                guiColorNode.get("g").floatValue(),
                guiColorNode.get("b").floatValue(),
                guiColorNode.get("a").floatValue()
        ));
        return guiObject;
    }
}