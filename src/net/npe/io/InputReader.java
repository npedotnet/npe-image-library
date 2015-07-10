/**
 * InputReader.java
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

public abstract class InputReader {
	
	public interface ByteOrderReader {
		/**
		 * read short value
		 * @param a byte0
		 * @param b byte1
		 * @return short value
		 */
		short readShort(int a, int b);
		/**
		 * read int value
		 * @param a byte0
		 * @param b byte1
		 * @param c byte2
		 * @param d byte3
		 * @return int value
		 */
		int readInt(int a, int b, int c, int d);
		/**
		 * read long value
		 * @param a byte0
		 * @param b byte1
		 * @param c byte2
		 * @param d byte3
		 * @param e byte4
		 * @param f byte5
		 * @param g byte6
		 * @param h byte7
		 * @return long value
		 */
		long readLong(int a, int b, int c, int d, int e, int f, int g, int h);
		/**
		 * read float value
		 * @param a byte0
		 * @param b byte1
		 * @param c byte2
		 * @param d byte3
		 * @return float value
		 */
		float readFloat(int a, int b, int c,  int d);
		/**
		 * read double value
		 * @param a byte0
		 * @param b byte1
		 * @param c byte2
		 * @param d byte3
		 * @param e byte4
		 * @param f byte5
		 * @param g byte6
		 * @param h byte7
		 * @return double value
		 */
		double readDouble(int a, int b, int c, int d, int e, int f, int g, int h);
	}
	
	/** BigEndian Reader */
	public static final ByteOrderReader BigEndian = new BigEndianReader();
	
	/** LittleEndian Reader */
	public static final ByteOrderReader LittleEndian = new LittleEndianReader();
	
	/**
	 * constructor
	 * @param endian default ByteOrderReader (BigEndian or LittleEndian)
	 */
	public InputReader(ByteOrderReader endian) {
		this.endian = endian;
	}
	
	/**
	 * read 1byte
	 * @return byte & 0xFF
	 * @throws IOException io exception
	 */
	public abstract int read() throws IOException;

	/**
	 * mark current position.
	 */
	public abstract void mark();
	
	/**
	 * move to marked position.
	 * @throws IOException I/O exception
	 */
	public abstract void reset() throws IOException;
	
	/**
	 * skip numberOfBytes.
	 * @param numberOfBytes number of bytes
	 * @throws IOException I/O exception
	 */
	public void skip(int numberOfBytes) throws IOException {
		for(int i=0; i<numberOfBytes; i++) read();
	}
	
	/**
	 * read a byte with default byte-order.
	 * @return byte value
	 * @throws IOException I/O exception
	 */
	public byte readByte() throws IOException {
		return (byte)read();
	}
	
	/**
	 * read a short value with default byte-order.
	 * @return short value
	 * @throws IOException I/O exception
	 */
	public short readShort() throws IOException {
		return endian.readShort(read(), read());
	}
	
	/**
	 * read a int value with default byte-order.
	 * @return int value
	 * @throws IOException I/O exception
	 */
	public int readInt() throws IOException {
		return endian.readInt(read(), read(), read(), read());
	}
	
	/**
	 * read a long value with default byte-order.
	 * @return long value
	 * @throws IOException I/O exception
	 */
	public long readLong() throws IOException {
		return endian.readLong(read(), read(), read(), read(), read(), read(), read(), read());
	}
	
	/**
	 * read a float value with default byte-order.
	 * @return float value
	 * @throws IOException I/O exception
	 */
	public float readFloat() throws IOException {
		return endian.readFloat(read(), read(), read(), read());
	}
	
	/**
	 * read a double value with default byte-order.
	 * @return double value
	 * @throws IOException I/O exception
	 */
	public double readDouble() throws IOException {
		return endian.readDouble(read(), read(), read(), read(), read(), read(), read(), read());
	}
	
	/**
	 * read string value.
	 * @param length length of string
	 * @return string value
	 * @throws IOException I/O exception
	 */
	public final String readString(int length) throws IOException {
		byte [] buffer = new byte[length];
		for(int i=0; i<length; i++) buffer[i] = (byte)read();
		String value = new String(buffer, 0, length);
		return value;
	}
	
	/**
	 * read a big-endian short value.
	 * @return big-endian short value
	 * @throws IOException I/O exception
	 */
	public short readBigEndianShort() throws IOException {
		return BigEndian.readShort(read(), read());
	}
	
	/**
	 * read a big-endian int value.
	 * @return big-endian int value
	 * @throws IOException I/O exception
	 */
	public int readBigEndianInt() throws IOException {
		return BigEndian.readInt(read(), read(), read(), read());
	}
	
	/**
	 * read a big-endian long value.
	 * @return big-endian long value
	 * @throws IOException I/O exception
	 */
	public long readBigEndianLong() throws IOException {
		return BigEndian.readLong(read(), read(), read(), read(), read(), read(), read(), read());
	}
	
	/**
	 * read a big-endian float value.
	 * @return big-endian float value
	 * @throws IOException I/O exception
	 */
	public float readBigEndianFloat() throws IOException {
		return BigEndian.readFloat(read(), read(), read(), read());
	}
	
	/**
	 * read a big-endian double value.
	 * @return big-endian double value
	 * @throws IOException I/O exception
	 */
	public double readBigEndianDouble() throws IOException {
		return BigEndian.readDouble(read(), read(), read(), read(), read(), read(), read(), read());
	}

	/**
	 * read a little-endian short value.
	 * @return little-endian short value
	 * @throws IOException I/O exception
	 */
	public short readLittleEndianShort() throws IOException {
		return LittleEndian.readShort(read(), read());
	}
	
	/**
	 * read a little-endian int value
	 * @return little-endian int value
	 * @throws IOException I/O exception
	 */
	public int readLittleEndianInt() throws IOException {
		return LittleEndian.readInt(read(), read(), read(), read());
	}
	
	/**
	 * read a little-endian long value.
	 * @return little-endian long value
	 * @throws IOException I/O exception
	 */
	public long readLittleEndianLong() throws IOException {
		return LittleEndian.readLong(read(), read(), read(), read(), read(), read(), read(), read());
	}
	
	/**
	 * read a little-endian float value.
	 * @return little-endian float value
	 * @throws IOException I/O exception
	 */
	public float readLittleEndianFloat() throws IOException {
		return LittleEndian.readFloat(read(), read(), read(), read());
	}
	
	/**
	 * read a little-endian double value.
	 * @return little-endian double value
	 * @throws IOException I/O exception
	 */
	public double readLittleEndianDouble() throws IOException {
		return LittleEndian.readDouble(read(), read(), read(), read(), read(), read(), read(), read());
	}
	
	protected ByteOrderReader endian;

	private static final class BigEndianReader implements ByteOrderReader {
		@Override
		public short readShort(int a, int b) {
			return (short)(a<<8 | b);
		}
		@Override
		public int readInt(int a, int b, int c, int d) {
			return a<<24 | b<<16 | c<<8 | d;
		}
		@Override
		public long readLong(int a, int b, int c, int d, int e, int f, int g, int h) {
			return a<<56 | b<<48 | c<<40 | d<<32 | e<<24 | f<<16 | g<<8 | h;
		}
		@Override
		public float readFloat(int a, int b, int c, int d) {
			return Float.intBitsToFloat(readInt(a, b, c, d));
		}
		@Override
		public double readDouble(int a, int b, int c, int d, int e, int f, int g, int h) {
			return Double.longBitsToDouble(readLong(a, b, c, d, e, f, g, h));
		}
	}
	
	private static final class LittleEndianReader implements ByteOrderReader {
		@Override
		public short readShort(int a, int b) {
			return (short)(a | b<<8);
		}
		@Override
		public int readInt(int a, int b, int c, int d) {
			return a | b<<8 | c<<16 | d<<24;
		}
		@Override
		public long readLong(int a, int b, int c, int d, int e, int f, int g, int h) {
			return a | b<<8 | c<<16 | d<<24 | e<<32 | f<<40 | g<<48 | h<<56;
		}
		@Override
		public float readFloat(int a, int b, int c, int d) {
			return Float.intBitsToFloat(readInt(a, b, c, d));
		}
		@Override
		public double readDouble(int a, int b, int c, int d, int e, int f, int g, int h) {
			return Double.longBitsToDouble(readLong(a, b, c, d, e, f, g, h));
		}
	}

}
