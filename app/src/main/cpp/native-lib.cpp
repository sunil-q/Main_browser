#include <jni.h>
#include <string>



extern "C"
JNIEXPORT jstring JNICALL
Java_com_fastspeed_five5gbrowser_rld_1bholu_core_1bholu_BholuSplashActivity_stringFromJNI(JNIEnv *env, jobject thiz) {
    std::string hello = "https://para-lx--db-default-rtdb.firebaseio.com/apps/";
    return env->NewStringUTF(hello.c_str());
}