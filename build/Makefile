#
# Makefile for npe-image-library-ios
#

# User settings
IPHONE_OS_SDK_INCLUDE = /Applications/Xcode.app/Contents/Developer/Platforms/iPhoneOS.platform/Developer/SDKs/iPhoneOS7.1.sdk/usr/include
# End of User settings

PACKAGE_DIR = npe-image-library-ios-1.0
PACKAGE = $(PACKAGE_DIR).zip

BUILD_DIR = build_ios
SOURCE_DIR = ../src
OBJC_SOURCE_DIR = $(BUILD_DIR)/src

ARCH_DIR_I386 = $(BUILD_DIR)/i384
ARCH_DIR_X86_64 = $(BUILD_DIR)/x86_64
ARCH_DIR_ARMV6 = $(BUILD_DIR)/armv6
ARCH_DIR_ARMV7 = $(BUILD_DIR)/armv7
ARCH_DIR_ARMV7S = $(BUILD_DIR)/armv7s
ARCH_DIR_ARM64 = $(BUILD_DIR)/arm64

J2OBJC = j2objc
J2OBJCC = j2objcc

#J2OBJCC_FLAGS = 
J2OBJCC_FLAGS = -fobjc-abi-version=2 -fobjc-legacy-dispatch

SOURCES = $(wildcard \
 $(SOURCE_DIR)/net/npe/io/*.java \
 $(SOURCE_DIR)/net/npe/image/*.java \
 $(SOURCE_DIR)/net/npe/image/dds/*.java \
 $(SOURCE_DIR)/net/npe/image/psd/*.java \
 $(SOURCE_DIR)/net/npe/image/tga/*.java \
 $(SOURCE_DIR)/net/npe/image/util/*.java \
)

DIR_I386 = \
 $(ARCH_DIR_I386)/net/npe/io \
 $(ARCH_DIR_I386)/net/npe/image/dds \
 $(ARCH_DIR_I386)/net/npe/image/psd \
 $(ARCH_DIR_I386)/net/npe/image/tga \
 $(ARCH_DIR_I386)/net/npe/image/util

DIR_X86_64 = $(subst $(ARCH_DIR_I386),$(ARCH_DIR_X86_64),$(DIR_I386))
DIR_ARMV6 = $(subst $(ARCH_DIR_I386),$(ARCH_DIR_ARMV6),$(DIR_I386))
DIR_ARMV7 = $(subst $(ARCH_DIR_I386),$(ARCH_DIR_ARMV7),$(DIR_I386))
DIR_ARMV7S = $(subst $(ARCH_DIR_I386),$(ARCH_DIR_ARMV7S),$(DIR_I386))
DIR_ARM64 = $(subst $(ARCH_DIR_I386),$(ARCH_DIR_ARM64),$(DIR_I386))

OBJC_SOURCES = $(subst .java,.m, $(subst $(SOURCE_DIR),$(OBJC_SOURCE_DIR),$(SOURCES)))

OBJECTS = $(subst .java,.o, $(subst $(SOURCE_DIR),$(OBJC_SOURCE_DIR),$(SOURCES)))

OBJECTS_I386 = $(subst .java,.o, $(subst $(SOURCE_DIR),$(ARCH_DIR_I386),$(SOURCES)))
OBJECTS_X86_64 = $(subst $(ARCH_DIR_I386),$(ARCH_DIR_X86_64),$(OBJECTS_I386))
OBJECTS_ARMV6 = $(subst $(ARCH_DIR_I386),$(ARCH_DIR_ARMV6),$(OBJECTS_I386))
OBJECTS_ARMV7 = $(subst $(ARCH_DIR_I386),$(ARCH_DIR_ARMV7),$(OBJECTS_I386))
OBJECTS_ARMV7S = $(subst $(ARCH_DIR_I386),$(ARCH_DIR_ARMV7S),$(OBJECTS_I386))
OBJECTS_ARM64 = $(subst $(ARCH_DIR_I386),$(ARCH_DIR_ARM64),$(OBJECTS_I386))

HEADERS = $(OBJECTS:.o=.h)

PACKAGE_HEADERS = $(subst $(BUILD_DIR),package/include,$(HEADERS))

LIB_NAME = libnpeimage
LIB_FAT = $(BUILD_DIR)/$(LIB_NAME).a
LIB_I386 = $(BUILD_DIR)/$(LIB_NAME)_i386.a
LIB_X86_64 = $(BUILD_DIR)/$(LIB_NAME)_x86_64.a
LIB_ARMV6 = $(BUILD_DIR)/$(LIB_NAME)_armv6.a
LIB_ARMV7 = $(BUILD_DIR)/$(LIB_NAME)_armv7.a
LIB_ARMV7S = $(BUILD_DIR)/$(LIB_NAME)_armv7s.a
LIB_ARM64 = $(BUILD_DIR)/$(LIB_NAME)_arm64.a

.SUFFIXES: .java .m

$(PACKAGE): $(LIB_FAT)
	mkdir -p $(PACKAGE_DIR)/{include,lib}
	cp -r $(OBJC_SOURCE_DIR)/net $(PACKAGE_DIR)/include
	rm -rf $(subst $(OBJC_SOURCE_DIR),$(PACKAGE_DIR)/include,$(OBJC_SOURCES))
	cp ../objc/npeimage.h $(PACKAGE_DIR)/include
	cp $(LIB_FAT) $(PACKAGE_DIR)/lib
#	mkdir -p $(PACKAGE_DIR)/NPEImage.framework/Versions/A/Headers
#	cp -r $(PACKAGE_DIR)/include/* $(PACKAGE_DIR)/NPEImage.framework/Versions/A/Headers
#	cp -r $(LIB_FAT) $(PACKAGE_DIR)/NPEImage.framework/Versions/A/NPEImage
#	cd $(PACKAGE_DIR)/NPEImage.framework; ln -s Versions/A/Headers .
#	ln -s A $(PACKAGE_DIR)/NPEImage.framework/Versions/Current
#	ln -s Versions/Current/Headers $(PACKAGE_DIR)/NPEImage.framework
#	ln -s Versions/Current/NPEImage $(PACKAGE_DIR)/NPEImage.framework
	zip -r $(PACKAGE) $(PACKAGE_DIR)

$(LIB_FAT) : $(LIB_I386) $(LIB_X86_64) $(LIB_ARMV7) $(LIB_ARMV7S) $(LIB_ARM64)
	lipo -create $^ -output $@

$(LIB_I386): translate $(DIR_I386) $(OBJECTS_I386)
	ar r $@ $(OBJECTS_I386)

$(LIB_X86_64): translate $(DIR_X86_64) $(OBJECTS_X86_64)
	ar r $@ $(OBJECTS_X86_64)

$(LIB_ARMV6): translate $(DIR_ARMV6) $(OBJECTS_ARMV6)
	ar r $@ $(OBJECTS_ARMV6)

$(LIB_ARMV7): translate $(DIR_ARMV7) $(OBJECTS_ARMV7)
	ar r $@ $(OBJECTS_ARMV7)

$(LIB_ARMV7S): translate $(DIR_ARMV7S) $(OBJECTS_ARMV7S)
	ar r $@ $(OBJECTS_ARMV7S)

$(LIB_ARM64): translate $(DIR_ARM64) $(OBJECTS_ARM64)
	ar r $@ $(OBJECTS_ARM64)

translate: $(OBJC_SOURCE_DIR) $(OBJC_SOURCES)

clean:
	@rm -rf $(PACKAGE) $(PACKAGE_DIR) $(RESULT) $(BUILD_DIR)

$(OBJC_SOURCE_DIR)/%.m $(OBJC_SOURCE_DIR)/%.h: $(SOURCE_DIR)/%.java
	$(J2OBJC) -sourcepath $(SOURCE_DIR) -d $(OBJC_SOURCE_DIR) $?

$(ARCH_DIR_I386)/%.o: $(OBJC_SOURCE_DIR)/%.m
	$(J2OBJCC) -arch i386 $(J2OBJCC_FLAGS) -I$(OBJC_SOURCE_DIR) -c $? -o $@

$(ARCH_DIR_X86_64)/%.o: $(OBJC_SOURCE_DIR)/%.m
	$(J2OBJCC) -arch x86_64 $(J2OBJCC_FLAGS) -I$(OBJC_SOURCE_DIR) -c $? -o $@

$(ARCH_DIR_ARMV6)/%.o: $(OBJC_SOURCE_DIR)/%.m
	$(J2OBJCC) -arch armv6 $(J2OBJCC_FLAGS) -I$(OBJC_SOURCE_DIR) -I$(IPHONE_OS_SDK_INCLUDE) -c $? -o $@

$(ARCH_DIR_ARMV7)/%.o: $(OBJC_SOURCE_DIR)/%.m
	$(J2OBJCC) -arch armv7 $(J2OBJCC_FLAGS) -I$(OBJC_SOURCE_DIR) -I$(IPHONE_OS_SDK_INCLUDE) -c $? -o $@

$(ARCH_DIR_ARMV7S)/%.o: $(OBJC_SOURCE_DIR)/%.m
	$(J2OBJCC) -arch armv7s $(J2OBJCC_FLAGS) -I$(OBJC_SOURCE_DIR) -I$(IPHONE_OS_SDK_INCLUDE) -c $? -o $@

$(ARCH_DIR_ARM64)/%.o: $(OBJC_SOURCE_DIR)/%.m
	$(J2OBJCC) -arch arm64 $(J2OBJCC_FLAGS) -I$(OBJC_SOURCE_DIR) -I$(IPHONE_OS_SDK_INCLUDE) -c $? -o $@

$(OBJC_SOURCE_DIR) $(DIR_I386) $(DIR_X86_64) $(DIR_ARMV6) $(DIR_ARMV7) $(DIR_ARMV7S) $(DIR_ARM64):
	mkdir -p $@
