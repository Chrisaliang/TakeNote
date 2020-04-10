#include <jni.h>
#include <string>

//extern "C" JNIEXPORT jstring JNICALL
//Java_com_zaihui_mc_MainActivity_stringFromJNI(
//        JNIEnv* env,
//        jobject /* this */) {
//    std::string hello = "Hello from C++";
//    return env->NewStringUTF(hello.c_str());
//}
extern "C"
JNIEXPORT jint JNICALL
Java_com_chris_eban_common_ZipUtils_executeCommand(JNIEnv *env, jclass clazz, jstring command) {
    // TODO: implement executeCommand()
}