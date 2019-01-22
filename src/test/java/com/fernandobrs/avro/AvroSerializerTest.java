package com.fernandobrs.avro;

import java.util.Optional;
import java.util.UUID;

import com.fernandobrs.avro.events.EventTest;
import com.fernandobrs.avro.events.EventTypesTest;
import com.itau.shared.messaging.messages.Message;
import com.itau.shared.messaging.messages.headers.HeaderConfig;
import com.itau.shared.messaging.messages.headers.Headers;
import com.itau.shared.messaging.messages.headers.MessageHeader;
import com.itau.shared.messaging.serializers.AvroSerializer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class AvroSerializerTest  {
    @Test
    public void shouldHaveMessageContent() {
        // Payload
        EventTest payload = new EventTest();
        payload.setTransactionId(UUID.randomUUID().toString());
        payload.setCorrelationId(UUID.randomUUID().toString());
        payload.setAccountFromId(UUID.randomUUID().toString());
        payload.setAccountToId(UUID.randomUUID().toString());
        payload.setAmount(120.00);

        // Header
        MessageHeader header = new MessageHeader();
        header.setTransactionId(payload.getTransactionId());
        header.setCorrelationId(payload.getCorrelationId());
        header.setEventType(EventTypesTest.BalanceSucceded);
        header.setSource("Sample");
        
        // Message
        Message<EventTest> message = new Message<>(header, payload);
        byte[] messageBytes = AvroSerializer.serialize(message);

        assertNotNull(messageBytes);
    }

    @Test
    public void shouldDeserializeHeader() {
        // Payload
        EventTest payload = new EventTest();
        payload.setTransactionId(UUID.randomUUID().toString());
        payload.setCorrelationId(UUID.randomUUID().toString());
        payload.setAccountFromId(UUID.randomUUID().toString());
        payload.setAccountToId(UUID.randomUUID().toString());
        payload.setAmount(120.00);

        // Header
        MessageHeader header = new MessageHeader();
        header.setTransactionId(payload.getTransactionId());
        header.setCorrelationId(payload.getCorrelationId());
        header.setEventType(EventTypesTest.BalanceSucceded);
        header.setSource("Sample");
        
        // Message
        Message<EventTest> message = new Message<>(header, payload);
        byte[] messageBytes = AvroSerializer.serialize(message);

        Headers headers = AvroSerializer.deserializeHeaders(messageBytes);

        assertNotNull(headers);
    }

    @Test
    public void shouldDeserializePayload() {
        // Payload
        EventTest payload = new EventTest();
        payload.setTransactionId(UUID.randomUUID().toString());
        payload.setCorrelationId(UUID.randomUUID().toString());
        payload.setAccountFromId(UUID.randomUUID().toString());
        payload.setAccountToId(UUID.randomUUID().toString());
        payload.setAmount(120.00);

        // Header
        MessageHeader header = new MessageHeader();
        header.setTransactionId(payload.getTransactionId());
        header.setCorrelationId(payload.getCorrelationId());
        header.setEventType(EventTypesTest.BalanceSucceded);
        header.setSource("Sample");
        
        // Message
        Message<EventTest> message = new Message<>(header, payload);
        byte[] messageBytes = AvroSerializer.serialize(message);

        EventTest deserializedPayload = AvroSerializer.deserializePayload(messageBytes);

        assertNotNull(deserializedPayload);
    }

    @Test
    public void shouldDeserializeMessage() {
        // Payload
        EventTest payload = new EventTest();
        payload.setTransactionId(UUID.randomUUID().toString());
        payload.setCorrelationId(UUID.randomUUID().toString());
        payload.setAccountFromId(UUID.randomUUID().toString());
        payload.setAccountToId(UUID.randomUUID().toString());
        payload.setAmount(120.00);

        // Header
        MessageHeader header = new MessageHeader();
        header.setTransactionId(payload.getTransactionId());
        header.setCorrelationId(payload.getCorrelationId());
        header.setEventType(EventTypesTest.BalanceSucceded);
        header.setSource("Sample");
        
        // Message
        Message<EventTest> message = new Message<>(header, payload);
        byte[] messageBytes = AvroSerializer.serialize(message);

        Message<EventTest> deserializedMessage = AvroSerializer.deserialize(messageBytes);

        assertNotNull(deserializedMessage);
    }

    @Test
    public void shouldHaveEventTypeAfterDeserialization() {
        // Payload
        EventTest payload = new EventTest();
        payload.setTransactionId(UUID.randomUUID().toString());
        payload.setCorrelationId(UUID.randomUUID().toString());
        payload.setAccountFromId(UUID.randomUUID().toString());
        payload.setAccountToId(UUID.randomUUID().toString());
        payload.setAmount(120.00);

        // Header
        MessageHeader header = new MessageHeader();
        header.setTransactionId(payload.getTransactionId());
        header.setCorrelationId(payload.getCorrelationId());
        header.setEventType(EventTypesTest.BalanceSucceded);
        header.setSource("Sample");
        
        // Message
        Message<EventTest> message = new Message<>(header, payload);
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
        EventTest payload = new EventTest();
        payload.setTransactionId(UUID.randomUUID().toString());
        payload.setCorrelationId(UUID.randomUUID().toString());
        payload.setAccountFromId(UUID.randomUUID().toString());
        payload.setAccountToId(UUID.randomUUID().toString());
        payload.setAmount(120.00);

        // Header
        MessageHeader header = new MessageHeader();
        header.setTransactionId(payload.getTransactionId());
        header.setCorrelationId(payload.getCorrelationId());
        header.setEventType(EventTypesTest.BalanceSucceded);
        header.setSource("Sample");
        
        // Message
        Message<EventTest> message = new Message<>(header, payload);
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
        EventTest payload = new EventTest();
        payload.setTransactionId(UUID.randomUUID().toString());
        payload.setCorrelationId(UUID.randomUUID().toString());
        payload.setAccountFromId(UUID.randomUUID().toString());
        payload.setAccountToId(UUID.randomUUID().toString());
        payload.setAmount(120.00);

        // Header
        MessageHeader header = new MessageHeader();
        header.setTransactionId(payload.getTransactionId());
        header.setCorrelationId(payload.getCorrelationId());
        header.setEventType(EventTypesTest.BalanceSucceded);
        header.setSource("Sample");
        
        // Message
        Message<EventTest> message = new Message<>(header, payload);
        byte[] messageBytes = AvroSerializer.serialize(message);

        EventTest deserializedPayload = AvroSerializer.deserializePayload(messageBytes);

        assertTrue(payload.getTransactionId().equals(deserializedPayload.getTransactionId()));
        assertTrue(payload.getCorrelationId().equals(deserializedPayload.getCorrelationId()));
        assertTrue(payload.getAccountFromId().equals(deserializedPayload.getAccountFromId()));
        assertTrue(payload.getAccountToId().equals(deserializedPayload.getAccountToId()));
        assertTrue(payload.getAmount().equals(deserializedPayload.getAmount()));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionForNullablePayload() {
        // Payload
        EventTest payload = null;

        // Header
        MessageHeader header = new MessageHeader();
        
        // Message
        Message<EventTest> message = new Message<>(header, payload);
        AvroSerializer.serialize(message);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionForNullableHeader() {
        // Payload
        EventTest payload = new EventTest();
        payload.setTransactionId(UUID.randomUUID().toString());
        payload.setCorrelationId(UUID.randomUUID().toString());
        payload.setAccountFromId(UUID.randomUUID().toString());
        payload.setAccountToId(UUID.randomUUID().toString());
        payload.setAmount(120.00);

        // Header
        MessageHeader header = null;
        
        // Message
        Message<EventTest> message = new Message<>(header, payload);
        AvroSerializer.serialize(message);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionForNullableHeaderAndPayload() {
        // Payload
        EventTest payload = null;

        // Header
        MessageHeader header = null;

        Message<EventTest> message = new Message<>(header, payload);
        AvroSerializer.serialize(message);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionForNullableMessage() {
        Message<EventTest> message = null;
        AvroSerializer.serialize(message);
    }
}
