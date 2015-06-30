/**
 * ByteArrayReader.java
 * 
 * Copyright (c) 2015 Kenji Sasaki
 * Released under the MIT license.
 * https://github.com/npedotnet/npe-image-library/blob/master/LICENSE
 * 
 * This file is a part of npe-image-library.
 * https://github.com/npedotnet/npe-image-library
 *
 * For more details, see npe-image-library wiki.
 * https://github.com/npedotnet/npe-image-library/wiki
 * 
 */

package net.npe.core;

public class ByteArrayReader {
	
	public interface ByteOrderReader {
		/**
		 * read short value from byte array buffer.
		 * @param buffer byte array buffer
		 * @param offset offset in buffer
		 * @return short value
		 */
		short readShort(byte [] buffer, int offset);
		/**
		 * read int value from byte array buffer.
		 * @param buffer byte array buffer
		 * @param offset offset in buffer
		 * @return int value
		 */
		int readInt(byte [] buffer, int offset);
		/**
		 * read long value from byte array buffer.
		 * @param buffer byte array buffer
		 * @param offset offset in buffer
		 * @return long value
		 */
		long readLong(byte [] buffer, int offset);
		/**
		 * read float value from byte array buffer.
		 * @param buffer byte array buffer
		 * @param offset offset in buffer
		 * @return float value
		 */
		float readFloat(byte [] buffer, int offset);
		/**
		 * read double value from byte array buffer.
		 * @param buffer byte array buffer
		 * @param offset offset in buffer
		 * @return double value
		 */
		double readDouble(byte [] buffer, int offset);
	}
	
	/** LittleEndian Reader */
	public static ByteOrderReader LittleEndian = new LittleEndianReader();
	
	/** BigEndian Reader */
	public static ByteOrderReader BigEndian = new BigEndianReader();
	
	/**
	 * constructor
	 * @param buffer byte array buffer
	 * @param offset offset in buffer
	 * @param endian default ByteOrderReader (LittleEndian or BigEndian)
	 */
	public ByteArrayReader(byte [] buffer, int offset, ByteOrderReader endian) {
		this.buffer = buffer;
		this.offset = offset;
		this.endian = endian;
		position = offset;
	}
	
	/**
	 * read byte value.
	 * @return byte value
	 */
	public final byte readByte() {
		return buffer[position++];
	}
	
	/**
	 * read short value with default byte order.
	 * @return short value
	 */
	public final short readShort() {
		return readShort(endian);
	}
	
	/**
	 * read int value with default byte order.
	 * @return int value
	 */
	public final int readInt() {
		return readInt(endian);
	}
	
	/**
	 * read long value with default byte order.
	 * @return long value
	 */
	public final long readLong() {
		return readLong(endian);
	}
	
	/**
	 * read float value with default byte order.
	 * @return float value
	 */
	public final float readFloat() {
		return readFloat(endian);
	}
	
	/**
	 * read double value with default byte order.
	 * @return double value
	 */
	public final double readDouble() {
		return readDouble(endian);
	}
	
	/**
	 * read string value.
	 * @param length length of string
	 * @return string value
	 */
	public final String readString(int length) {
		String value = new String(buffer, position, length);
		position += length;
		return value;
	}
	
	/**
	 * read big-endian short value.
	 * @return big-endian short value
	 */
	public final short readBigEndianShort() {
		return readShort(BigEndian);
	}
	
	/**
	 * read big-endian int value.
	 * @return big-endian int value
	 */
	public final int readBigEndianInt() {
		return readInt(BigEndian);
	}
	
	/**
	 * read big-endian long value.
	 * @return big-endian long value
	 */
	public final long readBigEndianLong() {
		return readLong(BigEndian);
	}
	
	/**
	 * read big-endian float value.
	 * @return big-endian float value
	 */
	public final float readBigEndianFloat() {
		return readFloat(BigEndian);
	}
	
	/**
	 * read big-endian double value.
	 * @return big-endian double value
	 */
	public final double readBigEndianDouble() {
		return readDouble(BigEndian);
	}
	
	/**
	 * read little-endian short value.
	 * @return little-endian short value
	 */
	public final short readLittleEndianShort() {
		return readShort(LittleEndian);
	}
	
	/**
	 * read little-endian int value.
	 * @return little-endian int value
	 */
	public final int readLittleEndianInt() {
		return readInt(LittleEndian);
	}
	
	/**
	 * read little-endian long value.
	 * @return little-endian long value
	 */
	public final long readLittleEndianLong() {
		return readLong(LittleEndian);
	}
	
	/**
	 * read little-endian float value.
	 * @return little-endian float value
	 */
	public final float readLittleEndianFloat() {
		return readFloat(LittleEndian);
	}
	
	/**
	 * read little-endian double value.
	 * @return little-endian double value
	 */
	public final double readLittleEndianDouble() {
		return readDouble(LittleEndian);
	}
	
	public final void skip(int numberOfBytes) {
		position += numberOfBytes;
	}
	
	public final void rewind() {
		position = offset;
	}
	
	public final int getPosition() {
		return position;
	}
	
	public final void setPosition(int position) {
		this.position = position;
	}
	
	public final int getOffset() {
		return offset;
	}
	
	public final void setOffset(int offset) {
		this.offset = offset;
	}
	
	public final int getReadByte() {
		return position - offset;
	}
	
	private final short readShort(ByteOrderReader endian) {
		short value = endian.readShort(buffer, position);
		position += 2;
		return value;
	}
	
	private final int readInt(ByteOrderReader endian) {
		int value = endian.readInt(buffer, position);
		position += 4;
		return value;
	}
	
	private final long readLong(ByteOrderReader endian) {
		long value = endian.readLong(buffer, position);
		position += 8;
		return value;
	}
	
	private final float readFloat(ByteOrderReader endian) {
		float value = endian.readFloat(buffer, position);
		position += 4;
		return value;
	}
	
	private final double readDouble(ByteOrderReader endian) {
		double value = endian.readDouble(buffer, position);
		position += 8;
		return value;
	}
	
	private byte [] buffer;
	private int position;
	private int offset;
	private ByteOrderReader endian;

	private static final class LittleEndianReader implements ByteOrderReader {
		@Override
		public short readShort(byte [] buffer, int offset) {
			return (short)((buffer[offset] & 0xFF) | (buffer[offset+1] & 0xFF) << 8);
		}
		@Override
		public int readInt(byte [] buffer, int offset) {
			return (buffer[offset] & 0xFF) | (buffer[offset+1] & 0xFF) << 8 | (buffer[offset+2] & 0xFF) << 16 | (buffer[offset+3] & 0xFF) << 24;
		}
		@Override
		public long readLong(byte [] buffer, int offset) {
			return	(buffer[offset] & 0xFF) |
					(buffer[offset+1] & 0xFF) << 8 |
					(buffer[offset+2] & 0xFF) << 16 |
					(buffer[offset+3] & 0xFF) << 24 |
					(buffer[offset+4] & 0xFF) << 32 |
					(buffer[offset+5] & 0xFF) << 40 |
					(buffer[offset+6] & 0xFF) << 48 |
					(buffer[offset+7] & 0xFF) << 56;
		}
		@Override
		public float readFloat(byte [] buffer, int offset) {
			return Float.intBitsToFloat(readInt(buffer, offset));
		}
		@Override
		public double readDouble(byte [] buffer, int offset) {
			return Double.longBitsToDouble(readLong(buffer, offset));
		}
	}
	
	private static final class BigEndianReader implements ByteOrderReader {
		@Override
		public short readShort(byte [] buffer, int offset) {
			return (short)((buffer[offset] & 0xFF) << 8 | (buffer[offset+1] & 0xFF));
		}
		@Override
		public int readInt(byte [] buffer, int offset) {
			return (buffer[offset] & 0xFF) << 24 | (buffer[offset+1] & 0xFF) << 16 | (buffer[offset+2] & 0xFF) << 8 | (buffer[offset+3] & 0xFF);
		}
		@Override
		public long readLong(byte [] buffer, int offset) {
			return	(buffer[offset] & 0xFF) << 56 |
					(buffer[offset+1] & 0xFF) << 48 |
					(buffer[offset+2] & 0xFF) << 40 |
					(buffer[offset+3] & 0xFF) << 32 |
					(buffer[offset+4] & 0xFF) << 24 |
					(buffer[offset+5] & 0xFF) << 16 |
					(buffer[offset+6] & 0xFF) << 8 |
					(buffer[offset+7] & 0xFF);
		}
		@Override
		public float readFloat(byte [] buffer, int offset) {
			return Float.intBitsToFloat(readInt(buffer, offset));
		}
		@Override
		public double readDouble(byte [] buffer, int offset) {
			return Double.longBitsToDouble(readLong(buffer, offset));
		}
	}
	
}
