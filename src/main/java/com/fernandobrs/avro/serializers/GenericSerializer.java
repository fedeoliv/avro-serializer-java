package com.fernandobrs.avro.serializers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.fernandobrs.avro.models.Headers;
import com.fernandobrs.avro.models.Serializer;

/**
 * Simple {@link Serializer} implementation for {@link Integer}s
 */
public class GenericSerializer<T> implements Serializer<T> {
    public byte[] serialize(T obj) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(out);
            
            os.writeObject(obj);
            
            return out.toByteArray();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public T deserialize(byte[] data) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            ObjectInputStream is = new ObjectInputStream(in);
            
            T result = (T) is.readObject();

            return result;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (ClassCastException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Headers deserializeHeaders(byte[] data) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            ObjectInputStream is = new ObjectInputStream(in);
            
            Headers result = (Headers) is.readObject();

            return result;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (ClassCastException e) {
            e.printStackTrace();
        }

        return null;
    }
}
