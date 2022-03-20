package data.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import entities.GameObject;

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
        jGen.writeObjectField("transform", gameObject.getTransform());
        jGen.writeEndObject();
    }
}
