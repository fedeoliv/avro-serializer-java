/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.fernandobrs.avro.models;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class AvroMessage extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 7726255716226961855L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AvroMessage\",\"namespace\":\"com.fernandobrs.avro.models\",\"fields\":[{\"name\":\"headers\",\"type\":{\"type\":\"map\",\"values\":\"string\"}},{\"name\":\"payload\",\"type\":\"bytes\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<AvroMessage> ENCODER =
      new BinaryMessageEncoder<AvroMessage>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<AvroMessage> DECODER =
      new BinaryMessageDecoder<AvroMessage>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<AvroMessage> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<AvroMessage> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<AvroMessage>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this AvroMessage to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a AvroMessage from a ByteBuffer. */
  public static AvroMessage fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.util.Map<java.lang.CharSequence,java.lang.CharSequence> headers;
   private java.nio.ByteBuffer payload;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public AvroMessage() {}

  /**
   * All-args constructor.
   * @param headers The new value for headers
   * @param payload The new value for payload
   */
  public AvroMessage(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> headers, java.nio.ByteBuffer payload) {
    this.headers = headers;
    this.payload = payload;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return headers;
    case 1: return payload;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: headers = (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>)value$; break;
    case 1: payload = (java.nio.ByteBuffer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'headers' field.
   * @return The value of the 'headers' field.
   */
  public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getHeaders() {
    return headers;
  }

  /**
   * Sets the value of the 'headers' field.
   * @param value the value to set.
   */
  public void setHeaders(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
    this.headers = value;
  }

  /**
   * Gets the value of the 'payload' field.
   * @return The value of the 'payload' field.
   */
  public java.nio.ByteBuffer getPayload() {
    return payload;
  }

  /**
   * Sets the value of the 'payload' field.
   * @param value the value to set.
   */
  public void setPayload(java.nio.ByteBuffer value) {
    this.payload = value;
  }

  /**
   * Creates a new AvroMessage RecordBuilder.
   * @return A new AvroMessage RecordBuilder
   */
  public static com.fernandobrs.avro.models.AvroMessage.Builder newBuilder() {
    return new com.fernandobrs.avro.models.AvroMessage.Builder();
  }

  /**
   * Creates a new AvroMessage RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new AvroMessage RecordBuilder
   */
  public static com.fernandobrs.avro.models.AvroMessage.Builder newBuilder(com.fernandobrs.avro.models.AvroMessage.Builder other) {
    return new com.fernandobrs.avro.models.AvroMessage.Builder(other);
  }

  /**
   * Creates a new AvroMessage RecordBuilder by copying an existing AvroMessage instance.
   * @param other The existing instance to copy.
   * @return A new AvroMessage RecordBuilder
   */
  public static com.fernandobrs.avro.models.AvroMessage.Builder newBuilder(com.fernandobrs.avro.models.AvroMessage other) {
    return new com.fernandobrs.avro.models.AvroMessage.Builder(other);
  }

  /**
   * RecordBuilder for AvroMessage instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<AvroMessage>
    implements org.apache.avro.data.RecordBuilder<AvroMessage> {

    private java.util.Map<java.lang.CharSequence,java.lang.CharSequence> headers;
    private java.nio.ByteBuffer payload;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.fernandobrs.avro.models.AvroMessage.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.headers)) {
        this.headers = data().deepCopy(fields()[0].schema(), other.headers);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.payload)) {
        this.payload = data().deepCopy(fields()[1].schema(), other.payload);
        fieldSetFlags()[1] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing AvroMessage instance
     * @param other The existing instance to copy.
     */
    private Builder(com.fernandobrs.avro.models.AvroMessage other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.headers)) {
        this.headers = data().deepCopy(fields()[0].schema(), other.headers);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.payload)) {
        this.payload = data().deepCopy(fields()[1].schema(), other.payload);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'headers' field.
      * @return The value.
      */
    public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getHeaders() {
      return headers;
    }

    /**
      * Sets the value of the 'headers' field.
      * @param value The value of 'headers'.
      * @return This builder.
      */
    public com.fernandobrs.avro.models.AvroMessage.Builder setHeaders(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
      validate(fields()[0], value);
      this.headers = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'headers' field has been set.
      * @return True if the 'headers' field has been set, false otherwise.
      */
    public boolean hasHeaders() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'headers' field.
      * @return This builder.
      */
    public com.fernandobrs.avro.models.AvroMessage.Builder clearHeaders() {
      headers = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'payload' field.
      * @return The value.
      */
    public java.nio.ByteBuffer getPayload() {
      return payload;
    }

    /**
      * Sets the value of the 'payload' field.
      * @param value The value of 'payload'.
      * @return This builder.
      */
    public com.fernandobrs.avro.models.AvroMessage.Builder setPayload(java.nio.ByteBuffer value) {
      validate(fields()[1], value);
      this.payload = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'payload' field has been set.
      * @return True if the 'payload' field has been set, false otherwise.
      */
    public boolean hasPayload() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'payload' field.
      * @return This builder.
      */
    public com.fernandobrs.avro.models.AvroMessage.Builder clearPayload() {
      payload = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AvroMessage build() {
      try {
        AvroMessage record = new AvroMessage();
        record.headers = fieldSetFlags()[0] ? this.headers : (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>) defaultValue(fields()[0]);
        record.payload = fieldSetFlags()[1] ? this.payload : (java.nio.ByteBuffer) defaultValue(fields()[1]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<AvroMessage>
    WRITER$ = (org.apache.avro.io.DatumWriter<AvroMessage>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<AvroMessage>
    READER$ = (org.apache.avro.io.DatumReader<AvroMessage>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
