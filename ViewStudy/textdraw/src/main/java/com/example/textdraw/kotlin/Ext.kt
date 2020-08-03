package com.example.textdraw.kotlin

import android.content.res.Resources
import android.util.TypedValue
import android.view.View

fun View.dp2px(dp: Float): Float{
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp, Resources.getSystem().displayMetrics)
}

fun View.sp2px(sp: Float): Float{
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp, Resources.getSystem().displayMetrics)
}
