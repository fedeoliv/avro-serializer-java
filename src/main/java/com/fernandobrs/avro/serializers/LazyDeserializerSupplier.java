package com.fernandobrs.avro.serializers;

import java.util.function.Supplier;
import static java.util.Objects.requireNonNull;
import com.fernandobrs.avro.models.Deserializer;

/**
 * {@link Supplier} implementation that lazily deserializes a given byte[].
 *
 * @param <T> Type of the object supplied.
 */
public class LazyDeserializerSupplier<T> implements Supplier<T> {

    // private static final LogFormatEnforcer LOGGER = LogFormatEnforcer.loggerFor(LazyDeserializerSupplier.class);

    private final Deserializer<T> serializer;
    private final byte[] bytes;
    private T obj = null;

    public LazyDeserializerSupplier(Deserializer<T> serializer, byte[] bytes) {
        this.serializer = requireNonNull(serializer);
        this.bytes = requireNonNull(bytes);
    }

    public synchronized T get() {
        if (this.obj == null) {
            deserializeBytes();
        }
        return this.obj;
    }

    private void deserializeBytes() {
        // LOGGER.trace(b -> b.operation("deserializeBytes")
        //         .message("Obj will be deserialized")
        //         .and("serializer", serializer)
        //         .and("bytesLength", bytes.length));

        this.obj = serializer.deserialize(bytes);

        // LOGGER.debug(b -> b.operation("deserializeBytes")
        //         .message("Obj was deserialized")
        //         .and("serializer", serializer)
        //         .and("bytesLength", bytes.length)
        //         .and("obj", obj));
    }
}
