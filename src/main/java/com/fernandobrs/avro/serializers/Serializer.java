package com.fernandobrs.avro.serializers;

import com.fernandobrs.avro.deserializers.Deserializer;

/**
 * Simple contract to be implemented by serialization/deserialization classes.
 * 
 * @param <T> The type this class (de)serializes.
 */
public interface Serializer<T> extends Deserializer<T> {
    byte[] serialize(T obj);
}
