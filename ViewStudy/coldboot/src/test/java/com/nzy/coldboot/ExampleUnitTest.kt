package com.nzy.coldboot

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val map: MutableMap<String, String> = LinkedHashMap()
        map["数学"] = "数学老师"
        map["化学"] = "化学老师"
        map["物理"] = "物理老师"
        map["生物"] = "生物老师"
        map["政治"] = "政治老师"
        for ((key, value) in map) {
            println("$key-->$value")
        }

        println("--------------------------------------")

 val map2: MutableMap<String, String> = HashMap()
        map2["数学"] = "数学老师"
        map2["化学"] = "化学老师"
        map2["物理"] = "物理老师"
        map2["生物"] = "生物老师"
        map2["政治"] = "政治老师"
        for ((key, value) in map2) {
            println("$key-->$value")
        }

    }
}