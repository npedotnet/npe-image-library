/**
 * ImageReader.java
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

package net.npe.image.util;

import java.io.IOException;
import java.io.InputStream;

import net.npe.image.PixelFormat;
import net.npe.image.PixelImage;
import net.npe.image.dds.DdsImage;
import net.npe.image.psd.PsdImage;
import net.npe.image.tga.TgaImage;

/**
 * Helper class for reading images.
 */
public class ImageReader {
	
	/**
	 * create a PixelImage instance from buffer with pixel format.
	 * @param type image type
	 * @param format pixel format
	 * @param buffer byte array buffer
	 * @param offset offset in buffer
	 * @return a PixelImage instance
	 * @throws IOException throws IOException
	 */
	public static PixelImage read(ImageType type, PixelFormat format, byte [] buffer, int offset) throws IOException {
		switch(type) {
		case DDS:
			DdsImage ddsImage = new DdsImage();
			ddsImage.read(buffer, offset, format);  // offset is not yet supported
			return ddsImage;
		case PSD:
			PsdImage psdImage = new PsdImage();
			psdImage.read(buffer, offset, format, false);
			return psdImage;
		case TGA:
			TgaImage tgaImage = new TgaImage();
			tgaImage.read(buffer, offset, format);  // offset is not yet supported
			return tgaImage;
		default:
			throw new IOException("No Support ImageType:"+type.toString());
		}
	}
	
	/**
	 * create a PixelImage instance from InputStream with pixel format.
	 * @param type image type
	 * @param format pixel format
	 * @param is InputStream
	 * @return a PixelImage instance
	 * @throws IOException throws IOException
	 */
	public static PixelImage read(ImageType type, PixelFormat format, InputStream is) throws IOException {
		byte [] buffer = new byte[is.available()];
		return read(type, format, buffer, 0);
	}
	
	public static PixelImage readPsd(PixelFormat format, byte [] buffer, int offset, boolean creatingLayers) throws IOException {
		PsdImage image = new PsdImage();
		image.read(buffer, offset, format, creatingLayers);
		return image;
	}
	
	// TODO read DDS mipmap

}
