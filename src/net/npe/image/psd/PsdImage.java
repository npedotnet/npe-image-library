/**
 * PsdImage.java
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

import java.io.IOException;

import net.npe.core.ByteArrayReader;
import net.npe.image.PixelFormat;
import net.npe.image.PixelImage;

public class PsdImage extends PixelImage {
	
	public static final int SIGUNATURE_8BPS = 0x38425053;
	
	public PsdImage() {
		super();
	}
	
	public void read(byte [] buffer, int offset, PixelFormat format, boolean creatingLayers) throws IOException {
		
		ByteArrayReader reader = new ByteArrayReader(buffer, offset, ByteArrayReader.BigEndian);
		
		// sigunature '8BPS'
		sigunature = reader.readInt();
		if(sigunature != SIGUNATURE_8BPS) throw new IOException("test");
		
		// version
		version = reader.readShort();
		
		// skip 6byte of reserved area
		reader.skip(6);
		
		// The number of channels in the image, including any alpha channels.
		// Supported range is 1 to 56.
		channels = reader.readShort();
		
		// The height of the image in pixels. Supported range is 1 to 30,000.
		height = reader.readInt();
		
		// The width of the image in pixels. Supported range is 1 to 30,000.
		width = reader.readInt();
		
		// Depth: the number of bits per channel. Supported values are 1, 8, 16 and 32.
		depth = reader.readShort();
		
		System.out.println("w:"+width+", h:"+height+", d:"+depth);
		
		// The color mode of the file.
		colorMode = PsdColorMode.values()[reader.readShort()];
		
		// end of header 26byte
		
		// ColorModeDataSection
		int colorModeDataSectionLength = reader.readInt();
		
		// skip ColorModeDataSection
		reader.skip(colorModeDataSectionLength);
		
		// ImageResourcesSection
		int imageResourcesSectionLength = reader.readInt();
		
		// skip ImageResourcesSection
		reader.skip(imageResourcesSectionLength);
		
		// LayerAndMaskInformationSection
		int layerAndMaskInformationSectionLength = reader.readInt();
		
		// LayerInfoSectionLength
//		int layerInfoSectionLength = reader.readInt();
		reader.skip(4);
		
		if(creatingLayers) {
			
			int layerCount = reader.readShort();
			
			System.out.println("LayerCount:"+layerCount);
			
			if(layerCount < 0) layerCount = -layerCount;
			
			// create layers
			layers = new PsdLayer[layerCount];
			
			for(int i=0; i<layerCount; i++) {
				layers[i] = new PsdLayer();
				layers[i].readRecords(reader);
			}
			
			for(int i=0; i<layerCount; i++) {
				layers[i].readChannelImageData(reader);
			}
			
		}
		else {
			// skip ImageDataSection
			// layerAndMaskInformationSectionLength - layerInfoSectionLength(int)
			reader.skip(layerAndMaskInformationSectionLength - 4);
		}

		// result data
			
		// compression method
		int compression = reader.readShort();

		if(compression == 1) {
			reader.skip(2*height*channels);
			pixels = new int[width*height];
			
			byte [] outBuffer = new byte[width];
			int [] SHIFTS = {
				format.getRedShift(),
				format.getGreenShift(),
				format.getBlueShift(),
				format.getAlphaShift()
			};
			
			for(int i=0; i<channels; i++) {
				for(int j=0; j<height; j++) {
					PsdDecorder.decodeRunLengthEncoding(reader, outBuffer);
					for(int k=0; k<width; k++) {
						pixels[j*width+k] |= (outBuffer[k] & 0xFF) << SHIFTS[i];
					}
				}
			}

			// fill alpha 0xFF
			if(channels == 3) {
				int alpha = 0xFF << format.getAlphaShift();
				for(int i=0; i<pixels.length; i++) {
					pixels[i] |= alpha;
				}
			}
			
		}
		
	}
	
	public int getSigunature() {
		return sigunature;
	}
	
	public int getVersion() {
		return version;
	}
	
	public int getChannels() {
		return channels;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public PsdColorMode getColorMode() {
		return colorMode;
	}
	
	public PsdLayer [] getLayers() {
		return layers;
	}
	
	private int sigunature;
	private int version;
	private int channels;
	private int depth;
	private PsdColorMode colorMode;
	private PsdLayer [] layers;

}
