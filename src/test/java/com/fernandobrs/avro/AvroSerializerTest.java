package com.fernandobrs.avro;

import java.util.Optional;
import java.util.UUID;

import com.fernandobrs.avro.messages.Message;
import com.fernandobrs.avro.messages.headers.HeaderConfig;
import com.fernandobrs.avro.messages.headers.Headers;
import com.fernandobrs.avro.messages.headers.MessageHeader;
import com.fernandobrs.avro.serializers.AvroSerializer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AvroSerializerTest  {
    @Test
    public void shouldHaveMessageContent() {
        // Payload
        SampleEvent payload = new SampleEvent();
        payload.setTransactionId(UUID.randomUUID().toString());
        payload.setCorrelationId(UUID.randomUUID().toString());
        payload.setAccountFromId(UUID.randomUUID().toString());
        payload.setAccountToId(UUID.randomUUID().toString());
        payload.setAmount(120.00);

        // Header
        MessageHeader header = new MessageHeader();
        header.setTransactionId(payload.getTransactionId());
        header.setCorrelationId(payload.getCorrelationId());
        header.setEventType(EventTypes.BalanceSucceded);
        header.setSource("Sample");
        
        // Message
        Message<SampleEvent> message = new Message<>(header, payload);
        byte[] messageBytes = AvroSerializer.serialize(message);

        assertNotNull(messageBytes);
    }

    @Test
    public void shouldDeserializeHeader() {
        // Payload
        SampleEvent payload = new SampleEvent();
        payload.setTransactionId(UUID.randomUUID().toString());
        payload.setCorrelationId(UUID.randomUUID().toString());
        payload.setAccountFromId(UUID.randomUUID().toString());
        payload.setAccountToId(UUID.randomUUID().toString());
        payload.setAmount(120.00);

        // Header
        MessageHeader header = new MessageHeader();
        header.setTransactionId(payload.getTransactionId());
        header.setCorrelationId(payload.getCorrelationId());
        header.setEventType(EventTypes.BalanceSucceded);
        header.setSource("Sample");
        
        // Message
        Message<SampleEvent> message = new Message<>(header, payload);
        byte[] messageBytes = AvroSerializer.serialize(message);

        Headers headers = AvroSerializer.deserializeHeaders(messageBytes);

        assertNotNull(headers);
    }

    @Test
    public void shouldDeserializePayload() {
        // Payload
        SampleEvent payload = new SampleEvent();
        payload.setTransactionId(UUID.randomUUID().toString());
        payload.setCorrelationId(UUID.randomUUID().toString());
        payload.setAccountFromId(UUID.randomUUID().toString());
        payload.setAccountToId(UUID.randomUUID().toString());
        payload.setAmount(120.00);

        // Header
        MessageHeader header = new MessageHeader();
        header.setTransactionId(payload.getTransactionId());
        header.setCorrelationId(payload.getCorrelationId());
        header.setEventType(EventTypes.BalanceSucceded);
        header.setSource("Sample");
        
        // Message
        Message<SampleEvent> message = new Message<>(header, payload);
        byte[] messageBytes = AvroSerializer.serialize(message);

        SampleEvent deserializedPayload = AvroSerializer.deserializePayload(messageBytes);

        assertNotNull(deserializedPayload);
    }

    @Test
    public void shouldDeserializeMessage() {
        // Payload
        SampleEvent payload = new SampleEvent();
        payload.setTransactionId(UUID.randomUUID().toString());
        payload.setCorrelationId(UUID.randomUUID().toString());
        payload.setAccountFromId(UUID.randomUUID().toString());
        payload.setAccountToId(UUID.randomUUID().toString());
        payload.setAmount(120.00);

        // Header
        MessageHeader header = new MessageHeader();
        header.setTransactionId(payload.getTransactionId());
        header.setCorrelationId(payload.getCorrelationId());
        header.setEventType(EventTypes.BalanceSucceded);
        header.setSource("Sample");
        
        // Message
        Message<SampleEvent> message = new Message<>(header, payload);
        byte[] messageBytes = AvroSerializer.serialize(message);

        Message<SampleEvent> deserializedMessage = AvroSerializer.deserialize(messageBytes);

        assertNotNull(deserializedMessage);
    }

    @Test
    public void shouldHaveEventTypeAfterDeserialization() {
        // Payload
        SampleEvent payload = new SampleEvent();
        payload.setTransactionId(UUID.randomUUID().toString());
        payload.setCorrelationId(UUID.randomUUID().toString());
        payload.setAccountFromId(UUID.randomUUID().toString());
        payload.setAccountToId(UUID.randomUUID().toString());
        payload.setAmount(120.00);

        // Header
        MessageHeader header = new MessageHeader();
        header.setTransactionId(payload.getTransactionId());
        header.setCorrelationId(payload.getCorrelationId());
        header.setEventType(EventTypes.BalanceSucceded);
        header.setSource("Sample");
        
        // Message
        Message<SampleEvent> message = new Message<>(header, payload);
        byte[] messageBytes = AvroSerializer.serialize(message);

        // Deserialized header
        Headers headers = AvroSerializer.deserializeHeaders(messageBytes);

        Optional<String> transactionIdHeader = headers.get(HeaderConfig.TransactionId);
        Optional<String> correlationIdHeader = headers.get(HeaderConfig.CorrelationId);
        Optional<String> eventTypeHeader = headers.get(HeaderConfig.EventType);
        Optional<String> sourceHeader = headers.get(HeaderConfig.Source);

        assertTrue(transactionIdHeader.isPresent());
        assertTrue(correlationIdHeader.isPresent());
        assertTrue(eventTypeHeader.isPresent());
        assertTrue(sourceHeader.isPresent());
    }

    @Test
    public void shouldMatchEventTypeAfterDeserialization() {
        // Payload
        SampleEvent payload = new SampleEvent();
        payload.setTransactionId(UUID.randomUUID().toString());
        payload.setCorrelationId(UUID.randomUUID().toString());
        payload.setAccountFromId(UUID.randomUUID().toString());
        payload.setAccountToId(UUID.randomUUID().toString());
        payload.setAmount(120.00);

        // Header
        MessageHeader header = new MessageHeader();
        header.setTransactionId(payload.getTransactionId());
        header.setCorrelationId(payload.getCorrelationId());
        header.setEventType(EventTypes.BalanceSucceded);
        header.setSource("Sample");
        
        // Message
        Message<SampleEvent> message = new Message<>(header, payload);
        byte[] messageBytes = AvroSerializer.serialize(message);

        // Deserialized header
        Headers headers = AvroSerializer.deserializeHeaders(messageBytes);

        Optional<String> transactionIdHeader = headers.get(HeaderConfig.TransactionId);
        Optional<String> correlationIdHeader = headers.get(HeaderConfig.CorrelationId);
        Optional<String> eventTypeHeader = headers.get(HeaderConfig.EventType);
        Optional<String> sourceHeader = headers.get(HeaderConfig.Source);

        assertTrue(transactionIdHeader.get().equals(header.getTransactionId()));
        assertTrue(correlationIdHeader.get().equals(header.getCorrelationId()));
        assertTrue(eventTypeHeader.get().equals(header.getEventType()));
        assertTrue(sourceHeader.get().equals(header.getSource()));
    }

    @Test
    public void shouldHavePayloadAfterDeserialization() {
        // Payload
        SampleEvent payload = new SampleEvent();
        payload.setTransactionId(UUID.randomUUID().toString());
        payload.setCorrelationId(UUID.randomUUID().toString());
        payload.setAccountFromId(UUID.randomUUID().toString());
        payload.setAccountToId(UUID.randomUUID().toString());
        payload.setAmount(120.00);

        // Header
        MessageHeader header = new MessageHeader();
        header.setTransactionId(payload.getTransactionId());
        header.setCorrelationId(payload.getCorrelationId());
        header.setEventType(EventTypes.BalanceSucceded);
        header.setSource("Sample");
        
        // Message
        Message<SampleEvent> message = new Message<>(header, payload);
        byte[] messageBytes = AvroSerializer.serialize(message);

        SampleEvent deserializedPayload = AvroSerializer.deserializePayload(messageBytes);

        assertTrue(payload.getTransactionId().equals(deserializedPayload.getTransactionId()));
        assertTrue(payload.getCorrelationId().equals(deserializedPayload.getCorrelationId()));
        assertTrue(payload.getAccountFromId().equals(deserializedPayload.getAccountFromId()));
        assertTrue(payload.getAccountToId().equals(deserializedPayload.getAccountToId()));
        assertTrue(payload.getAmount().equals(deserializedPayload.getAmount()));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionForNullablePayload() {
        // Payload
        SampleEvent payload = null;

        // Header
        MessageHeader header = new MessageHeader();
        
        // Message
        Message<SampleEvent> message = new Message<>(header, payload);
        AvroSerializer.serialize(message);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionForNullableHeader() {
        // Payload
        SampleEvent payload = new SampleEvent();
        payload.setTransactionId(UUID.randomUUID().toString());
        payload.setCorrelationId(UUID.randomUUID().toString());
        payload.setAccountFromId(UUID.randomUUID().toString());
        payload.setAccountToId(UUID.randomUUID().toString());
        payload.setAmount(120.00);

        // Header
        MessageHeader header = null;
        
        // Message
        Message<SampleEvent> message = new Message<>(header, payload);
        AvroSerializer.serialize(message);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionForNullableHeaderAndPayload() {
        // Payload
        SampleEvent payload = null;

        // Header
        MessageHeader header = null;

        Message<SampleEvent> message = new Message<>(header, payload);
        AvroSerializer.serialize(message);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionForNullableMessage() {
        Message<SampleEvent> message = null;
        AvroSerializer.serialize(message);
    }
}
