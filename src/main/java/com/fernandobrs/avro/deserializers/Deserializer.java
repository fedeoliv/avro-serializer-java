package com.fernandobrs.avro.deserializers;

import com.fernandobrs.avro.messages.Headers;

/**
 * Simple contract to be implemented by deserialization classes.
 * 
 * @param <T> The type this class deserializes.
 */
public interface Deserializer<T> {
    T deserialize(byte[] bytes);
    Headers deserializeHeaders(byte[] bytes);
}
