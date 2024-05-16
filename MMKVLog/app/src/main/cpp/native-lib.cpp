#include <jni.h>
#include <string>
#include <stdio.h>
#include <unistd.h>
#include <sys/mman.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <stdlib.h>
#include <android/log.h>

#define LOG_TAG "niezhiyang"
#define LOGI(...)  __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

extern "C" JNIEXPORT jstring
JNICALL
Java_com_nzy_nLog_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

int offset = 4;
int8_t *ptr = nullptr;

void appendData(char *data);

extern "C"
JNIEXPORT jstring JNICALL
Java_com_nzy_nLog_MainActivity_intMmap(JNIEnv *env, jobject thiz, jstring file_name) {
    //打开文件
//    const char *rawString = env->GetStringUTFChars(fileName, 0);
//    fd = open(rawString, O_RDWR | O_CREAT, 0);
//    // 获取一页的内存大小
//    int pageSize = getpagesize();
//    // 设置size大小
//    mmapSize = pageSize * 1024 * 1024;
//    // 设置文件大小为这么大
//    ftruncate(fd, mmapSize);
//    // 设置mmap ，add null固定，大小，读写 ，共享，映射文件，偏移量0
//    // https://zhuanlan.zhihu.com/p/656975553
//    ptr = static_cast<int8_t *>(mmap(NULL, mmapSize, PROT_READ | PROT_WRITE, MAP_SHARED, fd,
//                                     0));
//    std::string data("这里是要写入文件的内容");
//    //用户空间可以操作这个虚拟内存地址
//    memcpy(ptr, data.data(), data.size());



    std::string file = env->GetStringUTFChars(file_name, nullptr);
    //获取文件描述符
    int fd = open(file.c_str(), O_RDWR | O_CREAT, S_IRWXU);
    //设置文件大小 8M 一页就是4k
    int size = 1024 * getpagesize()*2;
    LOGI("The number is: %d", getpagesize());
    // 设置文件大小
    ftruncate(fd, size);
    //调用mmap函数，返回的是物理映射的虚拟内存地址
    ptr = static_cast<int8_t *>(mmap(0, size, PROT_READ | PROT_WRITE,
                                     MAP_SHARED, fd,
                                     0));
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());

}

extern "C"
JNIEXPORT void JNICALL
Java_com_nzy_nLog_MainActivity_write(JNIEnv *env, jobject thiz, jstring value) {
    const char *cdata = env->GetStringUTFChars(value, 0);
    //要写入文件的内容
    std::string data(cdata);
    //用户空间可以操作这个虚拟内存地址
    memcpy(ptr, data.data(), data.size());
    char *pId = const_cast<char *>(env->GetStringUTFChars(value, 0));
    appendData(pId);
}

void appendData(char *data) {
    memcpy(ptr + offset, data, strlen(data));
    offset = offset + strlen(data);
}



