package com.fernandobrs.avro;

import java.util.UUID;
import com.fernandobrs.avro.messages.Message;
import com.fernandobrs.avro.messages.MessageHeader;
import com.fernandobrs.avro.serializers.AvroSerializer;
import com.fernandobrs.avro.utils.EventTypes;
import static org.junit.Assert.assertNotNull;
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
