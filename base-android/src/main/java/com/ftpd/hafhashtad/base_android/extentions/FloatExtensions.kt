package com.ftpd.hafhashtad.base_android.extentions

import com.ftpd.hafhashtad.base_android.util.AndroidUtilities
import kotlin.math.ceil

fun Float.dp() : Int{
    return if (this == 0F) {
        0
    } else ceil((AndroidUtilities.displayDensity * this).toDouble()).toInt()

}
