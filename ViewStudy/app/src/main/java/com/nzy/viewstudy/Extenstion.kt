package com.nzy.viewstudy.kt

import android.content.res.Resources
import android.util.TypedValue
import com.nzy.viewstudy.Dog

/**
 *  @author niezhiyang
 *  since 9/7/21
 */
fun Dog.fly() {
   print("扩展 fly")
}

val Dog.px:Float
    get() = 1.0F

val Float.px:Float
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this, Resources.getSystem().displayMetrics)


