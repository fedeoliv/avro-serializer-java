package com.fernandobrs.avro.converters;

import org.apache.commons.lang3.tuple.Pair;
import java.nio.ByteBuffer;
import java.util.function.Supplier;

import com.fernandobrs.avro.deserializers.LazyDeserializerSupplier;
import com.fernandobrs.avro.messages.AvroMessage;
import com.fernandobrs.avro.messages.Headers;
import com.fernandobrs.avro.messages.Message;
import com.fernandobrs.avro.serializers.Serializer;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class AvroMessageConverter<T> implements Converter<Message<T>, AvroMessage> {
    private final Serializer<T> payloadSerializer;

    public AvroMessageConverter(Serializer<T> payloadSerializer) {
        this.payloadSerializer = requireNonNull(payloadSerializer);
    }

    @Override
    public AvroMessage convert(Message<T> toConvert) {
        AvroMessage.Builder builder = AvroMessage.newBuilder();

        builder.setHeaders(
                toConvert.getAllHeaders().stream().collect(toMap(Pair::getKey, Pair::getValue))
        );
        builder.setPayload(
                ByteBuffer.wrap(payloadSerializer.serialize(toConvert.getPayload()))
        );

        AvroMessage result = builder.build();
        return result;
    }

    @Override
    public Message<T> reverse(AvroMessage toConvert) {
        Headers headers = new Headers(
            toConvert.getHeaders()
                .entrySet().stream()
                .map(e -> Pair.of(e.getKey().toString(), e.getValue().toString()))
                .collect(toList())
        );
 
        Supplier<T> payload = new LazyDeserializerSupplier<>(payloadSerializer, toConvert.getPayload().array());
        Message<T> result = new Message<>(headers, payload);

        return result;
    }

    public Headers reverseHeaders(AvroMessage toConvert) {
        Headers headers = new Headers(
            toConvert.getHeaders()
                .entrySet().stream()
                .map(e -> Pair.of(e.getKey().toString(), e.getValue().toString()))
                .collect(toList())
        );
 
        return headers;
    }

    public Supplier<T> reversePayload(AvroMessage toConvert) {
        return new LazyDeserializerSupplier<>(payloadSerializer, toConvert.getPayload().array());
    }
}
