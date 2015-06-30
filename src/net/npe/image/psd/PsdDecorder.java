/**
 * PsdDecorder.java
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

import net.npe.core.ByteArrayReader;

public class PsdDecorder {
	
	static void readRaw(ByteArrayReader reader, byte [] outBuffer) {
		for(int i=0; i<outBuffer.length; i++) {
			outBuffer[i] = reader.readByte();
		}
	}
	
	static void decodeRunLengthEncoding(ByteArrayReader reader, byte [] outBuffer) {
		for(int i=0; i<outBuffer.length; ) {
			byte packet = reader.readByte();
			if((packet & 0x80) != 0) {
				int count = 1 - (int)packet;
				byte value = reader.readByte();
				for(int j=0; j<count; j++) {
					outBuffer[i++] = value;
				}
			}
			else {
				int count = packet + 1;
				for(int j=0; j<count; j++) {
					outBuffer[i++] = reader.readByte();
				}
			}
		}
	}

}
