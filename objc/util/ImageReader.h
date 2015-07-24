/**
 * ImageReader.h
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

#import <Foundation/Foundation.h>
#import "npeimage.h"

@interface ImageReader : NSObject

+ (NetNpeImagePixelImage *)read:(NSString *)path;
+ (NetNpeImagePixelImage *)read:(NSString *)path withFormat:(NetNpeImagePixelFormat *)format;

+ (NetNpeImagePixelImage *)createPixelImage:(UIImage *)image; /* Not yet implemented */

+ (UIImage *)createUIImage:(NSString *)path;
+ (UIImage *)createUIImageFromPixelImage:(NetNpeImagePixelImage *)image;
+ (CGImageRef)createCGImage:(NSString *)path;
+ (CGImageRef)createCGImageFromPixelImage:(NetNpeImagePixelImage *)image;

+ (void *)allocateMemory:(size_t)size;
+ (void)freeMemory:(void *)memory;

@end
