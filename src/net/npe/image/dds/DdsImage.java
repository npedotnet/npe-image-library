/**
 * DdsImage.java
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

package net.npe.image.dds;

import net.npe.image.PixelFormat;
import net.npe.image.PixelImage;

public class DdsImage extends PixelImage {
	
	public DdsImage() {}
	
	public void read(byte [] buffer, int offset, PixelFormat format) {
		 // offset is not yet supported
		this.width = DdsReader.getWidth(buffer);
		this.height = DdsReader.getHeight(buffer);
		this.pixels = DdsReader.read(buffer, format, 0);
		this.format = format;
	}

}
