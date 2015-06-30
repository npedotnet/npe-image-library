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
	public static final PixelFormat ARGB = new PixelFormat(16, 8, 0, 24);
	
	/**
	 *  ABGR format
	 *  <p>
	 *  for OpenGL Texture (GL_RGBA).
	 *  </p>
	 */
	public static final PixelFormat ABGR = new PixelFormat(0, 8, 16, 24);

	/**
	 * get red shift value.
	 * @return red shift value
	 */
	public int getRedShift() { return redShift; }
	
	/**
	 * get green shift value.
	 * @return green shift value
	 */
	public int getGreenShift() { return greenShift; }
	
	/**
	 * get blue shift value.
	 * @return blue shift value
	 */
	public int getBlueShift() { return blueShift; }
	
	/**
	 * get alpha shift value.
	 * @return alpha shift value
	 */
	public int getAlphaShift() { return alphaShift; }

	private PixelFormat(int redShift, int greenShift, int blueShift, int alphaShift) {
		this.redShift = redShift;
		this.greenShift = greenShift;
		this.blueShift = blueShift;
		this.alphaShift = alphaShift;
	}
	
	private int redShift;
	private int greenShift;
	private int blueShift;
	private int alphaShift;
	// TODO mask
//	private int redMask;
//	private int greenMask;
//	private int blueMask;
//	private int alphaMask;

}
