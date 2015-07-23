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

#import "ImageReader.h"
#import "IOSPrimitiveArray.h"

@implementation ImageReader

+ (NetNpeImagePixelImage *)read:(NSString *)path {
    return [self read:path withFormat:NetNpeImagePixelFormat_ABGR_];
}

+ (NetNpeImagePixelImage *)read:(NSString *)path withFormat:(NetNpeImagePixelFormat *)format {
    
    NetNpeImageUtilImageTypeEnum *type = [NetNpeImageUtilImageReader getImageTypeWithNSString:path];
    
    if(type != NULL) {
        
        FILE *file = fopen([path UTF8String], "rb");
        if(file) {
            fseek(file, 0, SEEK_END);
            int size = ftell(file);
            fseek(file, 0, SEEK_SET);
            
            char *buffer = (char *)[self allocateMemory:size];
            fread(buffer, 1, size, file);
            fclose(file);
            
            IOSByteArray *a = [IOSByteArray arrayWithBytes:buffer count:size];
            
            NetNpeImagePixelImage *image = [NetNpeImageUtilImageReader readWithNetNpeImageUtilImageTypeEnum:type withNetNpeImagePixelFormat:NetNpeImagePixelFormat_ABGR_ withByteArray:a withInt:0];
            
            [self freeMemory:buffer];
            
            return image;
        }
        
    }
    
    return NULL;
    
}

+ (NetNpeImagePixelImage *)createPixelImage:(UIImage *)image {
    /* Not yet implemented
    IOSIntArray *pixels = NULL;
    int width = [image size].width;
    int height = [image size].height;
    return new_NetNpeImagePixelImage_initWithIntArray_withInt_withInt_withNetNpeImagePixelFormat_(pixels, width, height, NetNpeImagePixelFormat_ABGR_);
     */
    return NULL;
}

+ (UIImage *)createUIImage:(NSString *)path {
    
    NetNpeImageUtilImageTypeEnum *type = [NetNpeImageUtilImageReader getImageTypeWithNSString:path];
    
    if(type != NULL) {
        NetNpeImagePixelImage *image = [self read:path];
        return [self createUIImageFromPixelImage:image];
    }
    
    return [UIImage imageWithContentsOfFile:path];
    
}

+ (UIImage *)createUIImageFromPixelImage:(NetNpeImagePixelImage *)image {
    return [UIImage imageWithCGImage:[self createCGImage:image]];
}

+ (CGImageRef)createCGImage:(NetNpeImagePixelImage *)image {
    
    [image changeFormatWithNetNpeImagePixelFormat:NetNpeImagePixelFormat_ABGR_];
    
    int width = [image getWidth];
    int height = [image getHeight];
    
    IOSIntArray *pixelArray = [image getPixels];
    int *arrayPixels = IOSIntArray_GetRef(pixelArray, 0);
    int arrayLength = [pixelArray length];
    
    int *pixels = [self allocateMemory:4*arrayLength];
    memcpy(pixels, arrayPixels, 4*arrayLength);
    
    CGColorSpaceRef colorSpaceRef = CGColorSpaceCreateDeviceRGB();
    CGBitmapInfo bitmapInfo = (CGBitmapInfo)kCGImageAlphaLast;
    CGDataProviderRef providerRef = CGDataProviderCreateWithData(NULL, pixels, 4*width*height, releaseDataCallback);
    
    return CGImageCreate(width, height, 8, 32, 4*width, colorSpaceRef, bitmapInfo, providerRef, NULL, 0, kCGRenderingIntentDefault);
    
}

+ (void *)allocateMemory:(size_t)size {
    return malloc(size);
}

+ (void)freeMemory:(void *)memory {
    free(memory);
}

static void releaseDataCallback(void *info, const void *data, size_t size) {
    [ImageReader freeMemory:(void *)data];
}

@end
