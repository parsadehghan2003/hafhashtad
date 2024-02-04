package com.ftpd.hafhashtad.base_android.extentions

import android.content.res.Resources
import kotlin.math.ceil

fun Int.dp() : Int{
    return if (this == 0) {
        0
    } else ceil((Resources.getSystem().displayMetrics.density * this).toDouble()).toInt()
}

fun Int.dpToPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}

fun Int.pxToDp(): Int {
    return (this / Resources.getSystem().displayMetrics.density).toInt()
}