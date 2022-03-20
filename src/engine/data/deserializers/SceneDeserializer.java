package data.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import entities.Camera;
import entities.GameObject;
import entities.Light;
import entities.Scene;
import entities.components.Model;
import entities.components.Transform;
import font.GUIText;
import font.TextMaster;
import layout.GUI;
import layout.GUIObject;
import layout.components.Color;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.io.IOException;

public class SceneDeserializer extends StdDeserializer<Scene> {

    public SceneDeserializer() {
        this(null);
    }

    public SceneDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Scene deserialize(JsonParser jsonParser, DeserializationContext deContext) throws IOException, JsonProcessingException {
        Scene scene = new Scene();
        JsonNode jNode = jsonParser.getCodec().readTree(jsonParser);
        JsonNode nameNode = jNode.get("name");
        scene.setName(nameNode.asText());
        JsonNode gameObjectListNode = jNode.get("gameObjectList");
        for(JsonNode gameObjectNode : gameObjectListNode){
            JsonNode goModel = gameObjectNode.get("model");
            JsonNode goTransform = gameObjectNode.get("transform");
            Model model;
            if((model = scene.findModel(goModel.asText()))==null)
                model = new Model(goModel.asText());
            Transform transform = new Transform(
                    new Vector3f(
                            goTransform.get("position").get("x").floatValue(),
                            goTransform.get("position").get("y").floatValue(),
                            goTransform.get("position").get("z").floatValue()
                    ),
                    new Vector3f(
                            goTransform.get("rotation").get("x").floatValue(),
                            goTransform.get("rotation").get("y").floatValue(),
                            goTransform.get("rotation").get("z").floatValue()
                    ),
                    goTransform.get("scale").floatValue()
                    );
            scene.getGameObjectList().add(new GameObject(gameObjectNode.get("name").asText(),model,transform));
        }
        JsonNode guiNode = jNode.get("currentGUI");
        GUI curGUI = new GUI();
        for(JsonNode guiObjectNode : guiNode.get("guiList")){
            JsonNode guiPositionNode = guiObjectNode.get("position");
            float posX = guiPositionNode.get("x").floatValue();
            float posY = guiPositionNode.get("y").floatValue();
            JsonNode guiScaleNode = guiObjectNode.get("scale");
            float scaleX = guiScaleNode.get("x").floatValue();
            float scaleY = guiScaleNode.get("y").floatValue();
            JsonNode guiColorNode = guiObjectNode.get("color");
            JsonNode guiTextNode = guiObjectNode.get("text");
            curGUI.add(new GUIObject(
                    guiObjectNode.get("guiType").asText(),
                    new Vector2f(
                            posX,
                            posY
                    ),
                    new Vector2f(
                            scaleX,
                            scaleY
                    ),
                    new Color(
                            guiColorNode.get("r").floatValue(),
                            guiColorNode.get("g").floatValue(),
                            guiColorNode.get("b").floatValue(),
                            guiColorNode.get("a").floatValue()
                    ),
                    new GUIText(
                            guiTextNode.get("text").asText(),
                            guiTextNode.get("fontSize").floatValue(),
                            TextMaster.getFonts().get(guiTextNode.get("fontType").asText()),
                            new Vector2f(
                                    Math.signum(posX)*(Math.abs(posX)-scaleX),
                                    posY
                            ),
                            guiTextNode.get("maxLineSize").floatValue(),
                            guiTextNode.get("centerText").asBoolean()
                    )
                    )
            );
        }
        scene.setCurrentGUI(curGUI);
        JsonNode lightNode = jNode.get("light");
        scene.setLight(new Light(
                new Vector3f(
                        lightNode.get("position").get("x").floatValue(),
                        lightNode.get("position").get("y").floatValue(),
                        lightNode.get("position").get("z").floatValue()
                ),
                new Vector3f(
                        lightNode.get("colour").get("x").floatValue(),
                        lightNode.get("colour").get("y").floatValue(),
                        lightNode.get("colour").get("z").floatValue()
                )
        ));
        //JsonNode cameraNode = jNode.get("camera");                     потом поменяем, нах не нужно пока расположение камеры
        scene.setCamera(new Camera());
        return scene;
    }
}
