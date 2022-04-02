package data.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import core.entities.components.Transform;

import java.io.IOException;

public class TransformSerializer extends StdSerializer<Transform> {

    public TransformSerializer() {
        this(null);
    }

    public TransformSerializer(Class<Transform> t) {
        super(t);
    }

    @Override
    public void serialize(Transform transform, JsonGenerator jGen, SerializerProvider serializerProvider) throws IOException {
        jGen.writeStartObject();
        jGen.writeObjectFieldStart("position");
        jGen.writeNumberField("x",transform.getPosition().x);
        jGen.writeNumberField("y",transform.getPosition().y);
        jGen.writeNumberField("z",transform.getPosition().z);
        jGen.writeEndObject();
        jGen.writeObjectFieldStart("rotation");
        jGen.writeNumberField("x",transform.getRotation().x);
        jGen.writeNumberField("y",transform.getRotation().y);
        jGen.writeNumberField("z",transform.getRotation().z);
        jGen.writeEndObject();
        jGen.writeNumberField("scale",transform.getScale());
        jGen.writeEndObject();
    }
}
