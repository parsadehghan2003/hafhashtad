package com.ftpd.hafhashtad.base_android.util

object CharacterCompat {


    private const val MIN_HIGH_SURROGATE = '\uD800'
    private const val MIN_SUPPLEMENTARY_CODE_POINT = 0x010000
    private const val MIN_LOW_SURROGATE = '\uDC00'

    /**
     * Compat version of [Character.highSurrogate]
     */
    fun highSurrogate(codePoint: Int): Char {
        return ((codePoint ushr 10)
                + (MIN_HIGH_SURROGATE.code - (MIN_SUPPLEMENTARY_CODE_POINT ushr 10))).toChar()
    }

    /**
     * Compat version of [Character.lowSurrogate]
     */
    fun lowSurrogate(codePoint: Int): Char {
        return ((codePoint and 0x3ff) + MIN_LOW_SURROGATE.code).toChar()
    }
}