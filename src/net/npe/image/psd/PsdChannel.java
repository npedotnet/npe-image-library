/**
 * PsdChannel.java
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

package net.npe.image.psd;

import java.io.IOException;

import net.npe.io.InputReader;

public class PsdChannel {
	
	// TODO enum MaskType
	public static final int RED = 0;
	public static final int GREEN = 1;
	public static final int BLUE = 2;
	public static final int ALPHA = -1;
	public static final int USER_MASK = -2;
	public static final int USER_MASK2 = -3;
	
	public PsdChannel() {}
	
	public void readInformation(InputReader reader) throws IOException {
		type = reader.readShort();
		length = reader.readInt();
	}
	
	public void read(InputReader reader, int width, int height) throws IOException {
		int compression = reader.readShort();
		switch(compression) {
		case 0: // raw
			data = new byte[width*height];
			PsdDecorder.readRaw(reader, data);
			break;
		case 1: // RLE
			data = new byte[width*height];
			reader.skip(2*height);
			PsdDecorder.decodeRunLengthEncoding(reader, data);
			break;
		default: break;
		}
	}
	
	public int getType() { return type; }
	public int getLength() { return length; }
	public byte [] getData() { return data; }

	private int type;
	private int length;
	private byte [] data;
	
}
