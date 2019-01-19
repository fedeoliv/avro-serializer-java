package com.fernandobrs.avro.models;

import org.apache.commons.lang3.tuple.Pair;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import static java.util.Collections.emptyList;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

/**
 * Headers to be used by the {@link Message} class. It stores key-value pairs (both represented by {@link String}s for
 * simplicity).
 */
public class Headers {
    // private static final LogFormatEnforcer LOGGER = LogFormatEnforcer.loggerFor(Headers.class);

    private final Map<String, String> headersMap;

    public Headers() {
        this(emptyList());
    }

    public Headers(Collection<Pair<String, String>> keyValuePairs) {
        this.headersMap = keyValuePairs.stream().collect(toMap(Pair::getKey, Pair::getValue));
    }

    public void put(String key, String value) {
        // LOGGER.trace(b -> b.operation("put").and("key", key).and("value", value));
        String oldValue = headersMap.put(requireNonNull(key), requireNonNull(value));
        // LOGGER.debug(b -> b.operation("put").and("key", key).and("value", value).and("oldValue", oldValue));
    }

    public Optional<String> get(String key) {
        // LOGGER.trace(b -> b.operation("get").and("key", key));
        Optional<String> result = ofNullable(headersMap.get(key));
        // LOGGER.debug(b -> b.operation("get").and("key", key).and("result", result));
        return result;
    }

    public Collection<Pair<String, String>> getAll() {
        // LOGGER.trace(b -> b.operation("getAll"));
        Collection<Pair<String, String>> result = headersMap.entrySet().stream()
                .map(e -> Pair.of(e.getKey(), e.getValue()))
                .collect(toSet());
        // LOGGER.debug(b -> b.operation("getAll").and("result", result));
        return result;
    }

    public Optional<String> remove(String key) {
        // LOGGER.trace(b -> b.operation("remove").and("key", key));
        Optional<String> result = ofNullable(headersMap.remove(key));
        // LOGGER.debug(b -> b.operation("remove").and("key", key).and("result", result));
        return result;
    }
}
