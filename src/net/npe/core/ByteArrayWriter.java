/**
 * ByteArrayWriter.java
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

public class ByteArrayWriter {
	
	public interface ByteOrderWriter {
		/**
		 * write short value to byte array buffer.
		 * @param buffer byte array buffer
		 * @param offset offset in buffer
		 * @param value short value
		 */
		void writeShort(byte [] buffer, int offset, short value);
		/**
		 * write int value to byte array buffer.
		 * @param buffer byte array buffer
		 * @param offset offset in buffer
		 * @param value int value
		 */
		void writeInt(byte [] buffer, int offset, int value);
		/**
		 * write long value to byte array buffer.
		 * @param buffer byte array buffer
		 * @param offset offset in buffer
		 * @param value long value
		 */
		void writeLong(byte [] buffer, int offset, long value);
		/**
		 * write float value to byte array buffer.
		 * @param buffer byte array buffer
		 * @param offset offset in buffer
		 * @param value float value
		 */
		void writeFloat(byte [] buffer, int offset, float value);
		/**
		 * write double value to byte array buffer.
		 * @param buffer byte array buffer
		 * @param offset offset in buffer
		 * @param value double value
		 */
		void writeDouble(byte [] buffer, int offset, double value);
	}
	
	/** LittleEndian Writer */
	public static ByteOrderWriter LittleEndian = new LittleEndianWriter();
	
	/** BigEndian Writer */
	public static ByteOrderWriter BigEndian = new BigEndianWriter();
	
	/**
	 * constructor
	 * @param buffer byte array buffer
	 * @param offset offset in buffer
	 * @param endian default ByteOrderWriter (LittleEndian or BigEndian)
	 */
	public ByteArrayWriter(byte [] buffer, int offset, ByteOrderWriter endian) {
		this.buffer = buffer;
		this.offset = offset;
		this.endian = endian;
		position = offset;
	}
	
	/**
	 * write byte value.
	 * @param value byte value
	 */
	public final void writeByte(byte value) {
		buffer[position++] = value;
	}
	
	/**
	 * write short value.
	 * @param value short value
	 */
	public final void writeShort(short value) {
		writeShort(endian, value);
	}
	
	/**
	 * write int value.
	 * @param value int value
	 */
	public final void writeInt(int value) {
		writeInt(endian, value);
	}
	
	/**
	 * write long value.
	 * @param value long value
	 */
	public final void writeLong(long value) {
		writeLong(endian, value);
	}
	
	/**
	 * write float value.
	 * @param value float value
	 */
	public final void writeFloat(float value) {
		writeFloat(endian, value);
	}
	
	/**
	 * write double value.
	 * @param value double value
	 */
	public final void writeDouble(double value) {
		writeDouble(endian, value);
	}

	// TODO writeString
	
	/**
	 * write big-endian short value.
	 * @param value big-endian short value
	 */
	public final void writeBigEndianShort(short value) {
		writeShort(BigEndian, value);
	}
	
	/**
	 * write big-endian int value.
	 * @param value big-endian int value
	 */
	public final void writeBigEndianInt(int value) {
		writeInt(BigEndian, value);
	}
	
	/**
	 * write big-endian long value.
	 * @param value big-endian long value
	 */
	public final void writeBigEndianLong(long value) {
		writeLong(BigEndian, value);
	}
	
	/**
	 * write big-endian float value.
	 * @param value big-endian float value
	 */
	public final void writeBigEndianFloat(float value) {
		writeFloat(BigEndian, value);
	}
	
	/**
	 * write big-endian double value.
	 * @param value big-endian double value
	 */
	public final void writeBigEndianDouble(double value) {
		writeDouble(BigEndian, value);
	}
	
	/**
	 * write little-endian short value.
	 * @param value little-endian short value
	 */
	public final void writeLittleEndianShort(short value) {
		writeShort(LittleEndian, value);
	}
	
	/**
	 * write little-endian int value.
	 * @param value little-endian int value
	 */
	public final void writeLittleEndianInt(int value) {
		writeInt(LittleEndian, value);
	}
	
	/**
	 * write little-endian long value.
	 * @param value little-endian long value
	 */
	public final void writeLittleEndianLong(long value) {
		writeLong(LittleEndian, value);
	}
	
	/**
	 * write little-endian float value.
	 * @param value little-endian float value
	 */
	public final void writeLittleEndianFloat(float value) {
		writeFloat(LittleEndian, value);
	}
	
	/**
	 * write little-endian double value.
	 * @param value little-endian double value
	 */
	public final void writeLittleEndianDouble(double value) {
		writeDouble(LittleEndian, value);
	}
	
	private final void writeShort(ByteOrderWriter endian, short value) {
		endian.writeShort(buffer, position, value);
		position += 2;
	}
	
	private final void writeInt(ByteOrderWriter endian, int value) {
		endian.writeInt(buffer, position, value);
		position += 4;
	}
	
	private final void writeLong(ByteOrderWriter endian, long value) {
		endian.writeLong(buffer, position, value);
		position += 8;
	}
	
	private final void writeFloat(ByteOrderWriter endian, float value) {
		endian.writeFloat(buffer, position, value);
		position += 4;
	}
	
	private final void writeDouble(ByteOrderWriter endian, double value) {
		endian.writeDouble(buffer, position, value);
		position += 8;
	}
	
	@SuppressWarnings("unused")
	private final void skip(int numberOfBytes) {
		position += numberOfBytes;
	}
	
	@SuppressWarnings("unused")
	private final void rewind() {
		position = offset;
	}

	private byte [] buffer;
	private int position;
	private int offset;
	private ByteOrderWriter endian;

	private static final class LittleEndianWriter implements ByteOrderWriter {
		@Override
		public void writeShort(byte [] buffer, int offset, short value) {
			buffer[offset+0] = (byte)(value & 0xFF);
			buffer[offset+1] = (byte)((value >> 8) & 0xFF);
		}
		@Override
		public void writeInt(byte [] buffer, int offset, int value) {
			buffer[offset+0] = (byte)(value & 0xFF);
			buffer[offset+1] = (byte)((value >> 8) & 0xFF);
			buffer[offset+2] = (byte)((value >> 16) & 0xFF);
			buffer[offset+3] = (byte)((value >> 24) & 0xFF);
		}
		@Override
		public void writeLong(byte [] buffer, int offset, long value) {
			buffer[offset+0] = (byte)(value & 0xFF);
			buffer[offset+1] = (byte)((value >> 8) & 0xFF);
			buffer[offset+2] = (byte)((value >> 16) & 0xFF);
			buffer[offset+3] = (byte)((value >> 24) & 0xFF);
			buffer[offset+4] = (byte)((value >> 32) & 0xFF);
			buffer[offset+5] = (byte)((value >> 40) & 0xFF);
			buffer[offset+6] = (byte)((value >> 48) & 0xFF);
			buffer[offset+7] = (byte)((value >> 56) & 0xFF);
		}
		@Override
		public void writeFloat(byte [] buffer, int offset, float value) {
			writeInt(buffer, offset, Float.floatToIntBits(value));
		}
		@Override
		public void writeDouble(byte [] buffer, int offset, double value) {
			writeLong(buffer, offset, Double.doubleToLongBits(value));
		}
	}
	
	private static final class BigEndianWriter implements ByteOrderWriter {
		@Override
		public void writeShort(byte [] buffer, int offset, short value) {
			buffer[offset+0] = (byte)((value >> 8) & 0xFF);
			buffer[offset+1] = (byte)(value & 0xFF);
		}
		@Override
		public void writeInt(byte [] buffer, int offset, int value) {
			buffer[offset+0] = (byte)((value >> 24) & 0xFF);
			buffer[offset+1] = (byte)((value >> 16) & 0xFF);
			buffer[offset+2] = (byte)((value >> 8) & 0xFF);
			buffer[offset+3] = (byte)(value & 0xFF);
		}
		@Override
		public void writeLong(byte [] buffer, int offset, long value) {
			buffer[offset+0] = (byte)((value >> 56) & 0xFF);
			buffer[offset+1] = (byte)((value >> 48) & 0xFF);
			buffer[offset+2] = (byte)((value >> 40) & 0xFF);
			buffer[offset+3] = (byte)((value >> 32) & 0xFF);
			buffer[offset+4] = (byte)((value >> 24) & 0xFF);
			buffer[offset+5] = (byte)((value >> 16) & 0xFF);
			buffer[offset+6] = (byte)((value >> 8) & 0xFF);
			buffer[offset+7] = (byte)(value & 0xFF);
		}
		@Override
		public void writeFloat(byte [] buffer, int offset, float value) {
			writeInt(buffer, offset, Float.floatToIntBits(value));
		}
		@Override
		public void writeDouble(byte [] buffer, int offset, double value) {
			writeLong(buffer, offset, Double.doubleToLongBits(value));
		}
	}
	
}
