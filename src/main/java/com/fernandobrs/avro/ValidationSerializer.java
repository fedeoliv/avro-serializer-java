package com.fernandobrs.avro;

import com.fernandobrs.avro.models.Serializer;
import com.fernandobrs.avro.serializers.AvroMessageSerializer;
import com.fernandobrs.avro.serializers.GenericSerializer;

public class ValidationSerializer<T> {
    public AvroMessageSerializer<T> createAvroSerializer() {
        Serializer<T> payloadSerializer = new GenericSerializer<>();
        return new AvroMessageSerializer<T>(payloadSerializer);
    }  
}
