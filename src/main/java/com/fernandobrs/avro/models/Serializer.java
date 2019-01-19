package com.fernandobrs.avro.models;

/**
 * Simple contract to be implemented by serialization/deserialization classes.
 * @param <T> The type this class (de)serializes.
 */
public interface Serializer<T> extends Deserializer<T> {
    byte[] serialize(T obj);
}
