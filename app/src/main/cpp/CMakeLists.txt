# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

set(LOCAL_PATH src/main/cpp/p7zip)

include_directories(${LOCAL_PATH}/C ${LOCAL_PATH}/CPP ${LOCAL_PATH}/CPP/myWindows ${LOCAL_PATH}/CPP/include_windows ${LOCAL_PATH}/CPP/Windows ${LOCAL_PATH}/CPP/Common ${LOCAL_PATH}/CPP/7zip/Common ${LOCAL_PATH}/CPP/7zip/UI/Agent ${LOCAL_PATH}/CPP/7zip/UI/FileManager)
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -DANDROID_NDK -fexceptions -DNDEBUG -D_REENTRANT -DENV_UNIX -DEXTERNAL_CODECS -DUNICODE -D_UNICODE -DUNIX_USE_WIN_FILE")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -DANDROID_NDK -fexceptions -DNDEBUG -D_REENTRANT -DENV_UNIX -DEXTERNAL_CODECS -DUNICODE -D_UNICODE -DUNIX_USE_WIN_FILE")


file(GLOB my_c_path ${LOCAL_PATH}/C/*.c)
file(GLOB my_archive_common_path p7zip/CPP/7zip/Archive/Common/*.cpp)
file(GLOB my_archive_7z_path p7zip/CPP/7zip/Archive/7z/*.cpp)
file(GLOB my_archive_cab_path p7zip/CPP/7zip/Archive/Cab/*.cpp)
file(GLOB my_archive_chm_path p7zip/CPP/7zip/Archive/Chm/*.cpp)
file(GLOB my_archive_iso_path p7zip/CPP/7zip/Archive/Iso/*.cpp)
file(GLOB my_archive_nsis_path p7zip/CPP/7zip/Archive/Nsis/*.cpp)
file(GLOB my_archive_rar_path p7zip/CPP/7zip/Archive/Rar/*.cpp)
file(GLOB my_archive_tar_path p7zip/CPP/7zip/Archive/Tar/*.cpp)
file(GLOB my_archive_udf_path p7zip/CPP/7zip/Archive/Udf/*.cpp)
file(GLOB my_archive_vim_path p7zip/CPP/7zip/Archive/Wim/*.cpp)
file(GLOB my_archive_zip_path p7zip/CPP/7zip/Archive/Zip/*.cpp)

file(GLOB my_7zip_common_path p7zip/CPP/7zip/Common/*.cpp)
file(GLOB my_7zip_crypto_path p7zip/CPP/7zip/Crypto/*.cpp)
file(GLOB my_7zip_ui_path p7zip/CPP/7zip/UI/Console/*.cpp)
file(GLOB my_common_path p7zip/CPP/Common/*.cpp)


add_library( # Sets the name of the library.
        p7zip

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        ${my_c_path}
        ${my_archive_common_path}
        ${my_archive_7z_path}
        ${my_archive_cab_path}
        ${my_archive_chm_path}
        ${my_archive_iso_path}
        ${my_archive_nsis_path}
        ${my_archive_rar_path}
        ${my_archive_tar_path}
        ${my_archive_udf_path}
        ${my_archive_vim_path}
        ${my_archive_zip_path}
        ${my_7zip_common_path}
        ${my_7zip_crypto_path}
        ${my_7zip_ui_path}
        ${my_common_path}
        p7zip/CPP/7zip/Archive/ApmHandler.cpp
        p7zip/CPP/7zip/Archive/ArHandler.cpp
        p7zip/CPP/7zip/Archive/ArjHandler.cpp
        p7zip/CPP/7zip/Archive/Bz2Handler.cpp
        p7zip/CPP/7zip/Archive/ComHandler.cpp
        p7zip/CPP/7zip/Archive/CpioHandler.cpp
        p7zip/CPP/7zip/Archive/CramfsHandler.cpp
        p7zip/CPP/7zip/Archive/DeflateProps.cpp
        p7zip/CPP/7zip/Archive/DmgHandler.cpp
        p7zip/CPP/7zip/Archive/ElfHandler.cpp
        p7zip/CPP/7zip/Archive/ExtHandler.cpp
        p7zip/CPP/7zip/Archive/FatHandler.cpp
        p7zip/CPP/7zip/Archive/FlvHandler.cpp
        p7zip/CPP/7zip/Archive/GptHandler.cpp
        p7zip/CPP/7zip/Archive/GzHandler.cpp
        p7zip/CPP/7zip/Archive/HandlerCont.cpp
        p7zip/CPP/7zip/Archive/HfsHandler.cpp
        p7zip/CPP/7zip/Archive/IhexHandler.cpp
        p7zip/CPP/7zip/Archive/LzhHandler.cpp
        p7zip/CPP/7zip/Archive/LzmaHandler.cpp
        p7zip/CPP/7zip/Archive/MachoHandler.cpp
        p7zip/CPP/7zip/Archive/MbrHandler.cpp
        p7zip/CPP/7zip/Archive/MslzHandler.cpp
        p7zip/CPP/7zip/Archive/MubHandler.cpp
        p7zip/CPP/7zip/Archive/NtfsHandler.cpp
        p7zip/CPP/7zip/Archive/PeHandler.cpp
        p7zip/CPP/7zip/Archive/PpmdHandler.cpp
        p7zip/CPP/7zip/Archive/QcowHandler.cpp
        p7zip/CPP/7zip/Archive/RpmHandler.cpp

        p7zip/CPP/7zip/Archive/SplitHandler.cpp
        p7zip/CPP/7zip/Archive/SquashfsHandler.cpp
        p7zip/CPP/7zip/Archive/SwfHandler.cpp
        p7zip/CPP/7zip/Archive/UefiHandler.cpp
        p7zip/CPP/7zip/Archive/VdiHandler.cpp
        p7zip/CPP/7zip/Archive/VhdHandler.cpp
        p7zip/CPP/7zip/Archive/VmdkHandler.cpp
        p7zip/CPP/7zip/Archive/XarHandler.cpp
        p7zip/CPP/7zip/Archive/XzHandler.cpp
        p7zip/CPP/7zip/Archive/ZHandler.cpp

        p7zip/CPP/7zip/Compress/Bcj2Coder.cpp
        p7zip/CPP/7zip/Compress/Bcj2Register.cpp
        p7zip/CPP/7zip/Compress/BcjCoder.cpp
        p7zip/CPP/7zip/Compress/BcjRegister.cpp
        p7zip/CPP/7zip/Compress/BitlDecoder.cpp
        p7zip/CPP/7zip/Compress/BranchMisc.cpp
        p7zip/CPP/7zip/Compress/BranchRegister.cpp
        p7zip/CPP/7zip/Compress/ByteSwap.cpp
        p7zip/CPP/7zip/Compress/BZip2Crc.cpp
        p7zip/CPP/7zip/Compress/BZip2Decoder.cpp
        p7zip/CPP/7zip/Compress/BZip2Encoder.cpp
        p7zip/CPP/7zip/Compress/BZip2Register.cpp
        p7zip/CPP/7zip/Compress/CodecExports.cpp
        p7zip/CPP/7zip/Compress/CopyCoder.cpp
        p7zip/CPP/7zip/Compress/CopyRegister.cpp
        p7zip/CPP/7zip/Compress/Deflate64Register.cpp
        p7zip/CPP/7zip/Compress/DeflateDecoder.cpp
        p7zip/CPP/7zip/Compress/DeflateEncoder.cpp
        p7zip/CPP/7zip/Compress/DeflateRegister.cpp
        p7zip/CPP/7zip/Compress/DeltaFilter.cpp
        p7zip/CPP/7zip/Compress/ImplodeDecoder.cpp
        p7zip/CPP/7zip/Compress/ImplodeHuffmanDecoder.cpp
        p7zip/CPP/7zip/Compress/LzhDecoder.cpp

        p7zip/CPP/7zip/Compress/Lzma2Decoder.cpp
        p7zip/CPP/7zip/Compress/Lzma2Encoder.cpp
        p7zip/CPP/7zip/Compress/Lzma2Register.cpp
        p7zip/CPP/7zip/Compress/LzmaDecoder.cpp
        p7zip/CPP/7zip/Compress/LzmaEncoder.cpp
        p7zip/CPP/7zip/Compress/LzmaRegister.cpp
        p7zip/CPP/7zip/Compress/LzmsDecoder.cpp
        p7zip/CPP/7zip/Compress/LzOutWindow.cpp
        p7zip/CPP/7zip/Compress/LzxDecoder.cpp
        p7zip/CPP/7zip/Compress/PpmdDecoder.cpp
        p7zip/CPP/7zip/Compress/PpmdEncoder.cpp
        p7zip/CPP/7zip/Compress/PpmdRegister.cpp
        p7zip/CPP/7zip/Compress/PpmdZip.cpp
        p7zip/CPP/7zip/Compress/QuantumDecoder.cpp
        p7zip/CPP/7zip/Compress/Rar1Decoder.cpp
        p7zip/CPP/7zip/Compress/Rar2Decoder.cpp
        p7zip/CPP/7zip/Compress/Rar3Decoder.cpp
        p7zip/CPP/7zip/Compress/Rar3Vm.cpp
        p7zip/CPP/7zip/Compress/Rar5Decoder.cpp
        p7zip/CPP/7zip/Compress/RarCodecsRegister.cpp
        p7zip/CPP/7zip/Compress/ShrinkDecoder.cpp
        p7zip/CPP/7zip/Compress/XpressDecoder.cpp
        p7zip/CPP/7zip/Compress/ZDecoder.cpp
        p7zip/CPP/7zip/Compress/ZlibDecoder.cpp
        p7zip/CPP/7zip/Compress/ZlibEncoder.cpp

        p7zip/CPP/7zip/UI/Common/ArchiveCommandLine.cpp
        p7zip/CPP/7zip/UI/Common/ArchiveExtractCallback.cpp
        p7zip/CPP/7zip/UI/Common/ArchiveName.cpp
        p7zip/CPP/7zip/UI/Common/ArchiveOpenCallback.cpp
        p7zip/CPP/7zip/UI/Common/Bench.cpp

        p7zip/CPP/7zip/UI/Common/DefaultName.cpp
        p7zip/CPP/7zip/UI/Common/EnumDirItems.cpp
        p7zip/CPP/7zip/UI/Common/Extract.cpp
        p7zip/CPP/7zip/UI/Common/ExtractingFilePath.cpp
        p7zip/CPP/7zip/UI/Common/HashCalc.cpp
        p7zip/CPP/7zip/UI/Common/LoadCodecs.cpp
        p7zip/CPP/7zip/UI/Common/OpenArchive.cpp
        p7zip/CPP/7zip/UI/Common/PropIDUtils.cpp
        p7zip/CPP/7zip/UI/Common/SetProperties.cpp
        p7zip/CPP/7zip/UI/Common/SortUtils.cpp
        p7zip/CPP/7zip/UI/Common/TempFiles.cpp
        p7zip/CPP/7zip/UI/Common/Update.cpp
        p7zip/CPP/7zip/UI/Common/UpdateAction.cpp
        p7zip/CPP/7zip/UI/Common/UpdateCallback.cpp
        p7zip/CPP/7zip/UI/Common/UpdatePair.cpp
        p7zip/CPP/7zip/UI/Common/UpdateProduce.cpp

        p7zip/CPP/Windows/COM.cpp
        p7zip/CPP/Windows/DLL.cpp
        p7zip/CPP/Windows/ErrorMsg.cpp
        p7zip/CPP/Windows/FileDir.cpp
        p7zip/CPP/Windows/FileFind.cpp
        p7zip/CPP/Windows/FileIO.cpp
        p7zip/CPP/Windows/FileName.cpp
        p7zip/CPP/Windows/PropVariantConv.cpp
        p7zip/CPP/Windows/PropVariant.cpp
        p7zip/CPP/Windows/PropVariantUtils.cpp
        p7zip/CPP/Windows/Registry.cpp
        p7zip/CPP/Windows/Synchronization.cpp
        p7zip/CPP/Windows/System.cpp
        p7zip/CPP/Windows/TimeUtils.cpp


        p7zip/CPP/myWindows/myAddExeFlag.cpp
        p7zip/CPP/myWindows/mySplitCommandLine.cpp
        p7zip/CPP/myWindows/wine_date_and_time.cpp

        p7zip/p7zip.cpp)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        p7zip

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})