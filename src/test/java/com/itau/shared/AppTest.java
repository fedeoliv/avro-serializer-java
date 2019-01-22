package com.itau.shared;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.Before;
import org.junit.Test;
import java.util.LinkedHashMap;
import com.itau.shared.events.EventTest;
import com.itau.shared.kafka.ConsumerServiceMock;
import com.itau.shared.kafka.ProducerServiceMock;
import com.itau.shared.messaging.messages.Message;
import com.itau.shared.messaging.messages.headers.MessageHeader;
import com.itau.shared.messaging.serializers.AvroSerializer;
import static org.junit.Assert.assertTrue;

public class AppTest {
    private ProducerServiceMock<String, byte[]> producer;
    private ConsumerServiceMock<String, byte[]> consumer;
    private final String TOPIC = "my_topic";

    @Before
    public void setUp() {
        producer = new ProducerServiceMock<>(TOPIC);
        consumer = new ConsumerServiceMock<>(TOPIC);
    }

    @Test
    public void shouldNotHaveEmptyHistoryOnProducer() {
        EventTest payload = new EventTest();
        MessageHeader header = new MessageHeader();
        Message<EventTest> message = new Message<>(header, payload);
        byte[] messageBytes = AvroSerializer.serialize(message);

        producer.send(header.getCorrelationId(), messageBytes);

        assertTrue(!producer.getHistory().isEmpty());
    }

    @Test
    public void shouldNotHaveEmptyMessagesOnConsumer() {
        // First message
        EventTest payload1 = new EventTest();
        MessageHeader header1 = new MessageHeader();
        Message<EventTest> message1 = new Message<>(header1, payload1);
        byte[] messageBytes1 = AvroSerializer.serialize(message1);

        // Second message
        EventTest payload2 = new EventTest();
        MessageHeader header2 = new MessageHeader();
        Message<EventTest> message2 = new Message<>(header2, payload2);
        byte[] messageBytes2 = AvroSerializer.serialize(message2);

        // Messages list
        LinkedHashMap<String, byte[]> messages = new LinkedHashMap<>();
        messages.put(header1.getCorrelationId(), messageBytes1);
        messages.put(header2.getCorrelationId(), messageBytes2);

        // Consumer records
        ConsumerRecords<String, byte[]> records = consumer.createRecords(messages);

        // records.forEach(record -> {
        //     String key = record.key();
        //     byte[] messageBytes = record.value();
        // });

        consumer.commitAsync();
        assertTrue(!records.isEmpty());
    }
}
