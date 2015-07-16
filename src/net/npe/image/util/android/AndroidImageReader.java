/**
 * AndroidImageReader.java
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

package net.npe.image.util.android;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import net.npe.image.PixelFormat;
import net.npe.image.PixelImage;
import net.npe.image.util.ImageReader;
import net.npe.image.util.ImageType;

public final class AndroidImageReader {
	
	/**
	 * read a pixel image from an asset manager with ARGB format.
	 * JPEG/GIF/PNG/BMP/WebP(Android 4.0+), DDS/PSD/TGA
	 * @param path file path
	 * @param assets asset manager
	 * @return pixel image
	 */
	public static PixelImage read(String path, AssetManager assets) {
		return read(path, assets, PixelFormat.ARGB);
	}
	
	/**
	 * read a pixel image from an asset manager.
	 * JPEG/GIF/PNG/BMP/WebP(Android 4.0+), DDS/PSD/TGA
	 * @param path file path
	 * @param assets asset manager
	 * @param format pixel format
	 * @return pixel image
	 */
	public static PixelImage read(String path, AssetManager assets, PixelFormat format) {
		ImageType type = ImageReader.getImageType(path);
		if(type != null) {
			PixelImage image = null;
			InputStream is = null;
			try {
				is = assets.open(path);
				image = ImageReader.readFast(type, format, is);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				if(is != null) {
					try {
						is.close();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			return image;
		}
		else {
			PixelImage image = null;
			InputStream is = null;
			try {
				is = assets.open(path);
				Bitmap bitmap = BitmapFactory.decodeStream(is);
				image = createPixelImage(bitmap);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if(is != null) {
					try {
						is.close();
					}
					catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
			image.changeFormat(format);
			return image;
		}
	}
	
	/**
	 * read a pixel image from a context with ARGB format.
	 * JPEG/GIF/PNG/BMP/WebP(Android 4.0+), DDS/PSD/TGA
	 * @param path file path
	 * @param context context
	 * @return pixel image
	 */
	public static PixelImage read(String path, Context context) {
		return read(path, context, PixelFormat.ARGB);
	}
	
	/**
	 * read a pixel image from a context.
	 * JPEG/GIF/PNG/BMP/WebP(Android 4.0+), DDS/PSD/TGA
	 * @param path file path
	 * @param context context
	 * @param format pixel format
	 * @return pixel image
	 */
	public static PixelImage read(String path, Context context, PixelFormat format) {
		ImageType type = ImageReader.getImageType(path);
		if(type != null) {
			PixelImage image = null;
			InputStream is = null;
			try {
				is = context.openFileInput(path);
				image = ImageReader.readFast(type, format, is);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				if(is != null) {
					try {
						is.close();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			return image;
		}
		else {
			PixelImage image = null;
			InputStream is = null;
			try {
				is = context.openFileInput(path);
				Bitmap bitmap = BitmapFactory.decodeStream(is);
				image = createPixelImage(bitmap);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if(is != null) {
					try {
						is.close();
					}
					catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
			image.changeFormat(format);
			return image;
		}
	}
	
	/**
	 * create a bitmap from an asset manager.
	 * JPEG/GIF/PNG/BMP/WebP(Android 4.0+), DDS/PSD/TGA
	 * @param path file path
	 * @param assets asset manager
	 * @return bitmap
	 */
	public static Bitmap createBitmap(String path, AssetManager assets) {
		ImageType type = ImageReader.getImageType(path);
		if(type != null) {
			Bitmap bitmap = null;
			InputStream is = null;
			try {
				is = assets.open(path);
				PixelImage image = ImageReader.readFast(type, PixelFormat.ARGB, is);
				bitmap = createBitmap(image);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				if(is != null) {
					try {
						is.close();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			return bitmap;
		}
		else {
			Bitmap bitmap = null;
			InputStream is = null;
			try {
				is = assets.open(path);
				bitmap = BitmapFactory.decodeStream(is);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if(is != null) {
					try {
						is.close();
					}
					catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
			return bitmap;
		}
	}
	
	/**
	 * create a bitmap from a context.
	 * JPEG/GIF/PNG/BMP/WebP(Android 4.0+), DDS/PSD/TGA
	 * @param path file path
	 * @param context context
	 * @return bitmap
	 */
	public static Bitmap createBitmap(String path, Context context) {
		ImageType type = ImageReader.getImageType(path);
		if(type != null) {
			Bitmap bitmap = null;
			InputStream is = null;
			try {
				is = context.openFileInput(path);
				PixelImage image = ImageReader.readFast(type, PixelFormat.ARGB, is);
				bitmap = createBitmap(image);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				if(is != null) {
					try {
						is.close();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			return bitmap;
		}
		else {
			Bitmap bitmap = null;
			InputStream is = null;
			try {
				is = context.openFileInput(path);
				bitmap = BitmapFactory.decodeStream(is);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				if(is != null) {
					try {
						is.close();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			return bitmap;
		}
	}
	
	/**
	 * create a bitmap from a pixel image.
	 * @param image pixel image
	 * @return bitmap
	 */
	public static Bitmap createBitmap(PixelImage image) {
		image.changeFormat(PixelFormat.ARGB);
		int [] pixels = image.getPixels();
		int width = image.getWidth();
		int height = image.getHeight();
		return Bitmap.createBitmap(pixels, 0, width, width, height, Config.ARGB_8888);
	}
	
	/**
	 * create a pixel image from a bitmap.
	 * @param bitmap bitmap
	 * @return pixel image
	 */
	public static PixelImage createPixelImage(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		int [] pixels = new int[width*height];
		bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
		return new PixelImage(pixels, width, height, PixelFormat.ARGB);
	}

}
