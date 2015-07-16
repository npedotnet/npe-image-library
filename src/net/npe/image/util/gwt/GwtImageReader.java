/**
 * GwtImageReader.java
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

package net.npe.image.util.gwt;

import java.io.IOException;

import net.npe.image.PixelFormat;
import net.npe.image.PixelImage;
import net.npe.image.dds.DdsReader;
import net.npe.image.tga.TgaReader;
import net.npe.image.util.ImageReader;
import net.npe.image.util.ImageType;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CanvasPixelArray;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.typedarrays.client.Uint8ArrayNative;
import com.google.gwt.typedarrays.shared.ArrayBuffer;
import com.google.gwt.xhr.client.ReadyStateChangeHandler;
import com.google.gwt.xhr.client.XMLHttpRequest;
import com.google.gwt.xhr.client.XMLHttpRequest.ResponseType;

public final class GwtImageReader {
	
	public static void read(final String url, final PixelImageRequestHandler handler) {
		XMLHttpRequest xhr = XMLHttpRequest.create();
		xhr.setResponseType(ResponseType.ArrayBuffer);
		xhr.setOnReadyStateChange(new ReadyStateChangeHandler() {
			@Override
			public void onReadyStateChange(XMLHttpRequest xhr) {
				if(xhr.getReadyState() == XMLHttpRequest.DONE) {
					if(xhr.getStatus() >= 400) {
						handler.onFailure(xhr.getStatusText());
					}
					else {
						ImageType type = ImageReader.getImageType(url);
						if(type != null) {
							ArrayBuffer arrayBuffer = xhr.getResponseArrayBuffer();
							try {
								PixelImage image = read(arrayBuffer, type);
								handler.onSuccess(image);
							}
							catch(Exception e) {
								e.printStackTrace();
							}
						}
						else {
							handler.onFailure("Unsupport image format: "+url);
						}
					}
				}
			}
		});
		xhr.open("GET", url);
	}
	
	public static PixelImage read(ArrayBuffer arrayBuffer, ImageType type) throws IOException {
		Uint8ArrayNative u8array = Uint8ArrayNative.create(arrayBuffer);
		byte [] buffer = new byte[u8array.length()];
		for(int i=0; i<buffer.length; i++) {
			buffer[i] = (byte)u8array.get(i);
		}
		return read(buffer, type);
	}

	public static PixelImage read(byte [] buffer, ImageType type) throws IOException {
		if(type == ImageType.DDS) {
			int [] pixels = DdsReader.read(buffer, PixelFormat.ABGR, 0);
			int width = DdsReader.getWidth(buffer);
			int height = DdsReader.getHeight(buffer);
			return new PixelImage(pixels, width, height, PixelFormat.ABGR);
		}
		else if(type == ImageType.PSD) {
			return ImageReader.read(type, PixelFormat.ABGR, buffer, 0);
		}
		else if(type == ImageType.TGA) {
			try {
				int [] pixels = TgaReader.read(buffer, PixelFormat.ABGR);
				int width = TgaReader.getWidth(buffer);
				int height = TgaReader.getHeight(buffer);
				return new PixelImage(pixels, width, height, PixelFormat.ABGR);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static void createCanvas(final String url, final CanvasRequestHandler handler) {
		XMLHttpRequest xhr = XMLHttpRequest.create();
		xhr.setResponseType(ResponseType.ArrayBuffer);
		xhr.setOnReadyStateChange(new ReadyStateChangeHandler() {
			@Override
			public void onReadyStateChange(XMLHttpRequest xhr) {
				if(xhr.getReadyState() == XMLHttpRequest.DONE) {
					if(xhr.getStatus() >= 400) {
						handler.onFailure(xhr.getStatusText());
					}
					else {
						ImageType type = ImageReader.getImageType(url);
						if(type != null) {
							ArrayBuffer arrayBuffer = xhr.getResponseArrayBuffer();
							try {
								Canvas canvas = createCanvas(arrayBuffer, type);
								handler.onSuccess(canvas);
							}
							catch(Exception e) {
								e.printStackTrace();
							}
						}
						else {
							handler.onFailure("Unsupport image format: "+url);
						}
					}
				}
			}
		});
		xhr.open("GET", url);
	}

	public static Canvas createCanvas(ArrayBuffer arrayBuffer, ImageType type) throws IOException {
		Uint8ArrayNative u8array = Uint8ArrayNative.create(arrayBuffer);
		byte [] buffer = new byte[u8array.length()];
		for(int i=0; i<buffer.length; i++) {
			buffer[i] = (byte)u8array.get(i);
		}
		return createCanvas(buffer, type);
	}
	
	public static Canvas createCanvas(byte [] buffer, ImageType type) throws IOException {
		if(type == ImageType.DDS) {
			int [] pixels = DdsReader.read(buffer, PixelFormat.ABGR, 0);
			int width = DdsReader.getWidth(buffer);
			int height = DdsReader.getHeight(buffer);
			return createCanvas(pixels, width, height);
		}
		else if(type == ImageType.PSD) {
			PixelImage image = ImageReader.read(type, PixelFormat.ABGR, buffer, 0);
			int [] pixels = image.getPixels();
			int width = image.getWidth();
			int height = image.getHeight();
			return createCanvas(pixels, width, height);
		}
		else if(type == ImageType.TGA) {
			try {
				int [] pixels = TgaReader.read(buffer, PixelFormat.ABGR);
				int width = TgaReader.getWidth(buffer);
				int height = TgaReader.getHeight(buffer);
				return createCanvas(pixels, width, height);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static Canvas createCanvas(int [] pixels, int width, int height) {
		
		Canvas canvas = Canvas.createIfSupported();
		
		if(canvas == null) return null;
		
		canvas.setCoordinateSpaceWidth(width);
		canvas.setCoordinateSpaceHeight(height);
		
		Context2d context = canvas.getContext2d();
		ImageData data = context.createImageData(width, height);

		CanvasPixelArray array = data.getData();
		for(int i=0; i<width*height; i++) { // ABGR
			array.set(4*i+0, pixels[i] & 0xFF);
			array.set(4*i+1, (pixels[i] >> 8) & 0xFF);
			array.set(4*i+2, (pixels[i] >> 16) & 0xFF);
			array.set(4*i+3, (pixels[i] >> 24) & 0xFF);
		}
		context.putImageData(data, 0, 0);
		
		return canvas;
		
	}

}
