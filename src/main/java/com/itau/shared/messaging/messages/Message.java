package com.itau.shared.messaging.messages;

import org.apache.commons.lang3.tuple.Pair;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;
import com.itau.shared.messaging.messages.headers.HeaderConfig;
import com.itau.shared.messaging.messages.headers.Headers;
import com.itau.shared.messaging.messages.headers.MessageHeader;

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

        if (header.getId() != null) {
            headers.put(HeaderConfig.MessageId, header.getId());
        }

        if (header.getTransactionId() != null) {
            headers.put(HeaderConfig.TransactionId, header.getTransactionId());
        }
        
        if (header.getCorrelationId() != null) {
            headers.put(HeaderConfig.CorrelationId, header.getCorrelationId());
        }
        
        if (header.getEventType() != null) {
            headers.put(HeaderConfig.EventType, header.getEventType());
        }
        
        if (header.getSource() != null) {
            headers.put(HeaderConfig.Source, header.getSource());
        }
        
        if (header.getCreationTime() != null) {
            headers.put(HeaderConfig.CreationTime, header.getCreationTime());
        }

        return headers;
    }
}
