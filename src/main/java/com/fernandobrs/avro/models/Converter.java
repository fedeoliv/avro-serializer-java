package com.fernandobrs.avro.models;

/**
 * Two-way conversion contract. Converts from {@link F} to {@link T} and the other way around.
 * @param <F> Convert objects of this type
 * @param <T> To objects of this type (and vice-versa)
 */
public interface Converter<F, T> {
    T convert(F toConvert);
    F reverse(T toReverse);
}
