package data.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import core.entities.GameObject;

import java.io.IOException;

public class GameObjectSerializer extends StdSerializer<GameObject> {

    public GameObjectSerializer() {
        this(null);
    }

    public GameObjectSerializer(Class t) {
        super(t);
    }

    @Override
    public void serialize(GameObject gameObject, JsonGenerator jGen, SerializerProvider serializerProvider) throws IOException {
        jGen.writeStartObject();
        jGen.writeStringField("name" , gameObject.getName());
        jGen.writeStringField("model", gameObject.getModel().getName());
        jGen.writeObjectFieldStart("material");
        jGen.writeNumberField("ambientValue",gameObject.getModel().getMaterial().getAmbientValue());
        jGen.writeNumberField("diffuseValue",gameObject.getModel().getMaterial().getDiffuseValue());
        jGen.writeNumberField("smoothness",gameObject.getModel().getMaterial().getSmoothness());
        jGen.writeNumberField("specularValue",gameObject.getModel().getMaterial().getSpecularValue());
        jGen.writeEndObject();
        jGen.writeObjectField("transform", gameObject.getTransform());
        jGen.writeEndObject();
    }
}
