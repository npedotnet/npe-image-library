/**
 * ByteArrayWriter.java
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

public class ByteArrayWriter extends OutputWriter {
	
	/**
	 * constructor
	 * @param buffer byte array buffer
	 * @param offset offset in buffer
	 * @param endian default ByteOrderWriter (LittleEndian or BigEndian)
	 */
	public ByteArrayWriter(byte [] buffer, int offset, ByteOrderWriter endian) {
		super(endian);
		this.buffer = buffer;
		position = offset;
	}
	
	@Override
	public void write(int byteValue) {
		buffer[position++] = (byte)byteValue;
	}

	private byte [] buffer;
	private int position;
	
}
