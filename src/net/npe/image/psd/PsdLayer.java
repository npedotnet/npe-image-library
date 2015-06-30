/**
 * PsdLayer.java
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

package net.npe.image.psd;

import net.npe.core.ByteArrayReader;
import net.npe.image.PixelFormat;
import net.npe.image.PixelImage;

public class PsdLayer {
	
	private static final int SIGUNATURE_8BIM = 0x3842494D;
	private static final int SIGUNATURE_8B64 = 0x38423634;
	
	public PsdLayer() {
	}
	
	public void readRecords(ByteArrayReader reader) {
		
		top = reader.readInt();
		left = reader.readInt();
		bottom = reader.readInt();
		right = reader.readInt();
		
		System.out.println("t:"+top+", l:"+left+", b:"+bottom+", r:"+right);
		
		int channelCount = reader.readShort();
		
		channels = new PsdChannel[channelCount];
		
		// Channel information
		for(int i=0; i<channelCount; i++) {
			channels[i] = new PsdChannel();
			channels[i].readInformation(reader);
		}
		
		// blend mode sigunature '8BIM'
		reader.skip(4);
		
		// blend mode key
		blendModeKey = reader.readInt();
		
		// opacity
		opacity = reader.readByte() & 0xFF;
		System.out.println("opacity:"+opacity);
		
		// clipping
		clipping = reader.readByte();
		System.out.println("clipping:"+clipping);
		
		// flags
		flags = reader.readByte();
		System.out.println("flags:"+flags);
		
		// filler(zero) 1byte
		reader.skip(1);
		
		// Length of the extra data field
		int extraLength = reader.readInt();
		System.out.println("ExtraLength:"+extraLength);
		
		// Layer mask / adjustment layer data
		int layerMaskLength = reader.readInt();
		
		// skip layerMask
		reader.skip(layerMaskLength);
		
		// Layer blending ranges data
		int layerBlendingLength = reader.readInt();
		
		// skip layerBlendingRangesData
		reader.skip(layerBlendingLength);
		
		// Layer name(Pascal String), padded to a multiple of 4 bytes.
		int position = reader.getPosition();
		int layerNameLength = reader.readByte() & 0xFF;
		name = reader.readString(layerNameLength);
		System.out.println("LayerName["+layerNameLength+"]:"+name);
		
		position += 4*((1+layerNameLength+3)/4);
		reader.setPosition(position);
		
		// Additional Layer Information
		int sigunature = reader.readInt();
		System.out.println("sigunature:"+sigunature);
		
		// check if additional layer information is exist
		if(sigunature == SIGUNATURE_8BIM || sigunature == SIGUNATURE_8B64) {
			
			// a 4-character code
//			int code = reader.readInt();
			reader.skip(4);
			
			// Length data below, rounded up to an even byte count.
			int length = reader.readInt();
			System.out.println("ALI Length:"+length);
			
			// skip Data
			reader.skip(length);
		}
		else {
			// rewind 4byte
			reader.skip(-4);
		}
		
	}
	
	public void readChannelImageData(ByteArrayReader reader) {
		
		int width = getWidth();
		int height = getHeight();
		for(int i=0; i<channels.length; i++) {
			channels[i].read(reader, width, height);
		}
		
	}
	
	public int getTop() { return top; }
	public int getLeft() { return left; }
	public int getBottom() { return bottom; }
	public int getRight() { return right; }
	public int getBlendModeKey() { return blendModeKey; }
	public int getOpacity() { return opacity; }
	public int getClipping() { return clipping; }
	public int getFlags() { return flags; }
	public String getName() { return name; }
	public PsdChannel [] getChannels() { return channels; }
	
	public boolean isTransparencyProtected() { return (flags & 0x01) != 0; }
	public boolean isInvisible() { return (flags & 0x02) != 0; }
	
	public int getWidth() { return right - left; }
	public int getHeight() { return bottom - top; }
	
	public PsdChannel getChannel(int channelType) {
		for(PsdChannel channel : channels) {
			if(channel.getType() == channelType) return channel;
		}
		return null;
	}
	
	public boolean hasChannel(int channelType) {
		return getChannel(channelType) != null;
	}
	
	public int [] createPixels(PixelFormat order) {
		
		int width = getWidth();
		int height = getHeight();
		int [] pixels = new int[width*height];
		
		PsdChannel redChannel = getChannel(PsdChannel.RED);
		PsdChannel greenChannel = getChannel(PsdChannel.GREEN);
		PsdChannel blueChannel = getChannel(PsdChannel.BLUE);
		PsdChannel alphaChannel = getChannel(PsdChannel.ALPHA);
		
		byte [] redData = (redChannel != null) ? redChannel.getData() : null;
		byte [] greenData = (greenChannel != null) ? greenChannel.getData() : null;
		byte [] blueData = (blueChannel != null) ? blueChannel.getData() : null;
		byte [] alphaData = (alphaChannel != null) ? alphaChannel.getData() : null;
		
		int redShift = order.getRedShift();
		int greenShift = order.getGreenShift();
		int blueShift = order.getBlueShift();
		int alphaShift = order.getAlphaShift();
		
		for(int i=0; i<pixels.length; i++) {
			int r = (redData != null) ? (redData[i]&0xFF) : 0x00;
			int g = (greenData != null) ? (greenData[i]&0xFF) : 0x00;
			int b = (blueData != null) ? (blueData[i]&0xFF) : 0x00;
			int a = (alphaData != null) ? (alphaData[i]&0xFF) : 0xFF;
			pixels[i] = (r<<redShift) | (g<<greenShift) | (b<<blueShift) | (a<<alphaShift);
		}
		
		return pixels;
		
	}
	
	public PixelImage createImage(PixelFormat order) {
		
		int [] pixels = createPixels(order);
		int width = getWidth();
		int height = getHeight();
		
		return new PixelImage(pixels, width, height, order);
		
	}
	
	private int top;
	private int left;
	private int bottom;
	private int right;
	private int blendModeKey;
	private int opacity;
	private int clipping;
	private int flags;
	private String name;
	
	private PsdChannel [] channels;

}
