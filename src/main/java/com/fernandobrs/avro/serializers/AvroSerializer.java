package com.fernandobrs.avro.serializers;

import com.fernandobrs.avro.converters.AvroMessageConverter;
import com.fernandobrs.avro.messages.AvroMessage;
import com.fernandobrs.avro.messages.Headers;
import com.fernandobrs.avro.messages.Message;

import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static java.util.Objects.requireNonNull;

public class AvroSerializer {
    public static <T> byte[] serialize(Message<T> obj) {
        AvroMessageConverter<T> messageConverter = createConverter();
        DatumWriter<AvroMessage> datumWriter = new SpecificDatumWriter<>(AvroMessage.getClassSchema());
        AvroMessage avroMessage = messageConverter.convert(obj);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(out, null);
            datumWriter.write(avroMessage, encoder);
            encoder.flush();
            out.close();
            return out.toByteArray();
        } 
        catch (Exception e) {
            throw new RuntimeException("Error serializing message", e);
        }
    }
    
    public static <T> Message<T> deserialize(byte[] bytes) {
        AvroMessageConverter<T> messageConverter = createConverter();
        Decoder decoder = DecoderFactory.get().binaryDecoder(bytes, null);
        DatumReader<AvroMessage> datumReader = new SpecificDatumReader<>(AvroMessage.getClassSchema());

        try {
            AvroMessage avroMessage = datumReader.read(null, decoder);
            return messageConverter.reverse(avroMessage);
        } 
        catch (IOException e) {
            throw new RuntimeException("Error deserializing message", e);
        }
    }

    public static <T> Headers deserializeHeaders(byte[] bytes) {
        AvroMessageConverter<T> messageConverter = createConverter();
        Decoder decoder = DecoderFactory.get().binaryDecoder(bytes, null);
        DatumReader<AvroMessage> datumReader = new SpecificDatumReader<>(AvroMessage.getClassSchema());

        try {
            AvroMessage avroMessage = datumReader.read(null, decoder);
            return messageConverter.reverseHeaders(avroMessage);
        } 
        catch (IOException e) {
            throw new RuntimeException("Error deserializing headers", e);
        }
    }

    public static <T> T deserializePayload(byte[] bytes) {
        AvroMessageConverter<T> messageConverter = createConverter();
        Decoder decoder = DecoderFactory.get().binaryDecoder(bytes, null);
        DatumReader<AvroMessage> datumReader = new SpecificDatumReader<>(AvroMessage.getClassSchema());

        try {
            AvroMessage avroMessage = datumReader.read(null, decoder);
            return messageConverter.reversePayload(avroMessage).get();
        } 
        catch (IOException e) {
            throw new RuntimeException("Error deserializing message", e);
        }
    }

    private static <T> AvroMessageConverter<T> createConverter() {
        return new AvroMessageConverter<>();
    }
}
