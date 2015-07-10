/**
 * StreamReader.java
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
import java.io.InputStream;

public class StreamReader extends InputReader {
	
	public StreamReader(InputStream stream, ByteOrderReader endian) {
		super(endian);
		this.stream = stream;
	}
	
	@Override
	public int read() throws IOException {
		return stream.read();
	}
	
	@Override
	public void mark() {
		stream.mark(0);
	}
	
	@Override
	public void reset() throws IOException {
		stream.reset();
	}
	
	@Override
	public void skip(int numberOfBytes) throws IOException {
		stream.skip(numberOfBytes);
	}
	
	private InputStream stream;

}
