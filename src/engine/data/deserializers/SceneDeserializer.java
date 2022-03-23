package data.deserializers;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import core.GameEngine;
import entities.*;
import entities.components.*;
import layout.*;
import layout.objects.GUIPane;
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
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(GUIObject.class, new GUIObjectDeserializer());
        simpleModule.addDeserializer(GUIPane.class, new GUIPaneDeserializer());
        mapper.registerModule(simpleModule);
        GameEngine.setCurrentScene(scene);
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
            model.getMaterial().setAmbientValue(gameObjectNode.get("material").get("ambientValue").floatValue());
            model.getMaterial().setDiffuseValue(gameObjectNode.get("material").get("diffuseValue").floatValue());
            model.getMaterial().setSmoothness(gameObjectNode.get("material").get("smoothness").floatValue());
            model.getMaterial().setSpecularValue(gameObjectNode.get("material").get("specularValue").floatValue());
            scene.getGameObjectList().add(new GameObject(gameObjectNode.get("name").asText(),model,transform));
        }
        JsonNode guiNode = jNode.get("currentGUI");
        GUI curGUI = new GUI();
        for(JsonNode guiObjectNode : guiNode.get("guiList")){
            try {
                GUIObject guiClass = (GUIObject) Class.forName(guiObjectNode.get("guiType").asText()).newInstance();
                curGUI.add(mapper.treeToValue(guiObjectNode, guiClass.getClass()));
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        scene.setCurrentGUI(curGUI);
        JsonNode lightsNode = jNode.get("lights");
        for(JsonNode lightNode : lightsNode) {
            scene.getLights().add(new Light(
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
        }
        JsonNode cameraNode = jNode.get("camera");
        scene.setCamera(new Camera(
                new Transform(
                        new Vector3f(
                                cameraNode.get("transform").get("position").get("x").floatValue(),
                                cameraNode.get("transform").get("position").get("y").floatValue(),
                                cameraNode.get("transform").get("position").get("z").floatValue()
                        ),
                        new Vector3f(
                                cameraNode.get("transform").get("rotation").get("x").floatValue(),
                                cameraNode.get("transform").get("rotation").get("y").floatValue(),
                                cameraNode.get("transform").get("rotation").get("z").floatValue()
                        )
                        ),
                cameraNode.get("pitch").floatValue(),
                cameraNode.get("yaw").floatValue(),
                cameraNode.get("roll").floatValue()
        ));
        return scene;
    }
}
