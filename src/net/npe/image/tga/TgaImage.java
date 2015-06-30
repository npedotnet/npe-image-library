/**
 * TgaImage.java
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

package net.npe.image.tga;

import java.io.IOException;

import net.npe.image.PixelFormat;
import net.npe.image.PixelImage;

public class TgaImage extends PixelImage {
	
	public TgaImage() {
	}
	
	public void read(byte [] buffer, int offset, PixelFormat format) throws IOException {
		 // offset is not yet supported
		this.width = TgaReader.getWidth(buffer);
		this.height = TgaReader.getHeight(buffer);
		this.pixels = TgaReader.read(buffer, format);
		this.format = format;
	}

}
