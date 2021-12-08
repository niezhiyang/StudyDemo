package com.nzy.viewstudy

class Testddd {
   // 这里加了个 NotNull 注解，
   //kotlin编译器内部调用了是否为null的检查，
   //这就是为什么我们传入null的时候会编译报错

    fun Test01(name:String){
        name.length
    }
  // 这里加了个 Nullable 注解,
  //对用使用 ?. 操作符号kotlin会判断是否为null //如果不为null执行对应的逻辑，如果为null则什么也不执行
    fun Test02(name:String?){
        name?.length
    }

  //对用使用 !! 操作符号 Kotlin 同样会执行null判断如果不为null
  // 则执行对应的逻辑，如果为null 则抛出异常
    fun Test03(name:String?){
        name!!.length
    }
}