package com.fernandobrs.avro.messages;

import org.apache.commons.lang3.tuple.Pair;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;
import com.fernandobrs.avro.messages.headers.HeaderConfig;
import com.fernandobrs.avro.messages.headers.Headers;
import com.fernandobrs.avro.messages.headers.MessageHeader;
import static java.util.Objects.requireNonNull;

/**
 * Generic message representation. These are the instances that will be serialized and sent through the wire. This class
 * knows nothing about the serialization/deserialization process though.
 *
 * @param <T> The type of the payload this message stores
 */
public class Message<T> {

    private final Headers headers;
    private final Supplier<T> payload;

    public Message(MessageHeader header, T payload) {
        requireNonNull(header);
        requireNonNull(payload);

        this.payload = () -> payload;
        this.headers = createHeaders(header);
    }

    public Message(Headers headers, T payload) {
        requireNonNull(payload);

        this.headers = requireNonNull(headers);
        this.payload = () -> payload;
    }

    public Message(Headers headers, Supplier<T> payload) {
        this.headers = requireNonNull(headers);
        this.payload = requireNonNull(payload);
    }

    public Optional<String> getHeader(String key) {
        return headers.get(key);
    }

    public Collection<Pair<String, String>> getAllHeaders() {
        return headers.getAll();
    }

    public Optional<String> removeHeader(String key) {
        return headers.remove(key);
    }

    public T getPayload() {
        return payload.get();
    }

    private Headers createHeaders(MessageHeader header) {
        Headers headers = new Headers();

        headers.put(HeaderConfig.MessageId, header.getId());
        headers.put(HeaderConfig.TransactionId, header.getTransactionId());
        headers.put(HeaderConfig.CorrelationId, header.getCorrelationId());
        headers.put(HeaderConfig.EventType, header.getEventType());
        headers.put(HeaderConfig.EventType, header.getEventType());
        headers.put(HeaderConfig.Source, header.getSource());
        headers.put(HeaderConfig.CreationTime, header.getCreationTime());

        return headers;
    }
}
