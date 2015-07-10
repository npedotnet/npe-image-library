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
	 * change format
	 * @param format pixel format
	 */
	public void changeFormat(PixelFormat format) {
		if(this.format != format) {
			for(int i=0; i<width*height; i++) {
				int red = this.format.getRed(pixels[i]);
				int green = this.format.getGreen(pixels[i]);
				int blue = this.format.getBlue(pixels[i]);
				int alpha = this.format.getAlpha(pixels[i]);
				pixels[i] = format.getPixel(red, green, blue, alpha);
			}
			this.format = format;
		}
	}

	/**
	 * multiply alpha
	 * @param alpha alpha is 0.0 to 1.0
	 */
	public void multiplyAlpha(float alpha) {
		int rgbMask = ~format.getAlphaMask();
		for(int i=0; i<width*height; i++) {
			int a = (int)(255 * alpha * (format.getAlpha(pixels[i]) / 255.f));
			pixels[i] = (pixels[i] & rgbMask) | (a << format.getAlphaShift());
		}
	}
	
	/**
	 * multiply alpha
	 * @param alpha alpha is 0 to 255
	 */
	public void multiplyAlpha(int alpha) {
		multiplyAlpha(alpha / 255.f);
	}
	
	/**
	 * set alpha
	 * @param alpha alpha is 0 to 255
	 */
	public void setAlpha(int alpha) {
		int rgbMask = ~format.getAlphaMask();
		for(int i=0; i<width*height; i++) {
			pixels[i] = (pixels[i] & rgbMask) | (alpha << format.getAlphaShift());
		}
	}
	
	/**
	 * remove transparency.
	 */
	public void removeTransparency() {
		int alphaMask = format.getAlphaMask();
		int rgbMask = ~alphaMask;
		for(int i=0; i<width*height; i++) {
			pixels[i] = (pixels[i] & rgbMask) | alphaMask;
		}
	}
	
	protected PixelImage() {
		this(null, 0, 0, null);
	}
	
	protected int [] pixels;
	protected int width;
	protected int height;
	protected PixelFormat format;

}
