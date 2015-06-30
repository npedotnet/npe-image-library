/**
 * PixelImage.java
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

package net.npe.image;

public class PixelImage {
	
	/**
	 * constructor.
	 * @param pixels image pixels
	 * @param width image width
	 * @param height image height
	 * @param format pixel format
	 */
	public PixelImage(int [] pixels, int width, int height, PixelFormat format) {
		this.pixels = pixels;
		this.width = width;
		this.height = height;
		this.format = format;
	}
	
	/**
	 * get pixel array.
	 * @return pixel array
	 */
	public int [] getPixels() { return pixels; }
	
	/**
	 * get image width.
	 * @return image width
	 */
	public int getWidth() { return width; }
	
	/**
	 * get image height.
	 * @return image height
	 */
	public int getHeight() { return height; }
	
	/**
	 * get pixel format.
	 * @return pixel format
	 */
	public PixelFormat getFormat() { return format; }
	
	/**
	 * change format (not yet implemented)
	 * @param format pixel format
	 */
	public void changeFormat(PixelFormat format) {
		// TODO implementation
	}

	/**
	 * multiply alpha (not yet implemented)
	 * @param alpha alpha is 0.0 to 1.0
	 */
	public void multiplyAlpha(float alpha) {
		// TODO implementation
	}
	
	/**
	 * multiply alpha (not yet implemented)
	 * @param alpha alpha is 0 to 255
	 */
	public void multiplyAlpha(int alpha) {
		multiplyAlpha(alpha / 255.f);
	}
	
	protected PixelImage() {
		this(null, 0, 0, null);
	}
	
	protected int [] pixels;
	protected int width;
	protected int height;
	protected PixelFormat format;

}
