package com.nzy.viewstudy

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.lang.reflect.Proxy
import kotlin.coroutines.CoroutineContext

/**
 *  @author niezhiyang
 *  since 2020/9/17
 */
class LLLL {
    suspend fun getName() {

    }

    suspend fun getName(name: String) {

    }

    suspend fun getName(name: Int): String {
        return name.toString()
    }

    fun getTime() {

        runBlocking(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                getName(1)
            }



        }

    }
}