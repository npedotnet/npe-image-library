/**
 * ImageWriter.java
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
import java.io.OutputStream;

import net.npe.image.PixelImage;
import net.npe.image.tga.TgaWriter;

/**
 * Helper class for writing images.
 */
public class ImageWriter {
	
	/**
	 * create a byte array buffer of image.
	 * @param type image type
	 * @param image pixel image
	 * @return byte array buffer
	 * @throws IOException throws IOException
	 */
	public static byte [] write(ImageType type, PixelImage image) throws IOException {
		switch(type) {
		case TGA:
			return TgaWriter.write(image.getPixels(), image.getWidth(), image.getHeight(), image.getFormat());
		default:
			throw new IOException("No Support ImageType:"+type.toString());
		}
	}
	
	public static void write(ImageType type, PixelImage image, OutputStream os) throws IOException {
		os.write(write(type, image));
	}
	
	/**
	 * create a byte array buffer of TGA image.
	 * @param image pixel image
	 * @param encode TgaWriter.EncodeType
	 * @return byte array buffer
	 * @throws IOException throws IOException
	 */
	public static byte [] writeTga(PixelImage image, TgaWriter.EncodeType encode) throws IOException {
		return TgaWriter.write(image.getPixels(), image.getWidth(), image.getHeight(), image.getFormat(), encode);
	}

}
