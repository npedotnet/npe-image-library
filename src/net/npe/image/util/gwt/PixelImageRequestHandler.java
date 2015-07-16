/**
 * PixelImageRequestHandler.java
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

import net.npe.image.PixelImage;

public interface PixelImageRequestHandler {
	void onSuccess(PixelImage image);
	void onFailure(String reason);
}
