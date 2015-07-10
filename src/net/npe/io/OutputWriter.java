/**
 * OutputWriter.java
 * 
 * Copyright (c) 2015 Kenji Sasaki
 * Released under the MIT license.
 * https://github.com/npedotnet/npe-library/blob/master/LICENSE
 * 
 * This file is a part of npe-library.
 * https://github.com/npedotnet/npe-library
 * 
 */

package net.npe.io;

import java.io.IOException;

public abstract class OutputWriter {
	
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
	
	/** BigEndian Writer */
	public static final ByteOrderWriter BigEndian = new BigEndianWriter();
	
	/** LittleEndian Writer */
	public static final ByteOrderWriter LittleEndian = new LittleEndianWriter();
	
	public OutputWriter(ByteOrderWriter endian) {
		this.endian = endian;
	}
	
	/**
	 * write 1-byte value.
	 * @param byteValue
	 * @throws IOException
	 */
	public abstract void write(int byteValue) throws IOException;
	
	/**
	 * write byte array buffer.
	 * @param buffer
	 * @param offset
	 * @param length
	 * @throws IOException
	 */
	public void write(byte [] buffer, int offset, int length) throws IOException {
		for(int i=0; i<length; i++) write(buffer[i+offset]);
	}
	
	/**
	 * write a byte value.
	 * @param value byte value
	 * @throws IOException I/O exception
	 */
	public final void writeByte(byte value) throws IOException {
		write(value);
	}
	
	/**
	 * write a short value.
	 * @param value short value
	 * @throws IOException I/O exception
	 */
	public final void writeShort(short value) throws IOException {
		endian.writeShort(works, 0, value);
		write(works, 0, 2);
	}
	
	/**
	 * write a int value.
	 * @param value int value
	 * @throws IOException I/O exception
	 */
	public final void writeInt(int value) throws IOException {
		endian.writeInt(works, 0, value);
		write(works, 0, 4);
	}
	
	/**
	 * write a long value.
	 * @param value long value
	 * @throws IOException I/O exception
	 */
	public final void writeLong(long value) throws IOException {
		endian.writeLong(works, 0, value);
		write(works, 0, 8);
	}
	
	/**
	 * write a float value.
	 * @param value float value
	 * @throws IOException I/O exception
	 */
	public final void writeFloat(float value) throws IOException {
		endian.writeFloat(works, 0, value);
		write(works, 0, 4);
	}
	
	/**
	 * write a double value.
	 * @param value double value
	 * @throws IOException I/O exception
	 */
	public final void writeDouble(double value) throws IOException {
		endian.writeDouble(works, 0, value);
		write(works, 0, 8);
	}

	// TODO writeString
	
	/**
	 * write a big-endian short value.
	 * @param value big-endian short value
	 * @throws IOException I/O exception
	 */
	public final void writeBigEndianShort(short value) throws IOException {
		BigEndian.writeShort(works, 0, value);
		write(works, 0, 2);
	}
	
	/**
	 * write a big-endian int value.
	 * @param value big-endian int value
	 * @throws IOException I/O exception
	 */
	public final void writeBigEndianInt(int value) throws IOException {
		BigEndian.writeInt(works, 0, value);
		write(works, 0, 4);
	}
	
	/**
	 * write a big-endian long value.
	 * @param value big-endian long value
	 * @throws IOException I/O exception
	 */
	public final void writeBigEndianLong(long value) throws IOException {
		BigEndian.writeLong(works, 0, value);
		write(works, 0, 8);
	}
	
	/**
	 * write a big-endian float value.
	 * @param value big-endian float value
	 * @throws IOException I/O exception
	 */
	public final void writeBigEndianFloat(float value) throws IOException {
		BigEndian.writeFloat(works, 0, value);
		write(works, 0, 4);
	}
	
	/**
	 * write a big-endian double value.
	 * @param value big-endian double value
	 * @throws IOException I/O exception
	 */
	public final void writeBigEndianDouble(double value) throws IOException {
		BigEndian.writeDouble(works, 0, value);
		write(works, 0, 8);
	}
	
	/**
	 * write a little-endian short value.
	 * @param value little-endian short value
	 * @throws IOException I/O exception
	 */
	public final void writeLittleEndianShort(short value) throws IOException {
		LittleEndian.writeShort(works, 0, value);
		write(works, 0, 2);
	}
	
	/**
	 * write a little-endian int value.
	 * @param value little-endian int value
	 * @throws IOException I/O exception
	 */
	public final void writeLittleEndianInt(int value) throws IOException {
		LittleEndian.writeInt(works, 0, value);
		write(works, 0, 4);
	}
	
	/**
	 * write a little-endian long value.
	 * @param value little-endian long value
	 * @throws IOException I/O exception
	 */
	public final void writeLittleEndianLong(long value) throws IOException {
		LittleEndian.writeLong(works, 0, value);
		write(works, 0, 8);
	}
	
	/**
	 * write a little-endian float value.
	 * @param value little-endian float value
	 * @throws IOException I/O exception
	 */
	public final void writeLittleEndianFloat(float value) throws IOException {
		LittleEndian.writeFloat(works, 0, value);
		write(works, 0, 4);
	}
	
	/**
	 * write a little-endian double value.
	 * @param value little-endian double value
	 * @throws IOException I/O exception
	 */
	public final void writeLittleEndianDouble(double value) throws IOException {
		LittleEndian.writeDouble(works, 0, value);
		write(works, 0, 8);
	}
	
	protected ByteOrderWriter endian;
	
	private byte [] works = new byte[8];
	
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

}
