package com.aeroncookbook.cluster.rfq.gen;

import java.lang.String;
import org.agrona.DirectBuffer;
import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;

public class RfqAcceptedEvent {
  /**
   * The eider spec id for this type. Useful in switch statements to detect type in first 16bits.
   */
  public static final short EIDER_ID = 5013;

  /**
   * The eider group id for this type. Useful in switch statements to detect group in second 16bits.
   */
  public static final short EIDER_GROUP_ID = 4;

  /**
   * The offset for the EIDER_ID within the buffer.
   */
  private static final int HEADER_OFFSET = 0;

  /**
   * The offset for the EIDER_GROUP_IP within the buffer.
   */
  private static final int HEADER_GROUP_OFFSET = 2;

  /**
   * The length offset. Required for segmented buffers.
   */
  private static final int LENGTH_OFFSET = 4;

  /**
   * The byte offset in the byte array for this INT. Byte length is 4.
   */
  private static final int RFQID_OFFSET = 8;

  /**
   * The byte offset in the byte array for this INT. Byte length is 4.
   */
  private static final int ACCEPTEDBYUSERID_OFFSET = 12;

  /**
   * The byte offset in the byte array for this INT. Byte length is 4.
   */
  private static final int REQUESTERUSERID_OFFSET = 16;

  /**
   * The byte offset in the byte array for this INT. Byte length is 4.
   */
  private static final int RESPONDERUSERID_OFFSET = 20;

  /**
   * The byte offset in the byte array for this LONG. Byte length is 8.
   */
  private static final int PRICE_OFFSET = 24;

  /**
   * The byte offset in the byte array for this FIXED_STRING. Byte length is 13.
   */
  private static final int REQUESTERCLORDID_OFFSET = 32;

  /**
   * The total bytes required to store the object.
   */
  public static final int BUFFER_LENGTH = 45;

  /**
   * Indicates if this flyweight holds a fixed length object.
   */
  public static final boolean FIXED_LENGTH = true;

  /**
   * The internal DirectBuffer.
   */
  private DirectBuffer buffer = null;

  /**
   * The internal DirectBuffer used for mutatation opertions. Valid only if a mutable buffer was provided.
   */
  private MutableDirectBuffer mutableBuffer = null;

  /**
   * The internal UnsafeBuffer. Valid only if an unsafe buffer was provided.
   */
  private UnsafeBuffer unsafeBuffer = null;

  /**
   * The starting offset for reading and writing.
   */
  private int initialOffset;

  /**
   * Flag indicating if the buffer is mutable.
   */
  private boolean isMutable = false;

  /**
   * Flag indicating if the buffer is an UnsafeBuffer.
   */
  private boolean isUnsafe = false;

  /**
   * Uses the provided {@link org.agrona.DirectBuffer} from the given offset.
   * @param buffer - buffer to read from and write to.
   * @param offset - offset to begin reading from/writing to in the buffer.
   */
  public void setUnderlyingBuffer(DirectBuffer buffer, int offset) {
    this.initialOffset = offset;
    this.buffer = buffer;
    if (buffer instanceof UnsafeBuffer) {
      unsafeBuffer = (UnsafeBuffer) buffer;
      mutableBuffer = (MutableDirectBuffer) buffer;
      isUnsafe = true;
      isMutable = true;
    }
    else if (buffer instanceof MutableDirectBuffer) {
      mutableBuffer = (MutableDirectBuffer) buffer;
      isUnsafe = false;
      isMutable = true;
    }
    else {
      isUnsafe = false;
      isMutable = false;
    }
    buffer.checkLimit(initialOffset + BUFFER_LENGTH);
  }

  /**
   * Uses the provided {@link org.agrona.DirectBuffer} from the given offset.
   * @param buffer - buffer to read from and write to.
   * @param offset - offset to begin reading from/writing to in the buffer.
   */
  public void setBufferWriteHeader(DirectBuffer buffer, int offset) {
    setUnderlyingBuffer(buffer, offset);
    writeHeader();
  }

  /**
   * Returns the eider sequence.
   * @return EIDER_ID.
   */
  public short eiderId() {
    return EIDER_ID;
  }

  /**
   * Writes the header data to the buffer.
   */
  public void writeHeader() {
    if (!isMutable) throw new RuntimeException("cannot write to immutable buffer");
    mutableBuffer.putShort(initialOffset + HEADER_OFFSET, EIDER_ID, java.nio.ByteOrder.LITTLE_ENDIAN);
    mutableBuffer.putShort(initialOffset + HEADER_GROUP_OFFSET, EIDER_GROUP_ID, java.nio.ByteOrder.LITTLE_ENDIAN);
    mutableBuffer.putInt(initialOffset + LENGTH_OFFSET, BUFFER_LENGTH, java.nio.ByteOrder.LITTLE_ENDIAN);
  }

  /**
   * Validates the length and eiderSpecId in the header against the expected values. False if invalid.
   */
  public boolean validateHeader() {
    final short eiderId = buffer.getShort(initialOffset + HEADER_OFFSET, java.nio.ByteOrder.LITTLE_ENDIAN);
    final short eiderGroupId = buffer.getShort(initialOffset + HEADER_GROUP_OFFSET, java.nio.ByteOrder.LITTLE_ENDIAN);
    final int bufferLength = buffer.getInt(initialOffset + LENGTH_OFFSET, java.nio.ByteOrder.LITTLE_ENDIAN);
    if (eiderId != EIDER_ID) return false;
    if (eiderGroupId != EIDER_GROUP_ID) return false;
    return bufferLength == BUFFER_LENGTH;
  }

  /**
   * Reads rfqId as stored in the buffer.
   */
  public int readRfqId() {
    return buffer.getInt(initialOffset + RFQID_OFFSET, java.nio.ByteOrder.LITTLE_ENDIAN);
  }

  /**
   * Writes rfqId to the buffer. Returns true if success, false if not.
   * @param value Value for the rfqId to write to buffer.
   */
  public boolean writeRfqId(int value) {
    if (!isMutable) throw new RuntimeException("Cannot write to immutable buffer");
    mutableBuffer.putInt(initialOffset + RFQID_OFFSET, value, java.nio.ByteOrder.LITTLE_ENDIAN);
    return true;
  }

  /**
   * Reads acceptedByUserId as stored in the buffer.
   */
  public int readAcceptedByUserId() {
    return buffer.getInt(initialOffset + ACCEPTEDBYUSERID_OFFSET, java.nio.ByteOrder.LITTLE_ENDIAN);
  }

  /**
   * Writes acceptedByUserId to the buffer. Returns true if success, false if not.
   * @param value Value for the acceptedByUserId to write to buffer.
   */
  public boolean writeAcceptedByUserId(int value) {
    if (!isMutable) throw new RuntimeException("Cannot write to immutable buffer");
    mutableBuffer.putInt(initialOffset + ACCEPTEDBYUSERID_OFFSET, value, java.nio.ByteOrder.LITTLE_ENDIAN);
    return true;
  }

  /**
   * Reads requesterUserId as stored in the buffer.
   */
  public int readRequesterUserId() {
    return buffer.getInt(initialOffset + REQUESTERUSERID_OFFSET, java.nio.ByteOrder.LITTLE_ENDIAN);
  }

  /**
   * Writes requesterUserId to the buffer. Returns true if success, false if not.
   * @param value Value for the requesterUserId to write to buffer.
   */
  public boolean writeRequesterUserId(int value) {
    if (!isMutable) throw new RuntimeException("Cannot write to immutable buffer");
    mutableBuffer.putInt(initialOffset + REQUESTERUSERID_OFFSET, value, java.nio.ByteOrder.LITTLE_ENDIAN);
    return true;
  }

  /**
   * Reads responderUserId as stored in the buffer.
   */
  public int readResponderUserId() {
    return buffer.getInt(initialOffset + RESPONDERUSERID_OFFSET, java.nio.ByteOrder.LITTLE_ENDIAN);
  }

  /**
   * Writes responderUserId to the buffer. Returns true if success, false if not.
   * @param value Value for the responderUserId to write to buffer.
   */
  public boolean writeResponderUserId(int value) {
    if (!isMutable) throw new RuntimeException("Cannot write to immutable buffer");
    mutableBuffer.putInt(initialOffset + RESPONDERUSERID_OFFSET, value, java.nio.ByteOrder.LITTLE_ENDIAN);
    return true;
  }

  /**
   * Reads price as stored in the buffer.
   */
  public long readPrice() {
    return buffer.getLong(initialOffset + PRICE_OFFSET, java.nio.ByteOrder.LITTLE_ENDIAN);
  }

  /**
   * Writes price to the buffer. Returns true if success, false if not.
   * @param value Value for the price to write to buffer.
   */
  public boolean writePrice(long value) {
    if (!isMutable) throw new RuntimeException("Cannot write to immutable buffer");
    mutableBuffer.putLong(initialOffset + PRICE_OFFSET, value, java.nio.ByteOrder.LITTLE_ENDIAN);
    return true;
  }

  /**
   * Reads requesterClOrdId as stored in the buffer.
   */
  public String readRequesterClOrdId() {
    return buffer.getStringWithoutLengthAscii(initialOffset + REQUESTERCLORDID_OFFSET, 13).trim();
  }

  /**
   * Writes requesterClOrdId to the buffer. Returns true if success, false if not.Warning! Does not pad the string.
   * @param value Value for the requesterClOrdId to write to buffer.
   */
  public boolean writeRequesterClOrdId(String value) {
    if (!isMutable) throw new RuntimeException("Cannot write to immutable buffer");
    if (value.length() > 13) throw new RuntimeException("Field requesterClOrdId is longer than maxLength=13");
    mutableBuffer.putStringWithoutLengthAscii(initialOffset + REQUESTERCLORDID_OFFSET, value);
    return true;
  }

  /**
   * Writes requesterClOrdId to the buffer with padding. 
   * @param value Value for the requesterClOrdId to write to buffer.
   */
  public boolean writeRequesterClOrdIdWithPadding(String value) {
    final String padded = String.format("%13s", value);
    return writeRequesterClOrdId(padded);
  }

  /**
   * True if transactions are supported; false if not.
   */
  public boolean supportsTransactions() {
    return false;
  }
}
