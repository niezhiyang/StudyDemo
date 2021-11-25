package com.nzy.viewstudy

/**
 *  @author niezhiyang
 *  since 11/15/21
 */
class Demo constructor() {
    companion object {
        val instance: Demo by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Demo()
        }
    }
}