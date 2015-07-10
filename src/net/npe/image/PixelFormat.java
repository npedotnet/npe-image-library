/**
 * PixelFormat.java
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

public final class PixelFormat {
	
	/**
	 *  ARGB format
	 *  <p>
	 *  for java.awt.image.BufferedImage and android.graphics.Bitmap.
	 *  </p>
	 */
	public static final PixelFormat ARGB = new PixelFormat(16, 8, 0, 24, 0x00FF0000, 0x0000FF00, 0x000000FF, 0xFF000000);
	
	/**
	 *  ABGR format
	 *  <p>
	 *  for OpenGL Texture (GL_RGBA).
	 *  </p>
	 */
	public static final PixelFormat ABGR = new PixelFormat(0, 8, 16, 24, 0x000000FF, 0x0000FF00, 0x00FF0000, 0xFF000000);

	/**
	 * get red shift value.
	 * @return red shift value
	 */
	public final int getRedShift() {
		return redShift;
	}
	
	/**
	 * get green shift value.
	 * @return green shift value
	 */
	public final int getGreenShift() {
		return greenShift;
	}
	
	/**
	 * get blue shift value.
	 * @return blue shift value
	 */
	public final int getBlueShift() {
		return blueShift;
	}
	
	/**
	 * get alpha shift value.
	 * @return alpha shift value
	 */
	public final int getAlphaShift() {
		return alphaShift;
	}

	/**
	 * get red mask.
	 * @return red mask
	 */
	public final int getRedMask() {
		return redMask;
	}
	
	/**
	 * get green mask
	 * @return green mask
	 */
	public final int getGreenMask() {
		return greenMask;
	}
	
	/**
	 * get blue mask
	 * @return blue mask
	 */
	public final int getBlueMask() {
		return blueMask;
	}
	
	/**
	 * get alpha mask
	 * @return alpha mask
	 */
	public final int getAlphaMask() {
		return alphaMask;
	}
	
	/**
	 * get a red value from the pixel.
	 * @param pixel the pixel
	 * @return red value
	 */
	public final int getRed(int pixel) {
		return (pixel & redMask) >> redShift;
	}
	
	/**
	 * get a green value from the pixel.
	 * @param pixel the pixel
	 * @return green value
	 */
	public final int getGreen(int pixel) {
		return (pixel & greenMask) >> greenShift;
	}
	
	/**
	 * get a blue value from the pixel.
	 * @param pixel the pixel
	 * @return blue value
	 */
	public final int getBlue(int pixel) {
		return (pixel & blueMask) >> blueShift;
	}
	
	/**
	 * get a alpha value from the pixel.
	 * @param pixel the pixel
	 * @return alpha value
	 */
	public final int getAlpha(int pixel) {
		return (pixel & alphaMask) >> alphaShift;
	}
	
	/**
	 * get a pixel from red, green, blue and alpha values.
	 * @param red the red value
	 * @param green the green value
	 * @param blue the blue value
	 * @param alpha the alpha value
	 * @return a pixel
	 */
	public final int getPixel(int red, int green, int blue, int alpha) {
		return (red<<redShift)|(green<<greenShift)|(blue<<blueShift)|(alpha<<alphaShift);
	}

	private PixelFormat(int redShift, int greenShift, int blueShift, int alphaShift, int redMask, int greenMask, int blueMask, int alphaMask) {
		this.redShift = redShift;
		this.greenShift = greenShift;
		this.blueShift = blueShift;
		this.alphaShift = alphaShift;
		this.redMask = redMask;
		this.greenMask = greenMask;
		this.blueMask = blueMask;
		this.alphaMask = alphaMask;
	}
	
	private int redShift;
	private int greenShift;
	private int blueShift;
	private int alphaShift;
	private int redMask;
	private int greenMask;
	private int blueMask;
	private int alphaMask;

}
