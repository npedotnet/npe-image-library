/**
 * npeimage.h
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

#ifndef _NPE_IMAGE_H_
#define _NPE_IMAGE_H_

#include "net/npe/image/PixelFormat.h"
#include "net/npe/image/PixelImage.h"

#include "net/npe/image/dds/DdsImage.h"
#include "net/npe/image/dds/DdsReader.h"

#include "net/npe/image/psd/PsdChannel.h"
#include "net/npe/image/psd/PsdColorMode.h"
#include "net/npe/image/psd/PsdDecorder.h"
#include "net/npe/image/psd/PsdImage.h"
#include "net/npe/image/psd/PsdLayer.h"

#include "net/npe/image/tga/TgaImage.h"
#include "net/npe/image/tga/TgaReader.h"
#include "net/npe/image/tga/TgaWriter.h"

#include "net/npe/image/util/ImageReader.h"
#include "net/npe/image/util/ImageType.h"
#include "net/npe/image/util/ImageWriter.h"

static inline void npeInitializeImageLibrary() {
  NetNpeImagePixelFormat_initialize();
  NetNpeImagePixelImage_initialize();
  NetNpeImageDdsDdsImage_initialize();
  NetNpeImageDdsDdsReader_initialize();
  NetNpeImagePsdPsdChannel_initialize();
  NetNpeImagePsdPsdColorModeEnum_initialize();
  NetNpeImagePsdPsdDecorder_initialize();
  NetNpeImagePsdPsdImage_initialize();
  NetNpeImagePsdPsdLayer_initialize();
  NetNpeImageTgaTgaImage_initialize();
  NetNpeImageTgaTgaReader_initialize();
  NetNpeImageTgaTgaWriter_initialize();
  NetNpeImageUtilImageReader_initialize();
  NetNpeImageUtilImageTypeEnum_initialize();
  NetNpeImageUtilImageWriter_initialize();
}

#endif /* _NPE_IMAGE_H_ */
