package com.nzy.coroutinedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Thread.sleep
import java.util.concurrent.Executors

/**
 * 协程天然优势：性能
 * 程序需要什么时候切，就去切，
 *  工作比较耗时：放在后台
 *  工作UI线程：更新UI，指定主线程
 *    方法的调用者去判断是否耗时，自己写的代码肯定知道是否耗时，当别人调用到这个函数的时候，一看有 suspend 还有 withContext(Dispatcher.io) 就知道。
 *
 *    https://mp.weixin.qq.com/s/N5mCacpoxGIvS_ASDAiQDQ 协程的理解  和  仍物线差不多，
 *    协程性能不比线程高，因为是基于线程的
 *    官网上说，new 1万个协程 和 1万个线程作对比，这完全就是错误的，一万个线程里面可能有10个线程，但是呢？为什么不用线程池来处理事情。
 *    用下面的单个线程池来做，比10万个协程更强
 *
 *    使用Kotlin协程，本质上其实并没有比我们原先的开发模式有多大性能上的优势，
 *    因为我们所使用的的OkHttp、AsyncTask等内部都帮我们封装了线程池，而不是直接使用Thread类。
 *    协程也不例外，里面也是封装了多个线程
 *
 *
 *
 *
 *
 *    kotlin协程相较于线程池，并没有什么性能上的优势
 *    非阻塞式挂起没什么特别的，java子线程也同样是非阻塞式的
 */

//repeat(100_000) {

//    val executor = Executors.newSingleThreadScheduledExecutor()
//    val task = Runnable {
//        print(".")
//    }
//    repeat(100_00) {
//        executor.schedule(task, 1, TimeUnit.SECONDS)
//    }
//}


class MainActivity : AppCompatActivity() {
//    协程基于线程，但相对于线程轻量很多，可理解为在用户层模拟线程操作；每创建一个协程，都有一个内核态进程动态绑定，
//    用户态下实现调度、切换，真正执行任务的还是内核线程。线程的上下文切换都需要内核参与，而协程的上下文切换，
//    完全由用户去控制，避免了大量的中断参与，减少了线程上下文切换与调度消耗的资源。


    // 协程性能上 低于线程，因为底层基于线程，顶多持平
    // 方法的调用者去判断是否耗时，自己写的代码肯定知道是否耗时，当别人调用到这个函数的时候，一看有 suspend 还有 withContext(Dispatcher.io) 就知道。
    //协程可以简化异步编程，可以顺序地表达程序，减少回调
    // 协程也是有父子关系，当父亲关闭的时候 子的也关闭了，相当于线程中的守护线程一样
    //父协程手动调用cancel()或者异常结束，会立即取消它的所有子协程。
    //父协程必须等待所有子协程完成（处于完成或者取消状态）才能完成
    //子协程抛出未捕获的异常时，默认情况下会取消其父协程
    //launch{}和aysnc{}一般都是子协程

//    对于协程的取消，cancel()只是将协程的状态修改为已取消状态，并不能取消协程的运算逻辑，协程库中很多挂起函数都会检测协程状态，
//    如果想及时取消协程的运算，最好使用isActive判断协程状态。
   private val coroutineDispatcher = newSingleThreadContext("ctx")
    private var name: String = ""
    suspend fun simple(): List<Int> {
        delay(1000) // 假装我们在这里做了一些异步的事情
        Log.e("sssssssss","${System.currentTimeMillis()}")
        return listOf(1, 2, 3)
    }
    fun simple2(): Flow<Int> = flow { // 流构建器
        for (i in 1..3) {
            delay(100) // 假装我们在这里做了一些有用的事情
            emit(i) // 发送下一个值
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            val channel = Channel<Int>()
            launch {
                for (x in 1..5) channel.send(x * x)
                channel.close() // 我们结束发送
            }
// 这里我们使用 `for` 循环来打印所有被接收到的元素（直到通道被关闭）
            for (y in channel) Log.e("sssssssss","$y")
            println("Done!")
//            simple().forEach { value ->  Log.e("sssssssss","￥$value the first coroutine ${Thread.currentThread().name}") }
            var thread =  Executors.newSingleThreadExecutor()
//            thread.execute{
//                Log.e("sssssssss","the first coroutine ${Thread.currentThread().name}")
//                sleep(2000)
//                Log.e("sssssssss","the first coroutine")
//            }
//
//            thread.execute{
//                Log.e("sssssssss","the second coroutine ${Thread.currentThread().name}")
//                sleep(1000)
//                Log.e("sssssssss","the second coroutine")
//            }

            // delay 并不会放弃时间片，只是一个挂起函数。只是自己在后台默认数 时间
            // 启动协程 1
            GlobalScope.launch(coroutineDispatcher) {
                Log.e("sssssssss","the first coroutine ${Thread.currentThread().name}")
                delay(2000)
                Log.e("sssssssss","the first coroutine")
            }
//            // 启动协程 2
            GlobalScope.launch(coroutineDispatcher) {
                Log.e("sssssssss","the second coroutine ${Thread.currentThread().name}")
                delay(1000)
                Log.e("sssssssss","the second coroutine")
            }
            ioCode1()
//            uiCode1()
//            // 阻塞当前线程,是创建一个新的协程同时阻塞当前线程，直到协程结束
//            // runBlocking 和 withContext 和async 都可以调度线程。中的线程是跟随父亲的 withContext必须传入协程context
           var 啊 = runBlocking {
                delay(3000)
                Log.e("sssssssss", "runBlocking${Thread.currentThread().name}")
            }
//            // 没有创建协程，只是挂起函数而已
            val job=  withContext(Dispatchers.Main) {
                delay(1000)
                Log.e("sssssssss", "withContext${Thread.currentThread().name}")
            }
//
//            //async 相当于又起了一个协程，{} 里面就是协程的方法体 async.await() 表示等待这个执行完，async 中的线程是跟随父亲的
            var one = async(Dispatchers.IO) {
                Log.e("sssssssss", "one------${Thread.currentThread().name}")
                delay(1000)
                dothing(1)

            }
            var two = async() {
                Log.e("sssssssss", "two------${Thread.currentThread().name}")
                delay(2000)
                dothing(2)
            }
            Log.e("sssssssss", "${ one.await()} ${ two.await()}")
//
//
//            ioCode2()
//            uiCode2()
//            ioCode3()
//            uiCode3()


            val job1 = launch(Dispatchers.Default) {
                repeat(5) {

                    Log.e("sssssssss", "job1 sleep ${it + 1} times")
                    delay(500)
                }
            }
//            delay(700)
//            job1.cancel()
//
//            val job2 = launch(Dispatchers.Default) {
//                var nextPrintTime = 0L
//                var i = 1
//                while (i <= 3) {
//                    val currentTime = System.currentTimeMillis()
//                    if (currentTime >= nextPrintTime) {
//                        Log.e("sssssssss", "job2 sleep ${i++} ...")
//                        nextPrintTime = currentTime + 500L
//                    }
//                }
//            }
//            delay(700)
//            job2.cancel()
            //上面代码中 job1 取消后，delay()会检测协程是否已取消，所以 job1 之后的运算就结束了；
            // 而 job2 取消后，没有检测协程状态的逻辑，都是计算逻辑，所以 job2 的运算逻辑还是会继续运行。

        }
//        Log.e("sssssssss", "$name -----")


    }

    private fun dothing(type:Int): String {
//        Log.e("sssssssss", "$type ----${Thread.currentThread().name}")
        return "dothing() $type"
    }

    private suspend fun ioCode1() {

        withContext(Dispatchers.IO) {
            //或者 Thread.sleep(1000)
            delay(1000) // 这个是不卡线程。

            Log.e("sssssssss", "ioCode1--- ${Thread.currentThread().name}")
        }
    }

    private suspend fun ioCode2() {
        // 切线程， 最简单的 withContext()
        withContext(Dispatchers.IO) {
            delay(1000)
            Log.e("sssssssss", "ioCode2--- ${Thread.currentThread().name}")
        }
    }

    private suspend fun ioCode3(): String {
        withContext(Dispatchers.IO) {
            delay(1000)
            Log.e("sssssssss", "ioCode3--- ${Thread.currentThread().name}")
        }
        return ""
    }

    private fun uiCode1() {
        Log.e("sssssssss", "uiCode1--- ${Thread.currentThread().name}")
    }

    private fun uiCode2() {
        Log.e("sssssssss", "uiCode2--- ${Thread.currentThread().name}")
    }

    private fun uiCode3() {
        Log.e("sssssssss", "uiCode3--- ${Thread.currentThread().name}")
    }

}
